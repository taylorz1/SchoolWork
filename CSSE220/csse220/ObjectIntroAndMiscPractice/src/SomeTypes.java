/**
 * This class is for an exercise on various number types in Java.
 * 
 * @author Curt Clifton, modified by Delvin Defoe. Created Sep 7, 2008, updated Dec 2, 2013.
 */
public class SomeTypes {
	/**
	 * Largest value to find the factorial of, in this program.
	 */
	public static final int MAX = 18;

	/**
	 * Starting point for the exercise.
	 * 
	 * @param args
	 *            Command-line arguments, ignored here
	 */
	public static void main(String[] args) {
		/*
		 * TODO in class: change the types below and in factorial() to avoid integer
		 * overflow
		 */
		System.out.println("Factorial");
		for (int i = 1; i < SomeTypes.MAX; i++) {
			int fact = factorial(i);
			// This is similar to how you format strings in Python.
			System.out.printf("%3d %16d\n", i, fact);
		}

		/* TODO in class: change the types and printf below to actually find and 
		 * print reciprocals. */


                /* Hint: you may find this cheat sheet useful 
                   http://alvinalexander.com/programming/printf-format-cheat-sheet */

		System.out.println("\n\nReciprocal");
		for (int i = 1; i < MAX; i++) {
			int recip = 1 / i;
			// This is similar to how you format strings in Python.
			System.out.printf("%3d %7d\n", i, recip);
		}
	}

	/**
	 * Returns the factorial of the given value, n.
	 * 
	 * @param n
	 * @return the factorial of the given value, n.
	 */
	private static int factorial(int n) {
		int product = 1;

		for (int j = 2; j <= n; j++) {
			product = product * j;
		}

		return product;
	}
}
