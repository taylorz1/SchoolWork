package mode;

import java.util.HashMap;

public class IntArray {

	/**
	 * Computes the mode (the value that appears most often) of the data given.
	 * You may assume there will be no ties, so the mode is unique.
	 * You may also assume the given array is nonempty.
	 * @return
	 */
	public static int mode(int[] arr) {
		HashMap<Integer, Integer> frequency = new HashMap<Integer, Integer>();
		int max_value = 0;
		int max_freq = 0;
		for (int i = 0; i < arr.length; i++) { //O(n);
			if (frequency.containsKey(arr[i])) { //O(1);
				int value = frequency.get(arr[i]); //This is the old freq
				value++; //This is the new freq
				frequency.put(arr[i], value);
				if (value > max_freq) {
					max_value = arr[i];
					max_freq = value;
				}
			} else {
				frequency.put(arr[i], 1);
				if (max_value == 0) {
					max_value = arr[i];
					max_freq = 1;
				}
			}
		}
		return max_value;
	}
}
