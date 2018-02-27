package banking;

public class BankingMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		SavingsAccount s = new SavingsAccount(10);
		
		s.deposit(100);
		s.addInterest();
		System.out.println(s.getBalance());
	}

}
