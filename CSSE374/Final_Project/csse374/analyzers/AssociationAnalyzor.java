package csse374.analyzers;

import java.awt.Window.Type;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import csse374.Project.Enums;
import csse374.Project.Model;
import csse374.Project.RelationObject;
import edu.rosehulman.jvm.sigevaluator.FieldEvaluator;
import edu.rosehulman.jvm.sigevaluator.GenericType;
import soot.SootClass;
import soot.SootField;
import soot.tagkit.Tag;

public class AssociationAnalyzor extends AbstractAnalyzor {

	private Set<SootClass> sootclass;

	@Override
	public void analyze(Model model) {
		System.out.println();
		
		sootclass = model.getSootClass();
		for (SootClass s : sootclass) {
			List<RelationObject> r = getAssociation(s);
			for (RelationObject obj : r) {
				if(!model.checkRelation(obj)){
					System.out.println("checkrelation: "+model.checkRelation(obj));
					System.out.println(obj);
					model.addRelation(obj);
				}
			}
		}
	}

	private List<RelationObject> getAssociation(SootClass c) {
		List<RelationObject> rel = new ArrayList<>();
		for (SootField f : c.getFields()) {
			if (f.getTag("SignatureTag") != null) {
				try{
					for (String str : getFieldGeneric(f)) {
						if (sootContains(str)) {
							rel.add(new RelationObject(c.getName(), str, Enums.Enum.ASSOCIATIONMANY));
						}
					}
				}catch (IllegalStateException e) {
					String str = f.getType().toString();
					if (sootContains(str)) {
						if (str.contains("[")) {
							rel.add(new RelationObject(c.getName(), str.substring(0,str.length()-2), Enums.Enum.ASSOCIATIONMANY));
						} else {
							rel.add(new RelationObject(c.getName(), str, Enums.Enum.ASSOCIATION));
						}
					}
				}

			} else {
				String str = f.getType().toString();
				if (sootContains(str)) {
					if (str.contains("[")) {
						rel.add(new RelationObject(c.getName(), str.substring(0,str.length()-2), Enums.Enum.ASSOCIATIONMANY));
					} else {
						rel.add(new RelationObject(c.getName(), str, Enums.Enum.ASSOCIATION));
					}
				}
			}

		}
		return rel;
	}

	private boolean sootContains(String name) {
		for (SootClass c : sootclass) {
			if (c.getName().equals(name) || (name.contains("[") && c.getName().equals(name.substring(0,name.length()-2)))) {
				return true;
			}
		}
		return false;
	}

	private List<String> getFieldGeneric(SootField f) {
		Tag signaturetag = f.getTag("SignatureTag");
		String signature = signaturetag.toString();
		FieldEvaluator evaluator = new FieldEvaluator(signature);
		GenericType fieldType = evaluator.getType();
		String field = fieldType.toString();
		String[] fields = field.split("<");
		fields = fields[1].split(",");
		fields[fields.length - 1] = fields[fields.length - 1].substring(0, fields[fields.length - 1].length() - 1);
		return Arrays.asList(fields);
	}
}
