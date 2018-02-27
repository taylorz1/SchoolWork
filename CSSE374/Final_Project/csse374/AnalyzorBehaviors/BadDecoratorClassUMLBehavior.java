package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import csse374.Project.Model;
import csse374.Project.Pattern;
import csse374.Project.PatternStore;
import soot.SootClass;
import soot.SootMethod;

public class BadDecoratorClassUMLBehavior extends UMLBehaviorTemplate {
	private boolean baddec;

	@Override
	protected void afterMethodBlock(Model model, OutputStream os) {
		Collection<SootMethod> methods = model.getModelElement("baddecoratormethods", SootMethod.class);
		if(baddec){
			try {
				for (SootMethod m : methods) {
					beforeMethodSpecial(model, m, os);
					atMethod(model, m, os);
					afterMethod(model, m, os);
				}	
			} catch (Exception e) {
				throw new RuntimeException("caught an IO exception while performing method printing in BadDecorator");
			}
		} 
		baddec = false;
	}

	protected void beforeMethodSpecial(Model model, SootMethod m, OutputStream os) throws IOException {
		if (m.isPrivate()) {
			os.write('-');
		} else if (m.isProtected()) {
			os.write('#');
		} else {
			os.write('+');
		}
		os.write(' ');
		os.write(("<color:red> ").getBytes());
		if (m.isAbstract()) {
			os.write("{abstract} ".getBytes());
		}
		if (m.isStatic()) {
			os.write("{static} ".getBytes());
		}
	}
	@Override
	protected void beforeMethodBlock(Model model, OutputStream os) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeClassBlock(Model model, OutputStream os) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterClassBlock(Model model, OutputStream os) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void atClass(Model model, SootClass c, OutputStream os) throws IOException {
		PatternStore patternStore = model.getPatternStore();
		List<Pattern> pats = patternStore.getPattern("baddecorator");
		boolean baddecorator = false;
		boolean component = false;
		for(Pattern p : pats) {
			if(p.getClasses().contains(c)) {
				if(p.getComponent("baddecorator").contains(c)) {
					baddecorator = true;
					baddec = true;
				} else if (p.getComponent("component").contains(c)) {
					component = true;
				} else {
					throw new RuntimeException("Unable to match component to given adapter class");
				}
				break;
			}
		}
		if(baddecorator) {
			os.write(("class " +c.getName() + " <<Bad Decorator>> ").getBytes());
		} else if (component) {
			os.write(("class " +c.getName() + " <<Component>> ").getBytes());
		} else {
			throw new RuntimeException("No adapter boolean set!");
		}
	}

	@Override
	protected void afterClass(Model model, SootClass c, OutputStream os) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
