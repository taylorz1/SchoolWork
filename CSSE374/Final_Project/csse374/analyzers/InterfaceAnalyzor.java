package csse374.analyzers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import csse374.Project.Enums;
import csse374.Project.Model;
import csse374.Project.RelationObject;
import csse374.Project.Enums.Enum;

import h.same_t;
import soot.SootClass;

public class InterfaceAnalyzor extends AbstractAnalyzor {
	private Set<SootClass> sootclass;

	@Override
	public void analyze(Model model) {
		sootclass = model.getSootClass();
		for(SootClass s: sootclass){
			Set<RelationObject> relations = getInterface(s);
			for(RelationObject r:relations){
				if(!model.checkRelation(r)){
					model.addRelation(r);
				}
			}
		}
	}
	
	private Set<RelationObject> getInterface(SootClass c){
		Set<RelationObject> relations = new HashSet<>();
		Collection<SootClass> interfaces = c.getInterfaces();
		for(SootClass klazz: interfaces){
			System.out.println("interfaces "+klazz.getName());
			if(sootclass.contains(klazz)){
				relations.add(new RelationObject(c.getName(), klazz.getName(), Enums.Enum.INTERFACE));
			}
		}
		return relations;
	}

}
