package part1;

import java.util.LinkedList;
import java.util.Scanner;

import edu.rosehulman.csse374.cheesecake.PlainCheeseCake;

public class ChocolateCheeseCake extends AbstractCheeseCakeDecorator {
	
	ChocolateDrizzle chocolate;

	public ChocolateCheeseCake(PlainCheeseCake wrapped) {
		super(wrapped);
		this.toppings = new LinkedList<>();
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(wrapped.toString());
		b.append("======== Then add on the extra toppings! ========\n");
		this.promptTopping();
		for(int i = 0; i < this.toppings.size(); i++) {
			b.append("Topping: " + this.toppings.get(i).toString() + "\n");
		}
		return b.toString();
	}

	@Override
	public void promptTopping() {
		System.out.println("Please melt the chocolate and add the chocolate");
		ChocolateDrizzle d = new ChocolateDrizzle();
		this.toppings.add(d);
		System.out.println("melt the chocolate? y/n");
		Scanner s = new Scanner(System.in);
		if (s.next().equals("y")) {
			d.meltme();
		}
	}

}
