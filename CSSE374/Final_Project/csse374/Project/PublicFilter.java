package csse374.Project;

import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

public class PublicFilter implements IFilter {

	@Override
	public boolean filter(SootClass soot) {
		return !soot.isPublic();
	}

	@Override
	public boolean filter(SootMethod soot) {
		return !soot.isPublic();
	}

	@Override
	public boolean filter(SootField soot) {
		return !soot.isPublic();
	}

}
