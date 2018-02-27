package banking;

public class CheckingAccount extends BankAccount{
	
	public CheckingAccount() {
		super(0);
	}
	
	@Override
	public void deposit(double amount) {
		super.deposit(amount-1);
	}
	
	
}
