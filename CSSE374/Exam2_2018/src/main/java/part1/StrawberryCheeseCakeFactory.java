package part1;


import edu.rosehulman.csse374.cheesecake.CheeseCakeFactory;
import edu.rosehulman.csse374.cheesecake.PlainCheeseCake;

public class StrawberryCheeseCakeFactory extends CheeseCakeFactory {
	@Override
	public PlainCheeseCake createCheeseCake(String type) {
		if (!type.equals("strawberry"))
			return null;
		
		PlainCheeseCake cake = super.createCheeseCake("plain");
		return new StrawberryCheeseCake(cake);
	}

}
