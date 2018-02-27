import java.util.ArrayList;

public class TwelveProblems {

	/**
	 * Given a particular point in the coordinate plane, compute the point's
	 * distance from the origin (0,0)
	 * 
	 * For example
	 * 
	 * distanceFromOrigin(-1,0) returns 1 distanceFromOrigin(77,77) returns
	 * 108.894 distanceFromOrigin(-3,-4) returns 5
	 * 
	 * Google for Java square root to figure out how to do it
	 */
	public static double distanceFromOrigin(double x, double y) {
		/*
		 * We can use the pythagorean theorem, a^2 + b^2 = c^2. x and y are a
		 * and b, so we are looking for the hypotenuse c, which is in this case,
		 * the distance from the origin
		 */
		double distOrigin = Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 0.5);
		return distOrigin;
	}

	/**
	 * Determine if a given positive integer has a 5 in the second rightmost
	 * digit
	 * 
	 * For example:
	 * 
	 * 5 returns false 52 returns true 151 returns true 30050 returns true 5000
	 * returns false
	 * 
	 * Hint: you'll want to use the modulus operator % and the division
	 * operation / See section 4.2.3 for details. Don't convert the number to a
	 * string!
	 * 
	 * Bonus Hint: you usually should not use an if statement to return a
	 * boolean
	 * 
	 * if (x % 2 == 0) { // checks if x is even return true; } else { return
	 * false; }
	 * 
	 * Instead, just return the result of the boolean test directly
	 * 
	 * return x % 2 == 0; //returns true or false
	 * 
	 * @param input
	 * @return
	 */
	public static boolean secondDigit5(int input) {
		/*
		 * First step is to divide the input by 10, leaving this as an integer
		 * will remove one order of magnitude so we are working with the second
		 * to last digit only and we are left with a truncated integer.
		 */
		int lowerOrderInput = input / 10;

		/*
		 * Numbers that are divisble by 5 may also be divisible by 10, so we
		 * check both scenarios. Check to see if divisible by 5, and then check
		 * to see if it is NOT divisible by 10 then return true or false.
		 */
		return (lowerOrderInput % 5 == 0 && !(lowerOrderInput % 10 == 0));
	}

	/**
	 * Determine if a given string ends with an uppercase letter.
	 * 
	 * For example:
	 * 
	 * endsWithUppercase("dog") returns false endsWithUppercase("doG") returns
	 * true endsWithUppercase("") returns false
	 * 
	 * Note that you can check if a particular char is uppercase like this:
	 * 
	 * char myChar = 'A'; if(Character.isUpperCase(myChar)) {
	 * System.out.println("uppercase!"); }
	 * 
	 * Note that the empty string is considered not to end with an uppercase
	 * letter.
	 * 
	 * Requires if statements, strings
	 */
	public static boolean endsWithUpperCaseLetter(String input) {
		// Getting input length; return false if 0
		int inputLnth = input.length();
		if (inputLnth == 0) {
			return false;
		}

		// getting the final letter and checking to see if it is uppercase
		char lastLtr = input.charAt(inputLnth - 1);

		if (Character.isUpperCase(lastLtr)) {
			return true;
		}

		return false;

	}

	/**
	 * Returns the first number taken to the power of the second number
	 * 
	 * For example pow(2,3) returns 2^3 or 8
	 * 
	 * DO NOT USE FUNCTIONS in Math (yes, Math.pow is how you would really do
	 * it) Instead, write this code yourself as practice.
	 * 
	 * Don't forget about negative powers! But both parameters are integers, so
	 * you don't need to worry about fractional powers.
	 * 
	 * Hint: if you want to get the reciprocal of an integer, do it like this
	 * 1.0/coolInt not like this 1/coolInt
	 * 
	 * Why? See section 4.2.3 in your book
	 * 
	 * Requires: for loops
	 */
	public static double pow(int num, int power) {
		double product = 1;
		// Checking sign of power
		if (power > 0) {
			// looping through and multiplying product, power times.
			for (int i = 0; i < power; i++) {
				product *= num;
			}
			return product;
		} else if (power < 0) {
			/*
			 * recipricol, do the same thing, but multiply power by -1 in for
			 * loop to correctly run the loop, and then return 1.0/product at
			 * the end
			 */
			for (int i = 0; i < power * -1; i++) {
				product *= num;
			}
			return 1.0 / product;
		} else {
			return 1;
		}
	}

	/**
	 * Given two strings of the same length, returns the index at which the
	 * strings first differ. If the strings are equal the function should return
	 * -1.
	 * 
	 * For example: firstDifference("abc", "axy") returns 1
	 * firstDifference("aby", "abz") returns 2 firstDifference("foo", "bar")
	 * returns 0 firstDifference("ninja", "ninja") returns -1
	 * firstDifference("","") returns -1
	 * 
	 * You don't need to worry about inputs of different lengths.
	 * 
	 * Hint: if you want to compare the two strings to see if they are equal.
	 * For example, something like this:
	 * 
	 * if(one.equals(two)) return -1;
	 * 
	 * Individual characters however, should be compared with == char a =
	 * one.charAt(0); char b = two.charAt(0); if(a == b) {
	 * System.out.println("First characters are equal"); }
	 * 
	 * Requires: for loops or while loops, strings
	 */
	public static int firstDifference(String one, String two) {
		char a, b;
		/*
		 * getting length of string, confirming that one and two aren't equal
		 * and they aren't empty
		 */
		int strLnth = one.length();
		if (one.equals(two))
			return -1;

		// checking through the indices of each string
		for (int i = 0; i < strLnth; i++) {
			a = one.charAt(i);
			b = two.charAt(i);

			// if a and b are different, return the index
			if (a != b)
				return i;
		}

		return 0;
	}

	/**
	 * Given one string, return the most common character.
	 * 
	 * Example: mostCommonCharacter("aaab") returns 'a'
	 * mostCommonCharacter("abcbcdc") returns 'c'
	 * 
	 * You can assume that your string will not be empty and that one character
	 * will be more common than all the others (i.e. there won't be a tie for
	 * the most common character)
	 * 
	 * Your solution should use a pair of nested for loops. You might be tempted
	 * to use something like python's dictionary here, but we'll save that till
	 * later when we cover hashmaps.
	 * 
	 * Requires: for loops, strings
	 */
	public static char mostCommonCharacter(String input) {
		/*
		 * Creating array lists to hold values of character frequency and actual
		 * characters
		 */
		ArrayList<Character> charHolder = new ArrayList<Character>();
		ArrayList<Integer> freqHolder = new ArrayList<Integer>();

		// Initializing placeholder, string length and index for frequency
		char placeholder;
		int lnth = input.length();
		int freqIndex;

		/*
		 * Loop will run through to check if the charHolder array contains a
		 * certain character from the string, if it doesn't, then add the
		 * character to the array, and add 1 to the freqHolder arrayList,
		 * otherwise, add 1 to the specified index
		 */
		for (int i = 0; i < lnth; i++) {
			placeholder = input.charAt(i);
			if (!charHolder.contains(placeholder)) {
				charHolder.add(placeholder);
				freqHolder.add(1);
			} else {
				freqIndex = charHolder.indexOf(placeholder);
				freqHolder.set(freqIndex, freqHolder.get(freqIndex) + 1);
			}
		}

		// Testing through the ArrayList to find the most frequent character
		char maxChar = charHolder.get(0);
		int maxVal = freqHolder.get(0);
		for (int i = 1; i < freqHolder.size(); i++) {
			if (freqHolder.get(i) > maxVal) {
				maxChar = charHolder.get(i);
				maxVal = freqHolder.get(i);
			}

		}

		return maxChar;
	}

	/**
	 * Finds the first number in an array divisible by 77 and returns it.
	 * 
	 * If no number is found, the function returns -1
	 * 
	 * Example: firstDivisibleBy77({88,24,154,77}) returns 154
	 * firstDivisibleBy77({5929, 280}) returns 5929
	 * firstDivisibleBy77({1,2,3,4}) returns -1
	 * 
	 * Don't forget about the modulus operator (%)
	 */
	public static int firstDivisibleBy77(int[] numbers) {
		// for loop to check through the array for the first number in the array
		int arraySize = numbers.length;

		for (int i = 0; i < arraySize; i++) {
			if (numbers[i] % 77 == 0) {
				return numbers[i];
			}
		}
		return -1;
	}

	/**
	 * Creates an array of all the powers of two, up to (and including) the
	 * given exponent starting at 2^0.
	 * 
	 * If the given exponent is less than zero, return an empty array. You can
	 * do this by saying "return new int[0];"
	 * 
	 * For example: powersOfTwo(3) returns {1,2,4,8} powersOfTwo(0) returns {1}
	 * powersOfTwo(-66) returns {}
	 * 
	 * Requires: arrays, for loops
	 */
	public static int[] powersOfTwo(int maxExponent) {
		// Creating an empty array if maxExponent is less than 0
		int[] empty = {};
		if (maxExponent < 0)
			return empty;

		// Initializing array, +1 because we have an initial value 1
		int[] powersOfTwo = new int[maxExponent + 1];
		powersOfTwo[0] = 1;

		// setting each index by the previous index multiplied by 2
		for (int i = 1; i <= maxExponent; i++) {
			powersOfTwo[i] = powersOfTwo[i - 1] * 2;
		}
		return powersOfTwo;
	}

	/**
	 * Given two arrays of integers that are the same length, returns a new
	 * array that contains the pairwise max of the corresponding elements of the
	 * original arrays (i.e. the larger of the two numbers in that slot in the
	 * original arrays)
	 * 
	 * For example: maxArray({2,10},{1,200}) returns {2,200} maxArray({-5, 60,
	 * 7},{-10,400,8}) returns {-5,400,8}
	 * 
	 * Requires: arrays, for loops
	 */
	public static int[] maxArray(int[] one, int[] two) {
		// assuming same length, initialize the maxArray
		int arrayLnth = one.length;
		int[] maxArray = new int[arrayLnth];

		// running the for loop to compare values of each array
		for (int i = 0; i < arrayLnth; i++) {
			if (one[i] > two[i]) {
				maxArray[i] = one[i];
			} else {
				maxArray[i] = two[i];
			}
		}
		return maxArray;
	}

	/**
	 * 
	 * Given two arrays, count the number of times the first array occurs in the
	 * second array. You can assume that the first array is shorter than the
	 * second.
	 * 
	 * For example: timesOccur({1,2}, {7,1,2,7,7,7,1,2,7}) returns 2
	 * timesOccur({1,1}, {1,1,1,3} returns 2 (in the 0th and 1st position)
	 * timesOccur({1,2}, {1,3,2,1} returns 0
	 * 
	 * Requires: arrays, nested for loops
	 */
	public static int timesOccur(int[] shorter, int[] longer) {
		// getting lengths of both arrays and making a counting variable;
		int shortLnth = shorter.length;
		int longLnth = longer.length;
		int counterInner;
		int counterOuter = 0;
		/*
		 * using for loops. Outer loop is to run through the indices of the
		 * longer array inner loop is to compare each value from the arrays
		 */

		for (int i = 0; i < longLnth; i++) {
			/*
			 * breaking if the current index of the longer array plus the length
			 * of the short array is greater than the length of the long array
			 * to avoid out of index error
			 */
			counterInner = 0;
			if (i + shortLnth > longLnth)
				break;

			// running through the inner loop, adding 1 to first if the same
			for (int j = 0; j < shortLnth; j++) {
				if (shorter[j] == longer[i + j]) {
					counterInner++;
				} else
					break;
			}
			/*
			 * If the length of the Inner counter is the same as the length of
			 * the shorter array, we know we got all successes so we can add 1
			 * to our outer counter
			 */
			if (counterInner == shortLnth)
				counterOuter++;
		}
		return counterOuter;
	}

	/**
	 * Given an arraylist of strings, return a new list where any time the word
	 * "double" appears in the original list it is doubled in the new list.
	 * 
	 * For example: doubleDouble(["foo","double"]) returns
	 * ["foo","double","double"] doubleDouble(["a","double","b","double","c"])
	 * returns ["a","double","double","b","double","double","c"]
	 * 
	 * Be careful not to modify the original list. Start by creating a new
	 * output list that holds the results! E.g.
	 * 
	 * ArrayList<String> output = new ArrayList<String>();
	 * 
	 * When you are comparing strings, be sure to use .equals and not == Eg.
	 * if(currentString .equals("double")) { stuff }
	 * 
	 */
	public static ArrayList<String> doubleDouble(ArrayList<String> input) {
		// Creating a new array to return, and a placeholder String
		ArrayList<String> doubleList = new ArrayList<String>();
		String currentString;

		// Looping through doubleDouble and adding the string to the new array
		for (int i = 0; i < input.size(); i++) {
			currentString = input.get(i);
			doubleList.add(currentString);

			// if double appears, add another double
			if (currentString.equals("double")) {
				doubleList.add("double");
			}
		}
		return doubleList;
	}

	/**
	 * Given a string, return all 3 character substrings of that string in an
	 * arraylist. That is, first it will return the 1st 3 characters of the
	 * string Then it will return the 2nd 3rd and 4th characters Then it will
	 * return the 3rd 4th and 5th characters etc.
	 * 
	 * For example: threeCharacterStrings("hello") returns ["hel","ell","llo"]
	 * threeCharacterStrings("foo") returns ["foo"]
	 * threeCharacterStrings(["ab"]) returns []
	 */
	public static ArrayList<String> threeCharacterStrings(String input) {
		// Creating a new array list and finding length of the input
		ArrayList<String> threeCharArray = new ArrayList<String>();
		int lnth = input.length();

		/*
		 * This series of loops will start at the first position and move
		 * forward until there are only three characters left in the input to
		 * analyze, we use the placeholder to hold the characters in the series
		 */
		String placeholder;
		for (int i = 0; i < lnth - 2; i++) {
			placeholder = "";

			for (int j = 0; j <= 2; j++) {
				placeholder += input.charAt(i + j);
			}

			threeCharArray.add(placeholder);
		}
		return threeCharArray;
	}

}
