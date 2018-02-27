import java.util.ArrayList;

/**
 * A customer of the store.
 * 
 * @author wilkin
 *
 */
public class Customer {
	
	private String name;
	private ArrayList<Rental> rentals;
	private int frequentRenterPoints;
	
	public Customer(String name) {
		this.name = name;
		this.rentals = new ArrayList<Rental>();
		this.frequentRenterPoints = 0;
	}
	
	public void addRental(Rental movieToRent) {
		rentals.add(movieToRent);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getFrequentRenterPoints() {
		return this.frequentRenterPoints;
	}
	
	public void increaseFrequentRenterPoints(int increaseBy) {
		this.frequentRenterPoints += increaseBy;
	}
	
	public String statement() {
		double totalAmount = 0.0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + getName() + "\n";
		
		for(Rental rental : this.rentals) {
			double thisAmount = rental.getPrice();
			
			// add frequent renter points
			frequentRenterPoints++;
			
			// add bonus for a two day new release rental
			if((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
				frequentRenterPoints++;
			
			// show figures for this rental
			result += "\t" + rental.getMovie().getTitle() + "\t" + String.format("%.2f", thisAmount) + "\n";
			totalAmount += thisAmount;
			
		}//end for
		
		//add footer lines
		result += "Amount owed is " + String.format("%.2f", totalAmount) + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		increaseFrequentRenterPoints(frequentRenterPoints);
	
		return result;
	}

}
