import java.util.ArrayList;

public class ArrayListFunctions {

	/**
	 * 
	 * Write a function that takes an array of strings and returns an arraylist.
	 * The arraylist should contain only the strings in the original list that
	 * begin with 'A'
	 * 
	 * So arrayListOfAs({"Abc","foo","AAA", "bar"}) yields ["Abc","AAA"]
	 */
	public static ArrayList<String> arraylistOfAs(String[] strings) {
		ArrayList<String> output = new ArrayList<String>();
		for (int i = 0; i < strings.length; i++) {
			String alpha = strings[i];
			if (alpha.startsWith("A")) {
				output.add(strings[i]);
			}
		}
		return output;
	}

	/**
	 * Takes an arrayList of numbers in sorted (ascending) order and a number to
	 * add.
	 * 
	 * Adds the number in the correct place in the sorted list.
	 * 
	 * so insertIntoSorted([1,5,9],6) yields [1,5,6,9]
	 * 
	 */
	public static void insertIntoSorted(ArrayList<Integer> list, int number) {
		if (list.size() <= 0) {
			list.add(0, number);
		}
		for (int i = 0; i < list.size(); i++) {
			if (number < list.get(i)) {
				list.add(i, number);

			}
			}
		}

	/**
	 * Takes an arraylist of strings and removes all strings longer than 3
	 * characters.
	 * 
	 * So removeLongStrings(["a","xxxx","b","zzzzz"]) yields ["a","b"]
	 */
	public static void removeLongStrings(ArrayList<String> strings) {
		// TODO: Solve me
	}

}
