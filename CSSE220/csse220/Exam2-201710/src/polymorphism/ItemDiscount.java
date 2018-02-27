package polymorphism;

public interface ItemDiscount {
	
	public double calculateDiscountFor(double price);
	
	public boolean matches(String itemName);
}
