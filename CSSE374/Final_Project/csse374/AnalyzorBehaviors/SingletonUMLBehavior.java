package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import csse374.Project.IFilter;
import csse374.Project.Model;
import csse374.Project.Pattern;
import csse374.Project.RelationObject;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

public class SingletonUMLBehavior extends UMLBehaviorTemplate {

	Collection<Pattern> patterns;

	@Override
	protected void atClass(Model model, SootClass c, OutputStream os) throws IOException {
		os.write(("class " + c.getName() + " <<Singleton>>").getBytes());
	}

	@Override
	protected void beforeClassBlock(Model model, OutputStream os) throws IOException {

	}

	@Override
	protected void afterClassBlock(Model model, OutputStream os) throws IOException {
		os.write(("skinparam class {\n BorderColor<<Singleton>> Blue\n}\n").getBytes());
	}

	@Override
	protected void afterClass(Model model, SootClass c, OutputStream os) throws IOException {

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
