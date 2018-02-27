
public class StringProbs {

	/**
	 * 
	 * If the sentence ends with "!", this function should return a string with
	 * all letters uppercase. If the sentence does not end with "!",. the
	 * function return the original string.
	 * 
	 * For example:
	 * 
	 * "hello!" becomes "HELLO!" "hello?" becomes "hello?"
	 * "I am excited to be here!" becomes "I AM EXCITED TO BE HERE!" "boring"
	 * becomes "boring"
	 * 
	 */
	public static String uppercaseIfExclaimation(String sentence) {
		// TODO: the code provided has a bug, fix it
		//
		// HINT: it might help to look up the toUpperCase documentation in the
		// JavaDocs:
		//
		// https://docs.oracle.com/javase/7/docs/api/
		//
		// (it'll be under "String")
		//
		// BONUS HINT: You should bookmark that URL!
		if (sentence.charAt(sentence.length() - 1) == '!') {
			String result = sentence.toUpperCase();
			return result;
		}
		return sentence;
	}

	/**
	 * 
	 * Uppercases the first 3 characters of the string.
	 * If the string has less than 3 characters, uppercases them all.
	 * 
	 * For example:
	 * 
	 * "hello" becomes "HELlo"
	 * "aaaaaaaa" becomes "AAAaaaaa"
	 * "q" becomes "Q"
	 */
	public static String uppercaseThree(String input) {
		// HINT: You'll want to use the functions substring and length here
		// look them up in the java docs!
		if (input.length() >= 3) {
			String substring = input.substring(0,3);
		//	String sub_cap_string = substring.toUpperCase()
		//	String string_final = sub_cap_string.concat(input.substring(3,input.length()));
		//	return string_final;
		}
		String substring = input.toUpperCase();
		return substring;
	}

	/**
	 * 
	 * Returns true if the first character matches the last character.
	 * 
	 * For example: "abca" returns true "abcd" returns false "a" returns true ""
	 * returns false
	 */
	public static boolean firstMatchesLast(String input) {
		return false;
	}

	/**
	 * Takes 2 strings, returns true if the first 4 characters match If either
	 * string has less than 4 characters, returns false
	 * 
	 * For example: "Xaaabb" & "Xaaacccc" returns true "aaaabb" & "aaacccc"
	 * returns false "abc" & "abc" returns false
	 */
	public static boolean first4Match(String one, String two) {
		if ((one.length() < 4 || two.length() < 4)) {
			return false;
		}
		return (one.substring(0, 4).equals(two.substring(0, 4)));
	}
}
