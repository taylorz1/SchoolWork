package csse374.Project;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.relation.Relation;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;

import csse374.analyzers.AbstractAnalyzor;
import polyglot.ast.New;
import soot.Scene;
import soot.SootClass;
import soot.toolkits.graph.UnitGraph;

public class Model {

	private List<AbstractAnalyzor> config;

	private ListMultimap<String, Object> modelElements;

	public Model(Map<String, String> configMap, List<AbstractAnalyzor> analyzors) {
		this.modelElements = LinkedListMultimap.create();
		this.config = analyzors;

		// Flatten the configMap into bare elements, hereby String,String
		for (String e : configMap.keySet()) {
			if (configMap.get(e) == null) {
				continue;
			} else {
				modelElements.put(e, configMap.get(e));
			}
		}
		this.setPatternStore(new PatternStore());
	}

	public <T extends Object> Collection<T> getModelElement(String element, Class<T> filterType) {
		return this.modelElements.get(element).stream().filter(me -> filterType.isAssignableFrom(me.getClass()))
				.map(me -> filterType.cast(me)).collect(Collectors.toList());
	}

	public <T extends Object> T getSingleElement(String name, Class<T> filterType) {
		List<T> l = this.modelElements.get(name).stream().filter(ls -> filterType.isAssignableFrom(ls.getClass()))
				.map(me -> filterType.cast(me)).collect(Collectors.toList());
		if (l.size() == 0) {
			return null;
		} else {
			return l.get(0);
		}
	}

	public void addModelElement(String key, Object s) {
		this.modelElements.put(key, s);
	}

	private void removeModelElement(String key, Object s) {
		this.modelElements.remove(key, s);
	}

	public String getPath() {
		if (getSingleElement("-dir", String.class) == null) {
			return getSingleElement("-sigdir", String.class);
		}
		return getSingleElement("-dir", String.class);
	}

	public String getMainClass() {
		return getSingleElement("-main", String.class);
	}

	public String getClasses() {
		return getSingleElement("-classes", String.class);
	}

	public String getDemo() {
		return getSingleElement("-demo", String.class);
	}

	public void addSootClass(SootClass clazz) {
		this.addModelElement("sootClass", clazz);
	}

	public void setScene(Scene s) {
		this.addModelElement("scene", s);
	}

	public Scene getScene() {
		return this.getSingleElement("scene", Scene.class);
	}
	
	public void addPattern(Pattern p) {
		PatternStore pat = this.getPatternStore();
		pat.addPattern(p);
		
	}
	
	public PatternStore getPatternStore() {
		return this.getSingleElement("patternstore", PatternStore.class);
	}
	
	public void setPatternStore(PatternStore p) {
		this.addModelElement("patternstore", p);
	}

	public void execute() throws IOException {
		for (int i = 0; i < config.size(); i++) {
			config.get(i).analyze(this);
		}
	}

	// These methods force them to become sets.
	public Set<SootClass> getSootClass() {
		Set<SootClass> toReturn = new HashSet<>();
		Collection<SootClass> l = this.getModelElement("sootClass", SootClass.class);
		toReturn.addAll(l);
		return toReturn;
	}

	public void addRelation(RelationObject r) {
		this.addModelElement("relations", r);
	}

	public boolean checkRelation(RelationObject r) {
		if (r.getType().equals(Enums.Enum.ASSOCIATION)) {
			RelationObject object = new RelationObject(r.getFrom(), r.getTo(), Enums.Enum.ASSOCIATIONMANY);
			if(this.modelElements.containsValue(r) || this.modelElements.containsValue(object)){
				return true;
			}
			return false;
		} else if (r.getType().equals(Enums.Enum.ASSOCIATIONMANY)) {
			RelationObject object = new RelationObject(r.getFrom(), r.getTo(), Enums.Enum.ASSOCIATION);
			if(this.modelElements.containsValue(r) ){
				return true;
			}else if(this.modelElements.containsValue(object)){
				removeRelation(object);
			}
			return false;

		} else if (r.getType().equals(Enums.Enum.DEPENDENCY)) {
			RelationObject object = new RelationObject(r.getFrom(), r.getTo(), Enums.Enum.DEPENDENCYMANY);
			if(this.modelElements.containsValue(r) || this.modelElements.containsValue(object)){
				return true;
			}
			return false;
		} else if (r.getType().equals(Enums.Enum.DEPENDENCYMANY)) {
			RelationObject object = new RelationObject(r.getFrom(), r.getTo(), Enums.Enum.DEPENDENCY);
			if(this.modelElements.containsValue(r) ){
				return true;
			}else if(this.modelElements.containsValue(object)){
				removeRelation(object);
			}
			return false;
		} else {
			return this.modelElements.containsValue(r);
		}

	}

	public void removeRelation(RelationObject r) {
		this.removeModelElement("relations", r);
	}

	public Set<RelationObject> getRelations() {
		Set<RelationObject> toReturn = new HashSet<>();
		Collection<RelationObject> r = this.getModelElement("relations", RelationObject.class);
		toReturn.addAll(r);
		return toReturn;
	}

	public String getMethodSignature() {
		return this.getSingleElement("-sig", String.class);
	}

	public void setUnitGraph(UnitGraph cfg) {
		this.addModelElement("unitgraph", cfg);
	}

	public UnitGraph getUnitGraph() {
		return this.getSingleElement("unitgraph", UnitGraph.class);
	}

	public String getDepth() {
		return this.getSingleElement("-depth", String.class);
	}

}
