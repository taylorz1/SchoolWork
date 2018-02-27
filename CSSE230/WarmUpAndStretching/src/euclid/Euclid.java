package euclid;

/**
 * 
 * Is a method of finding the GCD of two input numbers.
 *
 * @author Zachary Taylor. Created Dec 6, 2016.
 */
public class Euclid {
	/**
	 * Implementation requirement: must do recursively, as given in the spec.
	 * 
	 * @param a
	 *            First integer
	 * @param b
	 *            Second integer
	 * @return The greatest common divisor of a and b using Euclid's recursive
	 *         algorithm.
	 */
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

}
