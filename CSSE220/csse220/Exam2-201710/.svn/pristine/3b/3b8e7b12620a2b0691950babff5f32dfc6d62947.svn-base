package polymorphism;

public class FixedDiscount implements ItemDiscount{

	private String partialName;
	private double savings;

	/**
	 * Create a new fixed discount for a given name
	 * 
	 * @param name
	 * @param savings
	 */
	public FixedDiscount(String partialName, double savings) {
		this.partialName = partialName;
		this.savings = savings;
	}
	
	@Override
	public double calculateDiscountFor(double price) {
		return this.savings;
	}

	@Override
	public boolean matches(String itemName) {
		return itemName.contains(this.partialName);
	}

}
