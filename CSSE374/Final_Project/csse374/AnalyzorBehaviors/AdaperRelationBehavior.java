package csse374.AnalyzorBehaviors;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import csse374.Project.Model;
import csse374.Project.Pattern;
import csse374.Project.PatternStore;
import csse374.Project.RelationObject;

public class AdaperRelationBehavior extends UMLRelationBehaviorTemplate {
	
	
	@Override
	protected void afterRelation(Model model, RelationObject r, OutputStream os) throws IOException {
		PatternStore patternStore = model.getPatternStore();
		List<Pattern> pats = patternStore.getPattern("adapter");
		boolean adapter = false;
		for(Pattern p : pats) {
			if(p.getRelations().contains(r)) {
				if(p.getRelations("adapter").contains(r)) {
					adapter = true;
				} else if (p.getRelations("target").contains(r)) {
					
				} else {
					throw new RuntimeException("Unable to match component to given adapter relation");
				}
				break;
			}
		}
		if(adapter) {
			System.out.println(r+" adappts");
			os.write((" : <<adapts>>\n").getBytes());
		}else{
			System.out.println(r+ " not adapts");
			os.write("\n".getBytes());
		}
	}

	@Override
	protected void beforeRelation(Model model, RelationObject r, OutputStream os) throws IOException {
		// TODO Auto-generated method stub

	}

}
