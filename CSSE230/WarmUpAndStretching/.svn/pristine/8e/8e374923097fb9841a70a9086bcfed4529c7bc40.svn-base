package hardysTaxi;

import static hardysTaxi.TaxicabNumber.cube;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all
 * "taxicab numbers" that are less than n. These are represented as
 * TaxicabNumber objects: each includes the sum and two different ways of
 * writing that sum as a sum of two cubes.
 * 
 * @author anderson November, 2010. Edited by Zachary Taylor
 *
 */
public class Hardy {

	/**
	 * Returns floor of the cube root of n. Can you see why this method is
	 * useful for this problem? This is not a very efficient implementation.
	 * (Not required) Can you think of a more efficient approach?
	 * 
	 * @param n
	 *            a positive integer
	 * @return integer cube root of n
	 */
	public static int cubeRootFloor(int n) {
		// Very inefficient, but quick to write.
		int i = 0;
		while (cube(i) <= n)
			i++;
		return i - 1;
	}

	/**
	 * Computes and returns a sorted list of all taxicab numbers less than n.
	 * 
	 * @param n
	 *            a positive integer
	 * @return a List<TaxicabNumber> object. Each object contains the sum and
	 *         two different ways to write it as a sum of cubes.
	 */

	public static List<TaxicabNumber> hardySolutionsLessThan(int n) {
		List<TaxicabNumber> result = new ArrayList<TaxicabNumber>(); // You are
																		// to
																		// populate
																		// this
																		// list.
		int limit = cubeRootFloor(n);
		/* DONE: fill in the calculations */
		Integer num1 = 0;
		Integer num2 = 0;
		ArrayList<Integer> n1s = new ArrayList<>();
		HashMap<Integer, Integer[]> hash = new HashMap<>();

		for (int a = 0; a <= limit; a++) {
			for (int b = 0; b <= limit; b++) {
				for (int c = 0; c <= limit; c++) {
					for (int d = 0; d <= limit; d++) {
						int cube = a * a * a + b * b * b;
						int cube2 = c * c * c + d * d * d;
						if (cube == cube2) {
							if (!(a == c || a == d) && !(b == c || b == d)) {
								if (cube < n && cube2 < n) {
									Integer[] components = new Integer[4];
									components[0] = a;
									components[1] = b;
									components[2] = c;
									components[3] = d;
									hash.put(cube, components);
								}
							}
						}
					}
				}
			}
		}
		for (Integer cubic : hash.keySet()) {
			Integer[] g = hash.get(cubic);
			result.add(new TaxicabNumber(cubic, g[0], g[1], g[2], g[3]));
		}

		java.util.Collections.sort(result);
		return result;
	}

	/**
	 * main() is provided in case you want to test your code other than with
	 * unit tests. Just put try various arguments in the method call below.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(hardySolutionsLessThan(1730));
	}

}
