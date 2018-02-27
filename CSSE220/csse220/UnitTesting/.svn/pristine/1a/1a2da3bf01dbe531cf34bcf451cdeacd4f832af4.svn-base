import java.util.Scanner;

/**
 * This class demonstrates unit testing and asks you to use the Java API
 * documentation to find methods to solve problems using Strings.
 * 
 * @author Delvin Defoe. Created Sep 16, 2015.
 */
public class StringMethodsPractice {
	
	private Scanner inputScanner;
	private String message;
	
	/**
	 * 
	 * Initializes a no-argument instance of StringMethodsPractice.
	 *
	 */
	public StringMethodsPractice(){
		this.inputScanner = new Scanner(System.in);
		this.message = "message";
	}
	
	/**
	 * 
	 * Initializes a one-argument instance of StringMethodsPractice
	 *
	 * @param input
	 */
	public StringMethodsPractice(String input){
		this();
		this.message = input;
	}
	
	/**
	 * 
	 * Sets message to the given value.
	 *
	 * @param message
	 */
	public void setMessage(String message){
		this.message = message;
	}
	
	/**
	 * 
	 * Sets the value of message from the console
	 *
	 */
	public void setConsoleMessage(){
		System.out.println("Enter a message: ");
		this.message = this.inputScanner.nextLine();
	}

	/**
	 * Converts the message string to a string representing shouting.
	 * 
	 * @param message
	 * @return message in ALL UPPER CASE
	 */
	public String shout(String message) {
		return message.toUpperCase();
	}

	/**
	 * @param message
	 * @return message in all lower case
	 */
	public String whisper(String message) {
		return message.toLowerCase();
	}

	/**
	 * @param message
	 * @return message with o's and e's swapped
	 */
	public String holleWerld(String message) {
		String substitutionGibberish = "$%&";
		String answer = message.replace("o", substitutionGibberish);
		answer = answer.replace('e', 'o');
		answer = answer.replace(substitutionGibberish, "e");
		return answer;
	}
	
	/**
	 * Starting point.
	 * 
	 * @param args
	 *            ignored
	 */
	public static void main(String[] args) {
		StringMethodsPractice practice = new StringMethodsPractice();
		practice.setConsoleMessage();

		System.out.println(practice.shout(practice.message));
		System.out.println(practice.whisper(practice.message));
		System.out.println(practice.holleWerld(practice.message));
	}
}
