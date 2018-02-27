package modules;

import com.google.inject.Provides;
import com.google.inject.name.Names;

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

public class ChicagoPizzaModule extends PizzaModule {

	@Override
	protected void configure() {
		bind(String.class)
		.annotatedWith(Names.named("pizza-style"))
		.toInstance("Chicago Style");	
	}
	
	@Provides
	public Dough createDough() {
		return new ThickCrustDough();
	}
	
	
	@Provides
	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	@Provides
	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	@Provides
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(), 
		                      new Spinach(), 
		                      new Eggplant() };
		return veggies;
	}

	@Provides
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	@Provides
	public Clams createClam() {
		return new FrozenClams();
	}

}
