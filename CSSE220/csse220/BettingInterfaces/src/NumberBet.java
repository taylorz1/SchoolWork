
public class NumberBet implements BettingInterface{

	private int numberBetOn;
	private double amount;
	private String type;
	
	public NumberBet(int numberBetOn, double betAmount) {
		this.numberBetOn = numberBetOn;
		this.amount = betAmount;
		this.type = "number" + numberBetOn;
	}

	public boolean isWinResult(int rollResult) {
		return rollResult == this.numberBetOn;
	}
	
	public double winAmount() {
		return this.amount*6;
	}
	
	public String type() {
		return this.type;
	}
}
