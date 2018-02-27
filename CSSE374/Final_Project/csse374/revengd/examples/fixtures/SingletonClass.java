package csse374.revengd.examples.fixtures;

public class SingletonClass {
	
	private static SingletonClass single = new SingletonClass();
	
	private SingletonClass () {
		CalculatorA a = new CalculatorA();
	}
	
	public static SingletonClass getSingletonClass() {
		return single;
	}

}
