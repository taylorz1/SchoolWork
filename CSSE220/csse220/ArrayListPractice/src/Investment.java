/**
 * An Investment accumulates interest at a fixed annual rate.
 * 
 * @author Cay Horstmann, javadoc by David Mutchler. Modified December 15, 2009.
 */
public class Investment {
	private int years;
	private double balance;
	private double rate;

	/**
	 * Constructs an Investment object from the given starting balance and
	 * interest rate.
	 * 
	 * @param initialBalance
	 *            Initial balance (value) of the Investment
	 * @param rate
	 *            Annual interest rate as a percent (e.g. 5.0 for 5%)
	 */
	public Investment(double initialBalance, double rate) {
		this.balance = initialBalance;
		this.rate = rate;
		this.years = 0;
	}

	/**
	 * Keeps accumulating interest until the given target balance has been
	 * reached.
	 * 
	 * @param targetBalance
	 */
	public void waitForBalance(double targetBalance) {
		// TODO Implement this method so that it works as described.

	}

	/**
	 * Returns the current balance (value) of this Investment.
	 * 
	 * @return the current Investment balance
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * Returns the number of years since the start of the investment.
	 * 
	 * @return the number of years since the start of the investment
	 */
	public int getYears() {
		return this.years;
	}
}
