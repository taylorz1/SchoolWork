package csse374.AlgorithmResolvers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import csse374.Project.Model;
import soot.Hierarchy;
import soot.SootMethod;
import soot.Unit;

public class HeirarchyObjectResolveBehavior implements IResolveBehavior {

	@Override
	public List<SootMethod> resolve(Unit unit, SootMethod method, Model model) {
		// TODO Put heirarchyObjectResolveBehavior
		Hierarchy hierarchy = model.getScene().getActiveHierarchy();
		Collection<SootMethod> possibleMethods = hierarchy.resolveAbstractDispatch(method.getDeclaringClass(), method);
		List<SootMethod> output = new ArrayList<>();
		for(SootMethod m: possibleMethods){
			output.add(m);
		}
		return output;
	}

}
