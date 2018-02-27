import java.util.Scanner;

/**
 * This is an example of using a Scanner to read console input.
 * 
 * @author Delvin Defoe. Created Sep 16, 2015.
 */
public class ConsoleWorker {

	private Scanner inputScanner;
	private String name;
	private int birthMonth;
	private int birthDay;
	private int birthYear;
	private double annualSalary;

	/**
	 * Constructs a new ConsoleWorker, initializing the input scanner
	 *
	 */
	public ConsoleWorker() {
		this.inputScanner = new Scanner(System.in);
	}

	/**
	 * Returns the user's name
	 *
	 * @return user name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the user's birth month
	 *
	 * @return user birth month
	 */
	public int getBirthMonth() {
		return this.birthMonth;
	}

	/**
	 * Returns the user's birth day
	 *
	 * @return user birth day
	 */
	public int getBirthDay() {
		return this.birthDay;
	}

	/**
	 * Returns the user's birth year
	 *
	 * @return user birth year
	 */
	public int getBirthYear() {
		return this.birthYear;
	}

	/**
	 * Returns the user's annual salary
	 *
	 * @return user annual salary
	 */
	public double getAnnualSalary() {
		return this.annualSalary;
	}

	/**
	 * Starts here.
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main(String[] args) {
		ConsoleWorker worker = new ConsoleWorker();

		// DONE uncomment the code below and implement
		// the missing methods, adding appropriate prompts
		// before each input.
		worker.setName();
		worker.setBirthMonth();
		worker.setBirthDay();
		worker.setBirthYear();
		worker.setAnualSalary();

		System.out.printf("%s was born %02d/%02d/%d and earns $%03.2f.%n", worker.getName(), worker.getBirthMonth(),
				worker.getBirthDay(), worker.getBirthYear(), worker.getAnnualSalary());

	}

	private void setAnualSalary() {
		// DONE Auto-generated method stub.
		System.out.println("What is your Annual Salary?");
		this.annualSalary = this.inputScanner.nextDouble();

	}

	private void setBirthYear() {
		// DONE Auto-generated method stub.
		System.out.println("What is your Birth Year?");
		this.birthYear = this.inputScanner.nextInt();

	}

	private void setBirthDay() {
		// DONE Auto-generated method stub.
		System.out.println("What is your Birth day?");
		this.birthDay = this.inputScanner.nextInt();

	}

	private void setBirthMonth() {
		// DONE Auto-generated method stub.
		System.out.println("What is your Birth Month?");
		this.birthMonth = this.inputScanner.nextInt();

	}

	private void setName() {
		// DONE Auto-generated method stub.
		System.out.println("What is your name?");
		this.name = this.inputScanner.nextLine();

	}

}
