
public class BankAccountMain {
		public static void main(String[] args) {
			BankAccount buffalosAccount = new BankAccount("buffalo", 1.04);
			BankAccount secretOffshoreAccount = new BankAccount("Senior buffaluffaphagous", 1e6);
			buffalosAccount.deposit(0.25);
			System.out.println(buffalosAccount.getBalance());
			System.out.println(buffalosAccount.getOwner());
			System.out.println(secretOffshoreAccount.getBalance());
			System.out.println(secretOffshoreAccount.getOwner());
			System.out.println(buffalosAccount.getInterestRate());
		}
}
