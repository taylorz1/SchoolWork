package csse374.analyzers;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import csse374.Project.Enums;
import csse374.Project.Pattern;
import csse374.Project.RelationObject;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

public class SingletonDetector extends PatternDetectAnalyzor {

	List<String> superClasses = new LinkedList<>();

	@Override
	protected void detect() {
		Set<SootClass> sc = this.model.getSootClass();
		for (SootClass clazz : sc) {
			buildSuperClass(superClasses, clazz);
			if (hasPrivateConstructor(clazz)) {
				if (hasStaticInstanceMethod(clazz)) {
					if (hasField(clazz)) {
						Pattern p = new Pattern("singleton");
						p.addComponent("singleton", clazz);
						model.addPattern(p);
					}
				}
			}
			superClasses.clear();
		}
	}

	private boolean hasField(SootClass clazz) {
		for (SootField sf : clazz.getFields()) {
			if (matchesAnySuperType(sf.getType().getEscapedName()) && sf.isPrivate() && sf.isStatic()) {
				return true;
			}
		}
		return false;
	}

	private void buildSuperClass(List<String> superClasses2, SootClass clazz) {
		superClasses2.add(clazz.toString());
		Set<RelationObject> rl = this.model.getRelations();
		for (RelationObject r : rl) {
			if(r.getFrom().equals(clazz.getName())) {
				if(r.getType().equals(Enums.Enum.SUPERCLASS)) {
					superClasses2.add(r.getTo());
				}
			}
		}
	}

	private boolean hasStaticInstanceMethod(SootClass clazz) {
		List<SootMethod> sm = clazz.getMethods();
		for (SootMethod s : sm) {
			if (s.isStatic() && !s.isPrivate()) {
				if (matchesAnySuperType(s.getReturnType().getEscapedName())) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean matchesAnySuperType(String string) {
		for (String s : superClasses) {
			if (s.equals(string)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasPrivateConstructor(SootClass clazz) {
		SootMethod m;
		String s;
		try {
			s = clazz.getMethods().get(0).getSubSignature();
			m = clazz.getMethod("void <init>()");
		} catch (RuntimeException e) {
			return false;
		}
		return m.isPrivate();
	}
}
