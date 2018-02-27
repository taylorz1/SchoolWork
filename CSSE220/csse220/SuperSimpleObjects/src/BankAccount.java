
public class BankAccount {
		private double balance;
		private String owner;
		
		public BankAccount(String owner, double balance) {
			this.owner = owner;
			this.balance = balance;
			
		}
		public void deposit (double money) {
			this.balance = balance + money;
		}
		public double getBalance() {
			return this.balance;
		}
		public String getOwner() {
			return this.owner;
		}
		public double getInterestRate() {
			System.out.println("Yearly interest payment would be: ");
			System.out.println(balance*0.01);
			return 0.01;
		}
}
