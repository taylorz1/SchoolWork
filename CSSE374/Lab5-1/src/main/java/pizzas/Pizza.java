package pizzas;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import ingredients.Cheese;
import ingredients.Dough;
import ingredients.Sauce;

public abstract class Pizza {
	protected String style;

	protected Dough dough;
	protected Sauce sauce;
	protected Cheese cheese;

	
	@Inject
	public Pizza(@Named("pizza-style") String s, Dough d, Sauce sa, Cheese c) {
		this.style = s;
		this.dough = d;
		this.sauce = sa;
		this.cheese = c;
	}

	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	public void cut() {
		System.out.println("Cutting the pizza into diagonal slices");
	}

	public void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("---- " + style + " " + this.getClass().getSimpleName() + " ----\n");
		if (dough != null) {
			result.append(dough);
			result.append("\n");
		}
		if (sauce != null) {
			result.append(sauce);
			result.append("\n");
		}
		if (cheese != null) {
			result.append(cheese);
			result.append("\n");
		}
		return result.toString();
	}
}
