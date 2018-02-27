package csse374.analyzers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import csse374.Project.Enums;
import csse374.Project.Pattern;
import csse374.Project.RelationObject;
import soot.Body;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.util.Chain;

public class AdaptorDetector extends PatternDetectAnalyzor {
	Set<RelationObject> relation;
	SootClass targetClass;
	RelationObject target;

	@Override
	protected void detect() {
		System.out.println();
		System.out.println("Adaptor fam");
		System.out.println();
		relation = this.model.getRelations();
		Set<RelationObject> rel = new HashSet<>();
		for (RelationObject r : relation) {
			if (r.getType().equals(Enums.Enum.SUPERCLASS) || r.getType().equals(Enums.Enum.INTERFACE)) {
				rel.add(r);
			}
		}

		for (RelationObject r : rel) {
			if (adapte(r)) {
				SootClass from = model.getScene().getSootClass(r.getFrom());
				SootClass to = model.getScene().getSootClass(r.getTo());
				Pattern pattern = new Pattern("adapter");
				pattern.addComponent("adapter", from);
				pattern.addComponent("target", to);
				pattern.addComponent("adaptee", targetClass);
				System.out.println(target);
				System.out.println(r);
				pattern.addRelation("adapter", target);
				pattern.addRelation("target", r);
				model.addPattern(pattern);
			}
		}

	}

	private boolean adapte(RelationObject r) {
		SootClass from = model.getScene().getSootClass(r.getFrom());
		SootClass to = model.getScene().getSootClass(r.getTo());

		System.out.println("From: "+from.getName());
		System.out.println("To: "+to.getName());
		System.out.println();

		if (from.getFieldCount() == 0) {
			return false;
		}

		System.out.println("FieldCount not zero");

		Chain<SootField> fields = from.getFields();
		for (SootField f : fields) {
			ArrayList<Integer> count = new ArrayList<>();
			ArrayList<SootClass> targetCandidates = new ArrayList<>();
			for (SootMethod m : from.getMethods()) {
				try {
					if (to.getMethod(m.getName(), m.getParameterTypes()) != null) {
						Body body = m.retrieveActiveBody();
						UnitGraph cfg = new ExceptionalUnitGraph(body);
						ArrayList<Integer> flag = new ArrayList<>();
						cfg.forEach(stmt -> {
							if (stmt instanceof InvokeStmt) {
								InvokeExpr expr = ((InvokeStmt) stmt).getInvokeExpr();
								if (expr.getMethod().getDeclaringClass().getName().equals(f.getType().toString())) {
									if (flag.size() == 0) {
										targetCandidates.add(expr.getMethod().getDeclaringClass());
										count.add(1);
										flag.add(1);
									}
								}
							}
							if (stmt instanceof AssignStmt) {
								Value rightOp = ((AssignStmt) stmt).getRightOp();
								if (rightOp instanceof InvokeExpr) {
									InvokeExpr expr = (InvokeExpr)rightOp;
									if (expr.getMethod().getDeclaringClass().getName().equals(f.getType().toString())) {
										if (flag.size() == 0) {
											count.add(1);
											flag.add(1);
											targetCandidates.add(expr.getMethod().getDeclaringClass());
										}
									}								}

							}
						});
					}
				} catch (Exception e) {
					continue;
				}

			}

			System.out.println(count.size());
			System.out.println(to.getMethodCount());
			System.out.println(count.size()*1.0 / to.getMethodCount());
			System.out.println(count.size() / to.getMethodCount() * 1.0 >= 0.3);
			if(!targetCandidates.isEmpty()){
			System.out.println(targetCandidates.get(0).getName());
			}

			if (count.size()*1.0 / to.getMethodCount() >= 0.3) {
				System.out.println("hello");
				targetClass = model.getScene().getSootClass(f.getType().toString());
				System.out.println(targetClass.getName());
				searchRelation(from);
				return true;
			}
		}
		return false;
	}

	private void searchRelation(SootClass from) {
		for (RelationObject r : relation) {
			if (r.getFrom().equals(from.getName()) && r.getTo().equals(targetClass.getName())) {
				if (r.getType().equals(Enums.Enum.ASSOCIATION) || r.getType().equals(Enums.Enum.ASSOCIATIONMANY)) {
					target = r;
					return;
				}
			}
		}
	}

}
