package modules;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

import pizzas.CheesePizza;
import pizzas.ClamPizza;
import pizzas.PepperoniPizza;
import pizzas.Pizza;
import pizzas.VeggiePizza;

public abstract class PizzaModule extends AbstractModule {

	@Override
	protected abstract void configure();
	
	@Provides @Named("all-pizzas")
	public Map<String, Class<? extends Pizza>> createPizzaTypes() {
		Map<String, Class<? extends Pizza>> pizzaTypes = new HashMap<>();
		pizzaTypes.put("cheese", CheesePizza.class);
		pizzaTypes.put("veggie", VeggiePizza.class);
		pizzaTypes.put("clam", ClamPizza.class);
		pizzaTypes.put("pepperoni", PepperoniPizza.class);
		return pizzaTypes;
	}
	

}
