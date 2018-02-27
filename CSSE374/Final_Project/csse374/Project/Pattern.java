package csse374.Project;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import soot.SootClass;

public class Pattern {
	ListMultimap<String, SootClass> map;
	ListMultimap<String, RelationObject> relations;
	String type;
	
	public Pattern(String type){
		map = ArrayListMultimap.create();
		relations = ArrayListMultimap.create();
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Collection<RelationObject> getRelations(String s) {
		return this.relations.get(s);
	}
	
	public Collection<SootClass> getComponent(String s) {
		return this.map.get(s);
	}
	
	public void addRelation(String s, RelationObject r) {
		this.relations.put(s, r);
	}
	
	public void addComponent(String s, SootClass sc) {
		this.map.put(s, sc);
	}
	
	public Collection<SootClass> getClasses() {
		return this.map.values();
	}
	
	public Collection<RelationObject> getRelations() {
		return this.relations.values();
	}
}
