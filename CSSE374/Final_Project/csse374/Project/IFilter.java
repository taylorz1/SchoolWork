package csse374.Project;

import soot.SootClass;
import soot.SootMethod;
import soot.SootField;

public interface IFilter {
	public boolean filter(SootClass soot);
	public boolean filter(SootMethod soot);
	public boolean filter(SootField soot);
}
