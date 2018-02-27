package csse374.analyzers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import csse374.Project.Enums;
import csse374.Project.Pattern;
import csse374.Project.RelationObject;
import edu.rosehulman.jvm.sigevaluator.GenericType;
import edu.rosehulman.jvm.sigevaluator.MethodEvaluator;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.tagkit.Tag;

public class BadDecoratorDetector extends PatternDetectAnalyzor {

	Set<RelationObject> relation;
	Set<SootClass> extraDecorator;
	RelationObject extraRelation;
	Pattern pattern;

	@Override
	protected void detect() {
		System.out.println();
		System.out.println("Decorator fam");
		System.out.println();

		extraDecorator = new HashSet<>();
		relation = this.model.getRelations();
		Set<RelationObject> rel = new HashSet<>();
		for (RelationObject r : relation) {
			if (r.getType().equals(Enums.Enum.SUPERCLASS) || r.getType().equals(Enums.Enum.INTERFACE)) {
				rel.add(r);
			}
		}

		for (RelationObject r : rel) {
			if (decorates(r)) {
				Pattern pattern = new Pattern("baddecorator");
				SootClass from = model.getScene().getSootClass(r.getFrom());
				SootClass to = model.getScene().getSootClass(r.getTo());
				pattern.addComponent("component", to);
				pattern.addComponent("baddecorator", from);
				for (SootClass c : model.getSootClass()) {
					if (c.getSuperclass().equals(from)) {
						pattern.addComponent("baddecorator", c);
					}
				}
				for (RelationObject allrel : model.getRelations()) {
					if (allrel.getFrom().equals(r.getFrom()) && allrel.getTo().equals(r.getTo())) {
						if (allrel.getType().equals(Enums.Enum.ASSOCIATION)
								|| allrel.getType().equals(Enums.Enum.ASSOCIATIONMANY)) {
							pattern.addRelation("decorates", allrel);
						}
					}
				}

				for (SootClass c : extraDecorator) {
					pattern.addComponent("baddecorator", c);
				}
				System.out.println("Ro: " + extraRelation);
				pattern.addRelation("decorates", extraRelation);
				model.addPattern(pattern);
			}
		}
	}

	private boolean decorates(RelationObject r) {
		SootClass from = model.getScene().getSootClass(r.getFrom());
		SootClass to = model.getScene().getSootClass(r.getTo());
		for (RelationObject ro : relation) {
			if (ro.getFrom().equals(r.getFrom()) && ro.getTo().equals(r.getTo())) {
				if (ro.getType().equals(Enums.Enum.ASSOCIATION)) {
					System.out.println("\n CHECK \n");
					return checkMethod(from, to);
				}
			}
			if (ro.getType().equals(Enums.Enum.ASSOCIATION) && ro.getFrom().equals(r.getFrom())) {
				if (findSuperclass(ro.getTo(), r.getTo()) || findSuperclassReverse(ro.getTo(), r.getTo())) {
					extraDecorator.add(model.getScene().getSootClass(ro.getTo()));
					extraRelation = ro;
					System.out.println("\n CHECK \n");
					return checkMethod(from, to);
				}
			}
		}

		return false;
	}

	private boolean checkMethod(SootClass from, SootClass to) {
		List<SootMethod> methods = to.getMethods();
		List<String> methodsInSuper = new ArrayList<>();
		for (SootMethod m : methods) {
			if (!m.isPrivate() && !m.getName().contains("<init>")) {
				System.out.println(m.getName() + " " + getMethodReturnGeneric(m) + " " + getMethodParamGeneric(m));
				methodsInSuper.add(m.getName() + " " + getMethodReturnGeneric(m) + " " + getMethodParamGeneric(m));
			}
		}
		List<SootMethod> methods1 = from.getMethods();
		List<String> methodsInSub = new ArrayList<>();
		System.out.println("\n\n\n");
		for (SootMethod m : methods1) {
			if (!m.isPrivate() && !m.getName().contains("<init>")) {
				System.out.println(m.getName() + " " + getMethodReturnGeneric(m) + " " + getMethodParamGeneric(m));
				methodsInSub.add(m.getName() + " " + getMethodReturnGeneric(m) + " " + getMethodParamGeneric(m));
			}
		}

		for (String str : methodsInSub) {
			if (!methodsInSuper.contains(str)) {
				return false;
			}
		}
		for (int i = 0; i<methodsInSuper.size(); i++) {
			if(!methodsInSub.contains(methods.get(i))) {
				this.model.addModelElement("baddecoratormethods", methods.get(i));
			}
		}
		return true;

	}

	private boolean findSuperclass(String sub, String sup) {
		if (model.getScene().getSootClass(sub).hasSuperclass()
				|| model.getScene().getSootClass(sub).getInterfaceCount() != 0) {
			if (sub != sup) {
				return findSuperclass(model.getScene().getSootClass(sub).getSuperclass().getName(), sup);
			}
		}
		return sub == sup;

	}
	
	private boolean findSuperclassReverse(String sub, String sup) {
		if (model.getScene().getSootClass(sup).hasSuperclass()
				|| model.getScene().getSootClass(sup).getInterfaceCount() != 0) {
			if (sub != sup) {
				return findSuperclass(sub,model.getScene().getSootClass(sup).getSuperclass().getName());
			}
		}
		return sub == sup;

	}

	private String getMethodParamGeneric(SootMethod m) {
		Tag signaturetag = m.getTag("SignatureTag");
		if (signaturetag != null) {
			String signature = signaturetag.toString();
			MethodEvaluator evaluator = new MethodEvaluator(signature);
			try {
				List<GenericType> types = evaluator.getParameterTypes();

				String output = "(";
				for (int i = 0; i < types.size(); i++) {
					output += shortGeneric(types.get(i).toString());
					if (i != types.size() - 1) {
						output += ",";
					}
				}
				return output + ")";

			} catch (Exception e) {
				List<Type> types = m.getParameterTypes();
				String output = "(";
				for (int i = 0; i < types.size(); i++) {
					output += types.get(i).toString();
					if (i != types.size() - 1) {
						output += ",";
					}
				}
				return output + ")";
			}
		}
		List<Type> types = m.getParameterTypes();
		String output = "(";
		for (int i = 0; i < types.size(); i++) {
			output += types.get(i).toString();
			if (i != types.size() - 1) {
				output += ",";
			}
		}
		return output + ")";
	}

	private String getMethodReturnGeneric(SootMethod m) {
		Tag signaturetag = m.getTag("SignatureTag");
		if (signaturetag != null) {
			String signature = signaturetag.toString();
			MethodEvaluator evaluator = new MethodEvaluator(signature);
			try {
				GenericType returntype = evaluator.getReturnType();
				return shortGeneric(returntype.toString());
			} catch (Exception e) {
				m.getReturnType().toString();
			}
		}
		return m.getReturnType().toString();
	}

	private String shortGeneric(String gen) {
		if (gen.contains("<")) {
			String[] tokens = gen.split("<");
			String[] outer = tokens[0].split("\\.");
			String[] inner = tokens[1].split(",");

			String output = tokens[0] + "<";

			for (int i = 0; i < inner.length; i++) {
				if (i != inner.length - 1) {
					output += inner[i] + ",";
				} else {
					output += inner[i];
				}
			}
			return output;
		} else {
			return gen;
		}
	}
}
