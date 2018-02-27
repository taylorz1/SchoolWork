package csse374.AlgorithmResolvers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import csse374.Project.Model;
import soot.MethodOrMethodContext;
import soot.Scene;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.toolkits.callgraph.CallGraph;

public class CallGraphResolveBehavior implements IResolveBehavior {

	@Override
	public List<SootMethod> resolve(Unit unit, SootMethod method, Model model) {
		return performContextSensitivePointerAnalysis(model.getScene(), unit, method);
	}

	
	private List<SootMethod> performContextSensitivePointerAnalysis(Scene scene, Unit stmt, SootMethod method) {
		System.out.println("Performing Context-Sensitive pointer analysis for " + method.getName() + "() ...");
		CallGraph callGraph = scene.getCallGraph();
		
		List<SootMethod> targetMethods = new ArrayList<>();
		callGraph.edgesOutOf(stmt).forEachRemaining(edge -> {
			MethodOrMethodContext methodOrCntxt = edge.getTgt();
			SootMethod targetMethod = methodOrCntxt.method();
			if(targetMethod != null) {
				targetMethods.add(targetMethod);
			}
		});
		return targetMethods;
	}
}
