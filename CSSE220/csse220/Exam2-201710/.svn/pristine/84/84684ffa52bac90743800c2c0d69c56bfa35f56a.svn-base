package polymorphism;

public class PercentageDiscount implements ItemDiscount{

	private String partialName;
	private double percentage;

	/**
	 * Create a new percentage discount for a given name
	 * 
	 * @param name
	 * @param percentage percentage (represented as a number between 0 and 1)
	 */
	public PercentageDiscount(String partialName, double percentage) {
		this.partialName = partialName;
		this.percentage = percentage;
	}
	
	@Override
	public double calculateDiscountFor(double price) {
		return this.percentage*price;
	}
	
	@Override
	public boolean matches(String itemName) {
		return itemName.contains(this.partialName);
	}

}
