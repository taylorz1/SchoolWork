package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Target;
import java.util.List;

import csse374.Project.Model;
import csse374.Project.Pattern;
import csse374.Project.PatternStore;
import soot.SootClass;

public class AdapterClassBehavior extends UMLBehaviorTemplate {
	
	
	@Override
	protected void atClass(Model model, SootClass c, OutputStream os) throws IOException {
		PatternStore patternStore = model.getPatternStore();
		List<Pattern> pats = patternStore.getPattern("adapter");
		boolean target = false;
		boolean adapter = false;
		boolean adaptee = false;
		for(Pattern p : pats) {
			if(p.getClasses().contains(c)) {
				if(p.getComponent("target").contains(c)) {
					target = true;
				} else if (p.getComponent("adaptee").contains(c)) {
					adaptee = true;
				} else if (p.getComponent("adapter").contains(c)) {
					adapter = true;
				} else {
					throw new RuntimeException("Unable to match component to given adapter class");
				}
				break;
			}
		}
		if(target) {
			os.write(("class " +c.getName() + " <<target>> #red").getBytes());
		} else if (adapter) {
			os.write(("class " +c.getName() + " <<adapter>> #red").getBytes());
			
		} else if (adaptee) {
			os.write(("class " +c.getName() + " <<adaptee>> #red").getBytes());
			
		} else {
			throw new RuntimeException("No adapter boolean set!");
		}
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

	@Override
	protected void afterMethodBlock(Model model, OutputStream os) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void beforeMethodBlock(Model model, OutputStream os) {
		// TODO Auto-generated method stub
		
	}

}
