package factories;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

import pizzas.Pizza;

public class PizzaStore {
	protected Map<String, Class<? extends Pizza>> pizzas;
	private Injector injector;
	
	@Inject
	public PizzaStore(@Named("all-pizzas") Map<String, Class<? extends Pizza>> pizzas, Injector injector) {
		this.pizzas = pizzas;
		this.injector = injector;
	}


 
	public Pizza orderPizza(String type) {
		Class<? extends Pizza> pizzaClass = this.pizzas.get(type);
		Pizza pizza = this.injector.getInstance(pizzaClass);
		System.out.println("--- Making a " + pizza.getStyle() + " " + pizza.getClass().getSimpleName() + " ---");
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
