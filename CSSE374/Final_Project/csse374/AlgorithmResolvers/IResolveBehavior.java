package csse374.AlgorithmResolvers;

import java.util.List;

import csse374.Project.Model;
import soot.SootMethod;
import soot.Unit;
import soot.toolkits.graph.UnitGraph;

public interface IResolveBehavior {
	
	public List<SootMethod> resolve(Unit unit, SootMethod method, Model model);

}
