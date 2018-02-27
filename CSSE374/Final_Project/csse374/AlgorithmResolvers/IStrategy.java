package csse374.AlgorithmResolvers;

import java.util.List;

import soot.SootMethod;

public interface IStrategy {
	public List<SootMethod> resolve(List<IResolveBehavior> behaviors);
}
