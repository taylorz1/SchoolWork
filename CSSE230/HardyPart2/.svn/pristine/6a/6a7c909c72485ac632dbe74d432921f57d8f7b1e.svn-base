package hardysTaxi;

import java.util.PriorityQueue;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all "taxicab
 * numbers that are less than n. These are represented as TaxicabNumber objects,
 * which include the sum and two different ways of writing that as a sum of two
 * cubes.
 * 
 * @author Claude Anderson.
 * 
 */
public class Hardy {
	/**
	 * Find the nth Hardy number (start counting with 1, not 0) and the numbers
	 * whose cubes demonstrate that it is a Hardy number.
	 * 
	 * @param n
	 * @return the nth Hardy number
	 */
	public static long nthHardyNumber(int n) {
		PriorityQueue<Long> hardies = new PriorityQueue<Long>();
		HashSet<Long> lookupTable = new HashSet<Long>();
		PriorityQueue<Long> numbers = new PriorityQueue<Long>();
		// I think there's a better data structure
		// TreeSet<Long> potentials = new TreeSet<Long>();

		long a = 2, b = 1;
		long next = 0;
		long temp = 0;
		long temp2 = 0;

		int count = 0;
		while (true) {

			next = cube(a+1,1);
			
			temp = cube(a,1);
			
			if (!hardies.isEmpty()) {
				// Remove low values that won't appear again to save space in
				// hashset
				while (hardies.peek() < temp) {
					lookupTable.remove(hardies.poll());
				}
				// Needs to be sorted
				while (!numbers.isEmpty() && numbers.peek() < temp && count != n) {
					temp2 = numbers.poll();
					count++;
				}

				if (count == n) {
					return temp2;
				}
			}

			// col is a, row is b
			for (b = 1; b < a; b++) { // Go through all b's less than a
				temp = cube(a, b);
				// Not a Hardy Number
				if (!lookupTable.contains(temp)) {
					if (temp > next) {
						hardies.offer(temp);
						lookupTable.add(temp);
					}
				}
				// Hardy Number
				else {
					numbers.offer(temp);
					hardies.remove(temp);
					lookupTable.remove(temp);
				}
			}
			a++;
		}
	}

	private static long cube(long a, long b) {
		return a * a * a + b * b * b;
	}

}
