package csse374.analyzers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import csse374.Project.Model;
import csse374.revengd.soot.MainMethodMatcher;
import csse374.revengd.soot.SceneBuilder;
import soot.Scene;

public class DemoAnalyzor extends InitAnalyzor {

	@Override
	public void buildScene(Model model, SceneBuilder builder) {
		Scene scene = builder.addDirectory("/Users/taylorz1/Documents/School/CSSE374/RevEngD/build/classes/main/")
				.setEntryClass("csse374.Project.Main") // Sets the entry point
														// class for the
														// application under
														// analysis
				.addEntryPointMatcher(new MainMethodMatcher("csse374.Project.Main")) // Matches
																						// main
																						// method
																						// of
																						// CalculatorApp
				.addClass(model.getDemo()).build();
		model.setScene(scene);

		System.out.println("==============================================================");
		System.out.println("Application classes loaded by SOOT:");

		model.addSootClass(scene.getSootClass(model.getDemo()));

		Set<String> classNames = new HashSet();
		if (model.getClasses() != null) {
			String[] klasses = model.getClasses().split(",");
			for (String s : klasses) {
				classNames.add(s);
			}
		}

		scene.getClasses().forEach(clazz -> {
			if (classNames.contains(clazz.getName())) {
				model.addSootClass(clazz);
				System.out.println(clazz.getName());
			}
		});

		System.out.println(model.getSootClass().size());
	}

}
