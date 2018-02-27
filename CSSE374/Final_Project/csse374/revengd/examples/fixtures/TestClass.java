package csse374.revengd.examples.fixtures;

public class TestClass extends CalculatorA{

	@Override
	public double add(double... args) {
		// TODO Auto-generated method stub
		super.add(args);
		double i = super.add(args);
		CalculatorA a = new CalculatorA();
		double j = a.add(args);
		return super.add(args);
	}
}
