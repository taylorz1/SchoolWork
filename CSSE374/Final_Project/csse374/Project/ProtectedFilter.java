package csse374.Project;

import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

public class ProtectedFilter implements IFilter {

	@Override
	public boolean filter(SootClass soot) {
		return soot.isPrivate();
	}

	@Override
	public boolean filter(SootMethod soot) {
		return soot.isPrivate();
	}

	@Override
	public boolean filter(SootField soot) {
		return soot.isPrivate();
	}

}
