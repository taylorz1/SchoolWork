package part1;

import java.util.List;

import edu.rosehulman.csse374.cheesecake.PlainCheeseCake;

public abstract class AbstractCheeseCakeDecorator extends PlainCheeseCake {
	
	protected PlainCheeseCake wrapped;
	protected List<Topping> toppings;
	
	public AbstractCheeseCakeDecorator(PlainCheeseCake wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	abstract public String toString();
	
	abstract public void promptTopping();
}
