package part2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.rosehulman.csse374.cheesecake.CheeseCakeFactory;
import edu.rosehulman.csse374.cheesecake.PlainCheeseCake;
import part1.CheeseCakeApp;
import part1.ChocolateCheeseCakeFactory;
import part1.StrawberryCheeseCakeFactory;

public class CandyCapableApp extends CheeseCakeApp {
	
	Map<String, CandyTopping> candyTypes = new HashMap<>();
	Map<String,CheeseCakeFactory> cakeTypes = new HashMap<>();
	
	public CandyCapableApp() {
		this.addFactory("plain", new CheeseCakeFactory());
		this.addCandyType("reeses", new ReesesPieces());
		this.addFactory("chocolate", new ChocolateCheeseCakeFactory());
		this.addCandyType("mnm", new MnM());
		this.addFactory("strawberry", new StrawberryCheeseCakeFactory());
	}
	
	@Override
	public PlainCheeseCake createCheeseCake(String type) {
		String[] thingsToMake = type.split(" ");
		List<String> cl = new ArrayList<>();
		String cake = null;
		for (int i = 0; i < thingsToMake.length; i++) {
			if (this.candyTypes.get(thingsToMake[i]) != null) {
				cl.add(thingsToMake[i]);
			} else if (this.cakeTypes.get(thingsToMake[i]) != null) {
				cake = thingsToMake[i];
			}
		}
		List<CandyTopping> toppings = new LinkedList<>();
		for (int i = 0; i < cl.size(); i++) {
			CandyTopping t = this.candyTypes.get(cl.get(i));
			toppings.add(t);
		}
		PlainCheeseCake createdCake = null;
		if (cake != null) {
			CheeseCakeFactory f = this.cakeTypes.get(cake);
			createdCake = f.createCheeseCake(cake);
		}
		PlainCheeseCake candyWrap = null;
		if(createdCake != null) {
			candyWrap = new CandyDecorator(createdCake, toppings);
		}
		return candyWrap;
	}
	
	public void addCandyType(String s, CandyTopping c) {
		this.candyTypes.put(s, c);
	}
	
	@Override
	public void addFactory(String s, CheeseCakeFactory f) {
		this.cakeTypes.put(s, f);
	}

}
