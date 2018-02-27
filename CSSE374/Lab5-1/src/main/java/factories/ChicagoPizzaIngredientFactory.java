package factories;

import ingredients.BlackOlives;
import ingredients.Cheese;
import ingredients.Clams;
import ingredients.Dough;
import ingredients.Eggplant;
import ingredients.FrozenClams;
import ingredients.MozzarellaCheese;
import ingredients.Pepperoni;
import ingredients.PlumTomatoSauce;
import ingredients.Sauce;
import ingredients.SlicedPepperoni;
import ingredients.Spinach;
import ingredients.ThickCrustDough;
import ingredients.Veggies;

public class ChicagoPizzaIngredientFactory 
	implements PizzaIngredientFactory 
{

	public Dough createDough() {
		return new ThickCrustDough();
	}

	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(), 
		                      new Spinach(), 
		                      new Eggplant() };
		return veggies;
	}

	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FrozenClams();
	}
}
