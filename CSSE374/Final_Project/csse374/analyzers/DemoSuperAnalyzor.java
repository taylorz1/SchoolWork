package csse374.analyzers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import csse374.Project.Model;
import soot.SootClass;

public class DemoSuperAnalyzor extends AbstractAnalyzor {

	@Override
	public void analyze(Model model){
		for(SootClass clazz: model.getSootClass()){
			if(clazz.hasSuperclass()){
				SootClass superClass = clazz.getSuperclass();
				model.addSootClass(superClass);
			}
		}
	}

}
