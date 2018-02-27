package pizzas;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import factories.PizzaIngredientFactory;
import ingredients.Cheese;
import ingredients.Dough;
import ingredients.Pepperoni;
import ingredients.Sauce;
import ingredients.Veggies;

public class PepperoniPizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
	Pepperoni pepperoni;
	Veggies veggies[];

	@Inject
	public PepperoniPizza(@Named("pizza-style") String s, Dough d, Sauce sa, Cheese c) {
		super(s, d, sa, c);
	}
 
	public void prepare() {
		System.out.println("Preparing " + style + " " + this.getClass().getSimpleName());
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		veggies = ingredientFactory.createVeggies();
		pepperoni = ingredientFactory.createPepperoni();
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(super.toString());
		if (veggies != null) {
			for (int i = 0; i < veggies.length; i++) {
				result.append(veggies[i]);
				if (i < veggies.length-1) {
					result.append(", ");
				}
			}
			result.append("\n");
		}
		if (pepperoni != null) {
			result.append(pepperoni);
			result.append("\n");
		}
		return result.toString();
	}	
}
