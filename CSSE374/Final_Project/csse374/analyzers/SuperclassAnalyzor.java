package csse374.analyzers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import csse374.Project.Enums;
import csse374.Project.Model;
import csse374.Project.RelationObject;
import csse374.Project.Enums.Enum;
import soot.SootClass;

public class SuperclassAnalyzor extends AbstractAnalyzor {
	private Set<SootClass> sootclass;

	@Override
	public void analyze(Model model) {
		sootclass = model.getSootClass();
		for (SootClass s : sootclass) {
			if (s.hasSuperclass()) {
				RelationObject r = getSupeclass(s);
				if (r != null) {
					if(!model.checkRelation(r)){
						model.addRelation(r);
					}
				}
			}
		}
	}

	private RelationObject getSupeclass(SootClass c) {
		SootClass superclass = c.getSuperclass();
		System.out.println("superclass " + superclass.getName());
		if (sootclass.contains(superclass)) {
			return new RelationObject(c.getName(), superclass.getName(), Enums.Enum.SUPERCLASS);
		}
		return null;
	}

}
