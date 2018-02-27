package pizzas;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import factories.PizzaIngredientFactory;
import ingredients.Cheese;
import ingredients.Clams;
import ingredients.Dough;
import ingredients.Sauce;

public class ClamPizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
	protected Clams clam;

	@Inject
	public ClamPizza(@Named("pizza-style") String s, Dough d, Sauce sa, Cheese c) {
		super(s, d, sa, c);
	}
 
	public void prepare() {
		System.out.println("Preparing " + style + " " + this.getClass().getSimpleName());
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		clam = ingredientFactory.createClam();
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append(super.toString());
		if (clam != null) {
			result.append(clam);
			result.append("\n");
		}
		return result.toString();
	}	
}
