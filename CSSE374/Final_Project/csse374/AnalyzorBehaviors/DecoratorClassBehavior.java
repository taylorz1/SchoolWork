package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import csse374.Project.Model;
import csse374.Project.Pattern;
import csse374.Project.PatternStore;
import soot.SootClass;

public class DecoratorClassBehavior extends UMLBehaviorTemplate {
	
	@Override
	protected void atClass(Model model, SootClass c, OutputStream os) throws IOException {
		PatternStore patternStore = model.getPatternStore();
		List<Pattern> pats = patternStore.getPattern("decorator");
		boolean decorator = false;
		boolean component = false;
		for(Pattern p : pats) {
			if(p.getClasses().contains(c)) {
				if(p.getComponent("decorator").contains(c)) {
					decorator = true;
				} else if (p.getComponent("component").contains(c)) {
					component = true;
				} else {
					throw new RuntimeException("Unable to match component to given adapter class");
				}
				break;
			}
		}
		if(decorator) {
			os.write(("class " +c.getName() + " <<decorator>> #green").getBytes());
		} else if (component) {
			os.write(("class " +c.getName() + " <<component>> #green").getBytes());
		} else {
			throw new RuntimeException("No adapter boolean set!");
		}
	}

	@Override
	protected void afterMethodBlock(Model model, OutputStream os) {
		// TODO Auto-generated method stub

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
	protected void afterClass(Model model, SootClass c, OutputStream os) throws IOException {
		// TODO Auto-generated method stub

	}

}
