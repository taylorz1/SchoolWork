
public class HighRoll implements BettingInterface {

	private String type;
	private double amount;
	
	public HighRoll(double betAmount) {
		this.amount = betAmount;
		this.type = "HighRoll";
	}
	
	public boolean isWinResult(int rollResult) {
		if (rollResult == 5 || rollResult == 6) {
			return true;
		}
		return false;
	}
	
	public double winAmount() {
		return this.amount*3;
	}
	
	public String type() {
		return this.type;
	}
}
