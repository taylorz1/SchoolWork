package csse374.analyzers;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import csse374.Project.Enums;
import csse374.Project.Model;
import csse374.Project.RelationObject;
import csse374.Project.Enums.Enum;
import edu.rosehulman.jvm.sigevaluator.FieldEvaluator;
import edu.rosehulman.jvm.sigevaluator.GenericType;
import edu.rosehulman.jvm.sigevaluator.MethodEvaluator;
import gen.plugin.core.gvrender_core_svg__c;
import soot.Body;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.tagkit.Tag;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class DependencyAnalyzor extends AbstractAnalyzor {

	private Set<SootClass> sootclass;
	private Set<RelationObject> relations;

	@Override
	public void analyze(Model model) {
		sootclass = model.getSootClass();
		relations = model.getRelations();
		for (SootClass s : sootclass) {
			if(!s.isAbstract()){
				Map<String, Boolean> r = getDependency(s);
				for (String str : r.keySet()) {
					if (!checkAssociation(s.getName(), str, r.get(str))) {
						if (r.get(str)) {
							RelationObject obj = new RelationObject(s.getName(), str, Enums.Enum.DEPENDENCYMANY);
							if (!model.checkRelation(obj)) {
								model.addRelation(obj);
							}
						} else {
							RelationObject obj = new RelationObject(s.getName(), str, Enums.Enum.DEPENDENCY);
							if (!model.checkRelation(obj)) {
								model.addRelation(obj);
							}
						}
					}
				}
			}
		}
	}

	private Map<String, Boolean> getDependency(SootClass c) {
		List<RelationObject> rel = new ArrayList<>();
		Map<String, Boolean> remove_dup = new HashMap<>();
		// if (!c.isInterface()) {
		for (SootMethod m : c.getMethods()) {
			if (!m.isAbstract()) {
				System.out.println(m.getName());
				if (!m.hasActiveBody()) {
					continue;
				}
				Body body = m.retrieveActiveBody();
				UnitGraph cfg = new ExceptionalUnitGraph(body);
				cfg.forEach(stmt -> {
					String test = stmt.toString();
					String[] tokens = stmt.toString().split(" ");
					if (tokens.length >= 4 && tokens[2].equals("new")) {
						if (sootContains(tokens[3])) {
							if (!remove_dup.containsKey(tokens[3])) {
								remove_dup.put(tokens[3], false);
							}
						}
					}
				});
			}
			if (m.getTag("SignatureTag") != null) {
				Map<String, Boolean> typeMap = getGeneric(m);
				for (String n : typeMap.keySet()) {
					if (sootContains(n)) {
						if (!remove_dup.containsKey(n)) {
							remove_dup.put(n, typeMap.get(n));
						} else {
							remove_dup.replace(n, typeMap.get(n));
						}
					}
				}
			} else {
				if (sootContains(m.getReturnType().toString())) {

					if (c.getName().toString().equals("headfirst.factory.pizzaaf.PizzaIngredientFactory")) {
						System.out.println();
						System.out.println(m.getName().toString() + " " + m.getReturnType().toString());
						System.out.println();
					}

					if (m.getReturnType().toString().contains("[")) {
						if (!remove_dup.containsKey(
								m.getReturnType().toString().substring(0, m.getReturnType().toString().length() - 2))) {
							remove_dup.put(m.getReturnType().toString().substring(0,
									m.getReturnType().toString().length() - 2), true);
						} else {
							remove_dup.replace(m.getReturnType().toString().substring(0,
									m.getReturnType().toString().length() - 2), true);
						}
					} else {
						if (!remove_dup.containsKey(m.getReturnType().toString())) {
							remove_dup.put(m.getReturnType().toString(), false);
						}
					}
				}
				for (Type t : m.getParameterTypes()) {
					if (sootContains(t.toString())) {
						if (t.toString().contains("[")) {
							if (!remove_dup.containsKey(t.toString().substring(0, t.toString().length() - 2))) {
								remove_dup.put(t.toString().substring(0, t.toString().length() - 2), true);
							} else {
								remove_dup.replace(t.toString().substring(0, t.toString().length() - 2), true);
							}
						} else {
							if (!remove_dup.containsKey(t.toString())) {
								remove_dup.put(t.toString(), false);
							}
						}
					}
				}
			}

			// }
		}
		return remove_dup;
	}

	private boolean sootContains(String name) {
		for (SootClass c : sootclass) {
			if (c.getName().equals(name)
					|| (name.contains("[") && c.getName().equals(name.substring(0, name.length() - 2)))) {
				return true;
			}
		}
		return false;
	}

	private boolean checkAssociation(String from, String to, boolean b) {
		for (RelationObject obj : relations) {
			if (obj.getFrom().equals(from) && obj.getTo().equals(to)
					&& (obj.getType().equals(Enums.Enum.ASSOCIATION) || obj.getType().equals(Enums.Enum.ASSOCIATIONMANY)
							|| obj.getType().equals(Enums.Enum.DEPENDENCYMANY))) {
				return true;
			}
			if (obj.getFrom().equals(from) && obj.getTo().equals(to) && obj.getType().equals(Enums.Enum.DEPENDENCY)) {
				if (!b) {
					return true;
				} else {
					obj.setType(Enums.Enum.DEPENDENCYMANY);
					return true;
				}
			}
		}
		return false;
	}

	private Map<String, Boolean> getGeneric(SootMethod m) {
		Map<String, Boolean> names = new HashMap();
		Tag signaturetag = m.getTag("SignatureTag");
		if (signaturetag != null) {
			String signature = signaturetag.toString();
			MethodEvaluator evaluator = new MethodEvaluator(signature);

			try {
				List<GenericType> types = evaluator.getParameterTypes();
				GenericType returntype = evaluator.getReturnType();
				types.add(returntype);

				for (GenericType type : types) {
					if (type.toString().contains("<")) {
						String[] generics = type.toString().split("<")[1].split(",");
						generics[generics.length - 1] = generics[generics.length - 1].substring(0,
								generics[generics.length - 1].length() - 1);
						for (String s : generics) {
							names.put(s, true);
						}
					} else {
						if (type.toString().contains("[")) {
							names.put(type.toString().substring(0, type.toString().length() - 2), true);
						} else {
							names.put(type.toString(), false);
						}
					}
				}
			} catch (Exception e) {
				List<Type> types = m.getParameterTypes();
				for (Type type : types) {
					if (type.toString().contains("[")) {
						names.put(type.toString().substring(0, type.toString().length() - 2), true);
					} else {
						names.put(type.toString(), false);
					}
				}
			}

		}
		return names;
	}

}
