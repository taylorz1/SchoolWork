package factories;

import ingredients.Cheese;
import ingredients.Clams;
import ingredients.Dough;
import ingredients.FreshClams;
import ingredients.Garlic;
import ingredients.MarinaraSauce;
import ingredients.Mushroom;
import ingredients.Onion;
import ingredients.Pepperoni;
import ingredients.RedPepper;
import ingredients.ReggianoCheese;
import ingredients.Sauce;
import ingredients.SlicedPepperoni;
import ingredients.ThinCrustDough;
import ingredients.Veggies;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
 
	public Dough createDough() {
		return new ThinCrustDough();
	}
 
	public Sauce createSauce() {
		return new MarinaraSauce();
	}
 
	public Cheese createCheese() {
		return new ReggianoCheese();
	}
 
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
 
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FreshClams();
	}
}
