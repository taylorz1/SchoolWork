package app;

import com.google.inject.Guice;
import com.google.inject.Injector;

import factories.ChicagoPizzaStore;
import factories.NYPizzaStore;
import factories.PizzaStore;
import modules.ChicagoPizzaModule;
import modules.NYPizzaModule;
import pizzas.Pizza;

public class PizzaTestDrive {
 
	public static void main(String[] args) {
		Injector nyInjector = Guice.createInjector(new NYPizzaModule());
		Injector chicagoInjector = Guice.createInjector(new ChicagoPizzaModule());
		
		
		PizzaStore nyStore = nyInjector.getInstance(PizzaStore.class);
		PizzaStore chicagoStore = chicagoInjector.getInstance(PizzaStore.class);
		
 
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("clam");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("pepperoni");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("pepperoni");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("veggie");
		System.out.println("Ethan ordered a " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("veggie");
		System.out.println("Joel ordered a " + pizza + "\n");
	}
}
