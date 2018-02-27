package csse374.analyzers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import csse374.Project.Model;
import csse374.revengd.soot.MainMethodMatcher;
import csse374.revengd.soot.SceneBuilder;
import soot.Scene;

abstract public class InitAnalyzor extends AbstractAnalyzor{

	@Override
	public void analyze(Model model){
		SceneBuilder scenebuilder = SceneBuilder.create()
				.addExclusions(Arrays.asList("java.*","javax.*","sun,*"))
				.addExclusions(Arrays.asList("soot.*", "polygot.*"))					
				.addExclusions(Arrays.asList("org.*", "com.*"));
		
		buildScene(model, scenebuilder);
	}
	
	abstract public void buildScene(Model model, SceneBuilder builder);

}
