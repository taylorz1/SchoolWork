import java.util.ArrayList;

public class SmallProblems {

	/**
	 * Returns true if the string is the same string repeated twice.
	 * 
	 * For example: "BuffaloBuffalo" returns true "abab" returns true "abcab"
	 * returns false "abba" returns false "" returns true
	 * 
	 * @return true if it is a doubled String
	 */
	public static boolean isDoubled(String input) {
		if (input.length() % 2 != 0) {
			return false;
		}
		String test = input.substring(0, input.length() / 2);
		if (test.equals(input.substring(input.length() / 2, input.length()))) {
			return true;
		}
		return false;
	}

	/**
	 * Takes an array as a parameter. Returns a new array that is a copy of the
	 * 1st half of the original array. If the array is an odd size, the result
	 * should include the middle element.
	 * 
	 * For example: {3,2,1,9} yields {3,2} {99,-3,7,45,6} yields {99,-3,7} {7,0}
	 * yields {7} {2} yields {2} {} yields {}
	 * 
	 * @param array
	 * @return array that is 1st half of input array
	 */
	public static int[] firstHalf(int[] list) {
		if (list.length % 2 == 0) {
			int[] halfArray = new int[list.length / 2];
			for (int i = 0; i < list.length / 2; i++) {
				halfArray[i] = list[i];
			}
			return halfArray;

		}
		int[] halfArray = new int[list.length / 2 + 1];
		for (int i = 0; i < list.length / 2 + 1; i++) {
			halfArray[i] = list[i];
		}
		return halfArray;
	}

	/**
	 * Removes all the odd values from an int arraylist.
	 * 
	 * HINT: removing a value shortens the list, so indexes can get messed up.
	 * 
	 * For example: [0,1,2,3,4] becomes [0,2,4] [33,9,2,-5] becomes [2] [1,1,7]
	 * becomes [] [] becomes []
	 *
	 * @param list
	 */
	public static void removeOdds(ArrayList<Integer> list) {
		ArrayList<Integer> evens = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) % 2 != 0) {
				evens.add(list.get(i));
			}
		}
		list = evens;
	}

}
