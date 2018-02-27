package csse374.Project;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import soot.SootClass;

public class PatternStore {
	List<Pattern> patterns;

	public PatternStore() {
		patterns = new LinkedList<>();
	}
	
	public void addPattern(Pattern p) {
		this.patterns.add(p);
	}

	public String[] inPattern(SootClass sc) {
		StringBuilder b = new StringBuilder();
		for (Pattern p : patterns) {
			if (p.getClasses().contains(sc)) {
				b.append(p.getType()+",");
			}
		}
		if (b.length() == 0) {
			b.append("default");
		}
		return b.toString().split(",");
	}

	public String[] inPattern(RelationObject r) {
		StringBuilder b = new StringBuilder();
		for (Pattern p : patterns) {
			if (p.getRelations().contains(r)) {
				b.append(p.getType()+",");
			}
		}
		if (b.length() == 0) {
			b.append("default");
		}
		return b.toString().split(",");
	}

	public List<Pattern> getPattern(String name) {
		return this.patterns.stream().filter(em -> em.type.equals(name))
				.collect(Collectors.toList());
	}
}
