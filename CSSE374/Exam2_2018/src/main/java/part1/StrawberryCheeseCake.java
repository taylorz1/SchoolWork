package part1;

import java.util.LinkedList;
import java.util.Scanner;

import edu.rosehulman.csse374.cheesecake.PlainCheeseCake;

public class StrawberryCheeseCake extends AbstractCheeseCakeDecorator {

	public StrawberryCheeseCake(PlainCheeseCake wrapped) {
		super(wrapped);
		this.toppings = new LinkedList<>();
		toppings.add(new StrawberryTopping());
	}

	@Override
	public String toString() {
		promptTopping();
		StringBuilder b = new StringBuilder();
		b.append(wrapped.toString());


		b.append("======== Then add on the extra toppings! ========\n");
		for(int i = 0; i < this.toppings.size(); i++) {
			b.append("Topping: " + this.toppings.get(i).toString() + "\n");
		}

		return b.toString();
	}

	@Override
	public void promptTopping() {
		System.out.println("Does the customer want WhippedTopping? y/n");
		Scanner s = new Scanner(System.in);
		String str = s.next();
		if (str.equals("y")) {
		this.toppings.add(new WhippedTopping());
		}
	}
}
