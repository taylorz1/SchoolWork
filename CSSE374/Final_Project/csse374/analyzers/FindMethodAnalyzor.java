package csse374.analyzers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import csse374.Project.Model;
import soot.Body;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class FindMethodAnalyzor extends AbstractAnalyzor {

	@Override
	public void analyze(Model model) {
		String s = model.getMethodSignature();
		String[] signature = s.split(",");
		SootClass sc = model.getScene().getSootClass(signature[0]);
		List<SootMethod> methods = sc.getMethods();
		Map<String, Boolean> paramUsage = new HashMap<>();
		populateMap(paramUsage, signature);
		
		for (SootMethod m : methods) {
			if (m.getName().equals(signature[1])) {
				
				for(Type t : m.getParameterTypes()) {
					System.out.println(t.toString());
					if(paramUsage.get(t.toString()) != null) {
						paramUsage.replace(t.toString(), true);
					} else {
						break;
					}
				}
				boolean andMap = true;
				for(String key : paramUsage.keySet()) {
					andMap = andMap && paramUsage.get(key);
				}
				if (andMap == true) {
					Body body = m.retrieveActiveBody();
					UnitGraph cfg = new ExceptionalUnitGraph(body);
					model.setUnitGraph(cfg);
					return;
				}
			}
		}
		throw new RuntimeException("No method found");
	}

	private void populateMap(Map<String, Boolean> paramUsage, String[] sig) {
		for(int i = 2; i < sig.length; i++) {
			paramUsage.put(sig[i], false);
		}
	}

}
