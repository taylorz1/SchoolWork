package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import csse374.Project.Model;
import csse374.Project.Pattern;
import csse374.Project.PatternStore;
import csse374.Project.RelationObject;

public class DecoratorRelationBehavior extends UMLRelationBehaviorTemplate {

	@Override
	protected void afterRelation(Model model, RelationObject r, OutputStream os) throws IOException {
		os.write((" : <<decorates>>\n").getBytes());
	}

	@Override
	protected void beforeRelation(Model model, RelationObject r, OutputStream os) throws IOException {
		// TODO Auto-generated method stub

	}

}
