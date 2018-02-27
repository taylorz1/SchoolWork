package csse374.Project;

import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/**
 * 
 * @author taylorz1
 * Filters nothing out as it accesses the deepest level.
 *
 */

public class PrivateFilter implements IFilter {

	@Override
	public boolean filter(SootClass soot) {
		return false;
	}

	@Override
	public boolean filter(SootMethod soot) {
		return false;
	}

	@Override
	public boolean filter(SootField soot) {
		return false;
	}

}
