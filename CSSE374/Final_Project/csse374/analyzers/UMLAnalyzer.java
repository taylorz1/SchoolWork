package csse374.analyzers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import csse374.AnalyzorBehaviors.UMLBehaviorTemplate;
import csse374.AnalyzorBehaviors.UMLRelationBehaviorTemplate;
import csse374.Project.Model;
import csse374.Project.PatternStore;
import csse374.Project.RelationObject;
import soot.SootClass;

public class UMLAnalyzer extends FileAnalyzor {
	private ListMultimap<String, UMLBehaviorTemplate> behaviors = ArrayListMultimap.create();
	private ListMultimap<String, UMLRelationBehaviorTemplate> relBehaviors = ArrayListMultimap.create();

	public UMLAnalyzer(File file) {
		super(file);
	}
	
	public void addClassBehavior(String s, UMLBehaviorTemplate b) {
		this.behaviors.put(s, b);
	}
	
	public void addRelationBehavior(String s, UMLRelationBehaviorTemplate b) {
		this.relBehaviors.put(s, b);
	}

	@Override
	protected void fileAnalyze(Model model, File file) throws IOException {
		OutputStream os = new FileOutputStream(file);
		os.write("@startuml\n".getBytes());
		os.write(("skinparam linetype ortho\n").getBytes());
		
		Set<SootClass> sootclasses = model.getSootClass();
		Set<RelationObject> relations = model.getRelations();
		PatternStore patternStore = model.getPatternStore();
		for (SootClass c : sootclasses) {
			String[] patterns = patternStore.inPattern(c);
			for (String s : patterns) {
				List<UMLBehaviorTemplate> behavior = this.behaviors.get(s);
				for(UMLBehaviorTemplate b : behavior) {
					b.umlClassAnalyze(os, model, filters, c);
				}
			}
		}
		for (RelationObject r : relations) {
			String[] patterns = patternStore.inPattern(r);
			for (String s : patterns) {
				List<UMLRelationBehaviorTemplate> behavior = this.relBehaviors.get(s);
				for(UMLRelationBehaviorTemplate b : behavior) {
					b.umlRelationAnalyze(os, model, filters, r);
				}
			}
		}
		os.write("@enduml".getBytes());
		os.close();
	}
}
