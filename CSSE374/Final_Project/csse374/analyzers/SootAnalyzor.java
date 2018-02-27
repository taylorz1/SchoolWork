package csse374.analyzers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import csse374.Project.Model;
import csse374.revengd.soot.MainMethodMatcher;
import csse374.revengd.soot.SceneBuilder;
import soot.Scene;

public class SootAnalyzor extends InitAnalyzor {

	@Override
	public void buildScene(Model model, SceneBuilder builder) {
		SceneBuilder sceneb = builder.addDirectory(model.getPath()).setEntryClass(model.getMainClass())
				.addEntryPointMatcher(new MainMethodMatcher(model.getMainClass()));
		Scene scene;
		if(model.getSingleElement("-classpath", String.class) == null) {
			scene = sceneb.build();
		} else {
			scene = sceneb.addClassPath(model.getSingleElement("-classpath", String.class)).build();
		}
		System.out.println("==============================================================");
		System.out.println("Application classes loaded by SOOT:");

		Set<String> classNames = new HashSet();
		if(model.getClasses() != null){
			String[] klasses = model.getClasses().split(",");
			for (String s : klasses) {
				classNames.add(s);
			}
		}

		String string = model.getMethodSignature();
		scene.getClasses().forEach(clazz -> {
			if ( string != null) {
				if(clazz.getName().contains("java.") || clazz.getName().contains("edu.") || clazz.getName().contains("soot")){
					
				}else{
				model.addSootClass(clazz);
				}
			} else {
				if (classNames.contains(clazz.getName())) {
					model.addSootClass(clazz);
					System.out.println(clazz.getName());
				}
			}
		});
		model.setScene(scene);

		System.out.println(model.getSootClass().size());

	}

}
