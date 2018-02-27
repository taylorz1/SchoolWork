package pizzas;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import factories.PizzaIngredientFactory;
import ingredients.Cheese;
import ingredients.Dough;
import ingredients.Sauce;
import ingredients.Veggies;

public class VeggiePizza extends Pizza {
	Veggies veggies[];

	@Inject
	public VeggiePizza(@Named("pizza-style") String s, Dough d, Sauce sa, Cheese c) {
		super(s, d, sa, c);
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
		return result.toString();
	}	
}
