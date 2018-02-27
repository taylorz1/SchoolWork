package csse374.analyzers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import csse374.Project.Model;
import soot.SootClass;

public class DemoInterfaceAnalyzor extends AbstractAnalyzor{
	

	@Override
	public void analyze(Model model){
		List<SootClass> recurInterfaces = new ArrayList<>();  
		for(SootClass clazz: model.getSootClass()){
			Collection<SootClass> interfaces = clazz.getInterfaces();
			for(SootClass klass:interfaces){
				recurInterfaces.add(klass);
			}
		}
		
		for(SootClass clazz: recurInterfaces){
			model.addSootClass(clazz);
		}
	}
}
