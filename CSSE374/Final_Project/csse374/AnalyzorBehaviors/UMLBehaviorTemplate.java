package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import csse374.Project.IFilter;
import csse374.Project.Model;
import csse374.Project.Pattern;
import csse374.Project.RelationObject;
import edu.rosehulman.jvm.sigevaluator.FieldEvaluator;
import edu.rosehulman.jvm.sigevaluator.GenericType;
import edu.rosehulman.jvm.sigevaluator.MethodEvaluator;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.tagkit.Tag;

public abstract class UMLBehaviorTemplate {

	protected Set<RelationObject> relations;
	protected Set<SootClass> sootClass;
	protected Collection<Pattern> patterns;
	protected List<IFilter> filters;

	public void umlClassAnalyze(OutputStream os, Model model, List<IFilter> filters, SootClass c) throws IOException {
		this.filters = filters;
		this.relations = model.getRelations();
		sootClass = model.getSootClass();
		if (sootClass.contains(c)) {
			boolean toFilter = false;
			for (IFilter filter : filters) {
				if (filter.filter(c)) {
					toFilter = true;
				}
			}
			if (!toFilter) {
				beforeClassBlock(model, os);
				beforeClass(model, c, os);
				atClass(model, c, os);
				os.write(" {\n".getBytes());
				for (SootField f : c.getFields()) {
					toFilter = false;
					for (IFilter filter : filters) {
						if (filter.filter(f)) {
							toFilter = true;
						}
					}
					if (!toFilter) {
						beforeField(model, f, os);
						atField(model, f, os);
						afterField(model, f, os);
					}
				}
				beforeMethodBlock(model, os);
				for (SootMethod m : c.getMethods()) {
					if (!m.getName().equals("<init>") && !m.getName().equals("<clinit>")) {
						if (m.getName().contains("$")) {
							continue;
						}
						toFilter = false;
						for (IFilter filter : filters) {
							if (filter.filter(m)) {
								toFilter = true;
							}
						}
						if (!toFilter) {
							beforeMethod(model, m, os);
							atMethod(model, m, os);
							afterMethod(model, m, os);
						}
					}
				}
				afterMethodBlock(model, os);
				afterClass(model, c, os);
				os.write("}\n".getBytes());
				afterClassBlock(model, os);
			}
		}
	}

	abstract protected void afterMethodBlock(Model model, OutputStream os);
	abstract protected void beforeMethodBlock(Model model, OutputStream os);

	abstract protected void beforeClassBlock(Model model, OutputStream os) throws IOException;

	abstract protected void afterClassBlock(Model model, OutputStream os) throws IOException;

	private String getShortName(String name) {
		String[] tokens = name.split("\\.");
		return tokens[tokens.length - 1];
	}

	private String getFieldGeneric(SootField f) {
		Tag signaturetag = f.getTag("SignatureTag");
		if (signaturetag != null) {
			try {
				String signature = signaturetag.toString();
				FieldEvaluator evaluator = new FieldEvaluator(signature);
				GenericType fieldType = evaluator.getType();
				return fieldType.toString();
			} catch (IllegalStateException e) {
				return getShortName(f.getType().toString());
			}
		}
		return getShortName(f.getType().toString());
	}

	private String getMethodParamGeneric(SootMethod m) {
		Tag signaturetag = m.getTag("SignatureTag");
		if (signaturetag != null) {
			String signature = signaturetag.toString();
			MethodEvaluator evaluator = new MethodEvaluator(signature);
			try {
				List<GenericType> types = evaluator.getParameterTypes();

				String output = "(";
				for (int i = 0; i < types.size(); i++) {
					output += shortGeneric(types.get(i).toString());
					if (i != types.size() - 1) {
						output += ",";
					}
				}
				return output + ")";

			} catch (Exception e) {
				List<Type> types = m.getParameterTypes();
				String output = "(";
				for (int i = 0; i < types.size(); i++) {
					output += getShortName(types.get(i).toString());
					if (i != types.size() - 1) {
						output += ",";
					}
				}
				return output + ")";
			}
		}
		List<Type> types = m.getParameterTypes();
		String output = "(";
		for (int i = 0; i < types.size(); i++) {
			output += getShortName(types.get(i).toString());
			if (i != types.size() - 1) {
				output += ",";
			}
		}
		return output + ")";
	}

	private String getMethodReturnGeneric(SootMethod m) {
		Tag signaturetag = m.getTag("SignatureTag");
		if (signaturetag != null) {
			String signature = signaturetag.toString();
			MethodEvaluator evaluator = new MethodEvaluator(signature);
			try {
				GenericType returntype = evaluator.getReturnType();
				return shortGeneric(returntype.toString());
			} catch (Exception e) {
				getShortName(m.getReturnType().toString());
			}
		}
		return getShortName(m.getReturnType().toString());
	}

	private String shortGeneric(String gen) {
		if (gen.contains("<")) {
			String[] tokens = gen.split("<");
			String[] outer = tokens[0].split("\\.");
			String[] inner = tokens[1].split(",");

			String output = getShortName(tokens[0]) + "<";

			for (int i = 0; i < inner.length; i++) {
				if (i != inner.length - 1) {
					output += getShortName(inner[i]) + ",";
				} else {
					output += getShortName(inner[i]);
				}
			}
			return output;
		} else {
			return getShortName(gen);
		}
	}

	protected void beforeClass(Model model, SootClass c, OutputStream os) throws IOException {
		if (c.isAbstract() && !c.isInterface()) {
			os.write("abstract ".getBytes());
		}
		if (c.isStatic()) {
			os.write("static ".getBytes());
		}
	}

	protected void atClass(Model model, SootClass c, OutputStream os) throws IOException {
		if (c.isInterface()) {
			os.write("interface ".getBytes());
		} else {
			os.write("class ".getBytes());
		}
		os.write((c.getName() + " ").getBytes());
	}

	abstract protected void afterClass(Model model, SootClass c, OutputStream os) throws IOException;

	protected void beforeField(Model model, SootField f, OutputStream os) throws IOException {
		if (f.isPrivate()) {
			os.write('-');
		} else if (f.isProtected()) {
			os.write('#');
		} else {
			os.write('+');
		}
		os.write(' ');
		if (f.isStatic()) {
			os.write("static ".getBytes());
		}
		String generic;
		generic = getFieldGeneric(f);
		if (!generic.equals("")) {
			os.write(shortGeneric(generic).getBytes());
		}
	}

	protected void atField(Model model, SootField f, OutputStream os) throws IOException {
		os.write((' ' + f.getName()).getBytes());
	}

	protected void afterField(Model model, SootField f, OutputStream os) throws IOException {
		os.write('\n');

	}

	protected void beforeMethod(Model model, SootMethod m, OutputStream os) throws IOException {
		if (m.isPrivate()) {
			os.write('-');
		} else if (m.isProtected()) {
			os.write('#');
		} else {
			os.write('+');
		}
		os.write(' ');
		if (m.isAbstract()) {
			os.write("{abstract} ".getBytes());
		}
		if (m.isStatic()) {
			os.write("{static} ".getBytes());
		}
	}

	protected void atMethod(Model model, SootMethod m, OutputStream os) throws IOException {
		os.write(getMethodReturnGeneric(m).getBytes());
		os.write(' ');
		os.write(m.getName().getBytes());
		os.write(getMethodParamGeneric(m).getBytes());
	}

	protected void afterMethod(Model model, SootMethod m, OutputStream os) throws IOException {
		os.write('\n');
	}
}
