package pizzas;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import factories.PizzaIngredientFactory;
import ingredients.Cheese;
import ingredients.Dough;
import ingredients.Sauce;

public class CheesePizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
 
	@Inject
	public CheesePizza(@Named("pizza-style") String s, Dough d, Sauce sa, Cheese c) {
		super(s, d, sa, c);
	}
 
	public void prepare() {
		System.out.println("Preparing " + style + " " + this.getClass().getSimpleName());
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
	}
}
