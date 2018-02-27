package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

import csse374.Project.Enums;
import csse374.Project.IFilter;
import csse374.Project.Model;
import csse374.Project.RelationObject;
import soot.SootClass;

public abstract class UMLRelationBehaviorTemplate {
	
	private List<IFilter> filters;

	public void umlRelationAnalyze(OutputStream os, Model model, List<IFilter> filters, RelationObject r) throws IOException {
		this.filters = filters;
		beforeRelation(model, r, os);
		atRelation(model, r, os);
		afterRelation(model, r, os);
		
	}
	
	abstract protected void beforeRelation(Model model, RelationObject r, OutputStream os) throws IOException;

	protected void atRelation(Model model, RelationObject r, OutputStream os) throws IOException {
		Enum<csse374.Project.Enums.Enum> type = r.getType();
		if (type.equals(Enums.Enum.ASSOCIATION)) {
			os.write((r.getFrom() + " --> " + r.getTo()).getBytes());
		} else if (type.equals(Enums.Enum.ASSOCIATIONMANY)) {
			os.write((r.getFrom() + " --> \"1 .. *\" " + r.getTo()).getBytes());
		} else if (type.equals(Enums.Enum.DEPENDENCY)) {
			os.write((r.getFrom() + " ..> " + r.getTo()).getBytes());
		} else if (type.equals(Enums.Enum.DEPENDENCYMANY)) {
			os.write((r.getFrom() + " ..> \"1 .. *\" " + r.getTo()).getBytes());
		} else if (type.equals(Enums.Enum.INTERFACE)) {
			os.write((r.getFrom() + "..|> " + r.getTo()).getBytes());
		} else if (type.equals(Enums.Enum.SUPERCLASS)) {
			os.write((r.getFrom() + "--|> " + r.getTo()).getBytes());
		} else {
			throw new RuntimeException("Undefined relationship type");
		}
	}

	protected void afterRelation(Model model, RelationObject r, OutputStream os) throws IOException {
		os.write('\n');
	}
}
