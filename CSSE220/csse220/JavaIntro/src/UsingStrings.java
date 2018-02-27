/**
 * This class demonstrates using methods on String objects, plus implicit and
 * explicit parameters.
 * 
 * @author Curt Clifton. Created Sep 7, 2008.
 */
public class UsingStrings {

	/**
	 * This is the starting point for the example.
	 * 
	 * @param args
	 *            Command-line arguments, ignored here
	 */
	public static void main(String[] args) {
		String name = "Bob Forapples";
		String major = "Party Game Engineering";
		int age = 20;

		int nameLength = name.length();


		System.out.println("'"+ name + "' has " + nameLength + " characters" );

		/* TODO in class: print Bob's name, major and age. */

		/*
		 * TODO in class: Use the replace() method of String to replace "Party Game" with
		 * "Celebration Entertainment" in Bob's major.
		 */

		/* TODO in class: print Bob's name, major, and age again. */

		/*
		 * TODO in class: Change Bob's major again, this time to be in all uppercase. Use
		 * the toUpperCase() method of String.
		 */

		/* TODO in class: print Bob's name, major, and age again. */
	}
}
