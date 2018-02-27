package csse374.analyzers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.relation.Relation;
import javax.management.relation.RelationService;

import afu.org.checkerframework.common.reflection.qual.Invoke;
import csse374.Project.Enums;
import csse374.Project.Pattern;
import csse374.Project.RelationObject;
import soot.Body;
import soot.SootClass;
import soot.SootMethod;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class FavorCompositionDetector extends PatternDetectAnalyzor {
	Set<RelationObject> relation;

	@Override
	protected void detect() {
		System.out.println();
		System.out.println("Favor comp fam");
		System.out.println();
		relation = this.model.getRelations();
		Set<RelationObject> rel = new HashSet<>();
		for (RelationObject r : relation) {
			if (r.getType().equals(Enums.Enum.SUPERCLASS)) {
				rel.add(r);
			}
		}

		for (RelationObject r : rel) {
			try {
				if (isOrange(r)) {
					// model.removeRelation(r);
					// RelationObject oran = new RelationObject(r.getFrom(),
					// r.getTo(), Enums.Enum.ORANGE);

					Pattern p = new Pattern("favorcomp");
					p.addRelation("favorcomp", r);
					model.addPattern(p);

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
			}
		}

	}

	private boolean isOrange(RelationObject r) throws Exception {
		System.out.println();
		System.out.println(r);
		System.out.println();
		SootClass subclass = this.model.getScene().getSootClass(r.getFrom());
		SootClass superclass = this.model.getScene().getSootClass(r.getTo());
		List<SootMethod> supermethods = superclass.getMethods();
		for (SootMethod m : subclass.getMethods()) {
			if (!m.isAbstract()) {
				for (SootMethod sup : supermethods) {
					if (m.getName().equals(sup.getName()) && !m.getName().equals("<init>")
							&& sup.getReturnType().equals(m.getReturnType())
							&& sup.getParameterTypes().equals(m.getParameterTypes())) {
						if (!sup.isAbstract() && calledSuper(m, sup)) {
							return true;
						}
					}
				}
			}
		}

		if (superclass.hasSuperclass()) {
			return isOrange(
					new RelationObject(r.getFrom(), superclass.getSuperclass().getName(), Enums.Enum.SUPERCLASS));
		}
		return false;
	}

	private boolean calledSuper(SootMethod m, SootMethod sup) throws Exception {
		Body body = m.retrieveActiveBody();
		UnitGraph cfg = new ExceptionalUnitGraph(body);
		ArrayList<Boolean> b = new ArrayList<>();
		cfg.forEach(stmt -> {
			if (stmt instanceof InvokeStmt) {
				InvokeExpr expr = ((InvokeStmt) stmt).getInvokeExpr();
				String[] tokens = stmt.toString().split(" ");
				if (tokens[0].equals("specialinvoke")) {
					String methodName = expr.getMethod().getName();
					String className = expr.getMethod().getDeclaringClass().getName();
					if (methodName.equals(sup.getName()) && className.equals(sup.getDeclaringClass().getName())) {
						b.add(true);
					}
				}
			} else if (stmt instanceof AssignStmt) {
				Value rightOp = ((AssignStmt) stmt).getRightOp();
				String[] tokens = stmt.toString().split(" ");
				if (rightOp instanceof InvokeExpr) {
					if (tokens[2].equals("specialinvoke")) {
						String methodName = ((InvokeExpr) rightOp).getMethod().getName();
						String className = ((InvokeExpr) rightOp).getMethod().getDeclaringClass().getName();
						if (methodName.equals(sup.getName()) && className.equals(sup.getDeclaringClass().getName())) {
							b.add(true);
						}
					}
				}
			}
		});
		if (b.isEmpty()) {
			return false;
		}
		return true;
	}

}
