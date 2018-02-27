package part2;

import java.util.List;

import edu.rosehulman.csse374.cheesecake.PlainCheeseCake;
import part1.AbstractCheeseCakeDecorator;

public class CandyDecorator extends AbstractCheeseCakeDecorator {
	private List<CandyTopping> extratoppings;

	public CandyDecorator(PlainCheeseCake wrapped, List<CandyTopping> extraToppings) {
		super(wrapped);
		this.extratoppings = extraToppings;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(wrapped.toString());
		if (extratoppings.size() != 0) {
			b.append("======= Candies =======\n");
			for (int i = 0; i < extratoppings.size(); i++) {
				b.append("Candy: " + extratoppings.get(i).toString() + "\n");
			}
		}
		return b.toString();
	}

	@Override
	public void promptTopping() {
		/// Who cares!
	}

}
