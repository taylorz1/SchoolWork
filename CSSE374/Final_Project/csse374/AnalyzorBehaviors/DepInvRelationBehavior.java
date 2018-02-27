package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;

import csse374.Project.Model;
import csse374.Project.RelationObject;

public class DepInvRelationBehavior extends UMLRelationBehaviorTemplate {
	
	@Override
	protected void afterRelation(Model model, RelationObject r, OutputStream os) throws IOException {
				os.write((" #pink").getBytes());
				os.write('\n');
		}

	@Override
	protected void beforeRelation(Model model, RelationObject r, OutputStream os) throws IOException {
		// TODO Auto-generated method stub

	}

}
