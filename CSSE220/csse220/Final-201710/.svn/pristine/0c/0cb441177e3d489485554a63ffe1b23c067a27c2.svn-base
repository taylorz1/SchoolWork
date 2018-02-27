package recursion;

public class RecursionProblems {

	/**
	 * Returns a version of the input string in which all pairs of adjacent duplicate
	 * elements have been separated by a hyphen.
	 *
	 * Your solution MUST use recursion in a natural way.
	 * 
	 *  Examples:
	 *  "food" --> "fo-od"
	 *  "balloon" --> "bal-lo-on"
	 *  "bookkeeper" --> "bo-ok-ke-eper"
	 *  "baaad" --> "ba-a-ad"
	 *  "nodups" --> "nodups"
	 *  "" --> ""
	 *  
	 * @param input
	 * @return
	 */
	public static String separateAdjacentDuplicates(String input) {
		// TODO: write this
		if (input.length() <= 2) {
			return input;
		}
		if (input.charAt(0) == input.charAt(1)) {
			return input.charAt(0) + "-" + separateAdjacentDuplicates(input.substring(1,input.length()));
		}
		return input.substring(0,1) + separateAdjacentDuplicates(input.substring(1,input.length()));
	}
}
