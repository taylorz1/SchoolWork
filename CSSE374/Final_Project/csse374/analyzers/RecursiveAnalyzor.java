package csse374.analyzers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import csse374.Project.Model;
import soot.SootClass;

public class RecursiveAnalyzor extends AbstractAnalyzor{
	Set<SootClass> superinterface;
	
	public RecursiveAnalyzor() {
		this.superinterface = new HashSet();
	}

	@Override
	public void analyze(Model model){
		for(SootClass clazz: model.getSootClass()){
			getClassesRecursively(clazz);
		}

		for(SootClass clazz: superinterface){
			model.addSootClass(clazz);
		}
	}
	
	private void getClassesRecursively(SootClass clazz){
		if(clazz.getInterfaceCount()!=0){
			Collection<SootClass> inters = clazz.getInterfaces();
			for(SootClass klazz:inters){
				checkValid(klazz);
				getClassesRecursively(klazz);
			}
		}
		if(clazz.hasSuperclass()){
			SootClass superclass = clazz.getSuperclass();
			checkValid(clazz);
			getClassesRecursively(superclass);
		}
	}
	
	private void checkValid(SootClass clazz){
		if(!clazz.getName().contains("$")){
			superinterface.add(clazz);
		}
	}

}
