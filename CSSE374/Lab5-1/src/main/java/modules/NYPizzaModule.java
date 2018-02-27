package modules;

import com.google.inject.Provides;
import com.google.inject.name.Names;

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

public class NYPizzaModule extends PizzaModule {

	@Override
	protected void configure() {
		bind(String.class)
		.annotatedWith(Names.named("pizza-style"))
		.toInstance("NY Style");	
	}
	
	@Provides
	public Dough createDough() {
		return new ThinCrustDough();
	}
 
	@Provides
	public Sauce createSauce() {
		return new MarinaraSauce();
	}
 
	@Provides
	public Cheese createCheese() {
		return new ReggianoCheese();
	}
 
	@Provides
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
 
	@Provides
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	@Provides
	public Clams createClam() {
		return new FreshClams();
	}

}
