package part1;

import java.util.HashMap;
import java.util.Map;

import edu.rosehulman.csse374.cheesecake.CheeseCakeFactory;
import edu.rosehulman.csse374.cheesecake.PlainCheeseCake;

public class CheeseCakeApp extends CheeseCakeFactory {
	private Map<String, CheeseCakeFactory> factories = new HashMap<>();

	public void addFactory(String s, CheeseCakeFactory f) {
		this.factories.put(s, f);
	}

	@Override
	public PlainCheeseCake createCheeseCake(String type) {
		if (this.factories.get(type) == null) {
			return null;
		} else {
			return this.factories.get(type).createCheeseCake(type);
		}
	}

}
