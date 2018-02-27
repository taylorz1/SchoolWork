package csse374.analyzers;

import java.util.HashSet;
import java.util.Set;

import csse374.Project.Enums;
import csse374.Project.Pattern;
import csse374.Project.RelationObject;
import csse374.Project.Enums.Enum;
import soot.SootClass;
import soot.util.Chain;

public class DependencyInversionDetector extends PatternDetectAnalyzor {
	Set<RelationObject> relation;
	Set<RelationObject> rel;
	
	@Override
	protected void detect() {
		System.out.println();
		System.out.println("Dependency Inversion fam");
		System.out.println();
		
		relation = this.model.getRelations();
		rel = new HashSet<>();
		for(RelationObject r: relation){
			if(r.getType().equals(Enums.Enum.DEPENDENCY)
					|| r.getType().equals(Enums.Enum.DEPENDENCYMANY)){
				rel.add(r);
			}
		}

		for(RelationObject r: rel){
			try {
				if(violate(r)){
					Pattern pattern = new Pattern("depinv");
					pattern.addRelation("depinv", r);
					model.addPattern(pattern);
				}
			} catch (Exception e) {
				continue;
			}
		}
		
	}

	private boolean violate(RelationObject r) {
		SootClass to = this.model.getScene().getSootClass(r.getFrom());
		if(!to.isAbstract() && (to.hasSuperclass() || to.getInterfaceCount()!=0)){
			return true;
		}		
		return false;
	}

}
