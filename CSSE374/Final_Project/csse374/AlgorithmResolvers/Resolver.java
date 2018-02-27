package csse374.AlgorithmResolvers;

import java.util.LinkedList;
import java.util.List;

import csse374.Project.Model;
import soot.SootMethod;
import soot.Unit;

public class Resolver implements IResolveBehavior{
	
	private List<IResolveBehavior> behaviors = new LinkedList<IResolveBehavior>();
	private IStrategy strategy;
	
	
	public List<SootMethod> resolve(Unit unit, SootMethod sootMethod, Model model){
		return strategy.resolve(behaviors);
	}
	
	public void addBehavior(IResolveBehavior b) {
		this.behaviors.add(b);
	}
	
	public void addStrategy(IStrategy s){
		this.strategy = s;
	}

}
