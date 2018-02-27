package headfirst.designpatterns.decorator.starbuzzWithSizes;

public class Discount extends Beverage {

	public Beverage decorated;
	public double discount;
	
	public Discount(Beverage decorated, double discount){
		this.decorated = decorated;
		this.discount = discount;
	}
	
	
	@Override
	public double cost() {
		return decorated.cost() - discount;
	}

}
