import java.util.ArrayList;
import java.util.HashMap;

/**
 * A few problems on using HashMaps.
 *
 * @author TODO <Add your name here>. Created Dec 15, 2013.
 */
public class MapProblems {

	/**
	 * Returns true if the array contains a duplicated element
	 * 
	 * This can be done with for loops, but use a map. It's okay if you don't
	 * use the "value" portion of the key-value pair for anything in this
	 * problem.
	 * 
	 * {1,2,3} yields false {1,2,1} yields true
	 * 
	 * @param input
	 * @return true if the array contains a duplicated element
	 */
	public static boolean hasDuplicates(int[] input) {
		HashMap<Integer, Integer> checking = new HashMap<Integer, Integer>();
		for (int i = 0; i < input.length; i++) {
			if (checking.containsKey(input[i])) {
				return true;

			}
			checking.put(input[i], 0);
		}
		return false;
	}

	/**
	 * There is a card game you play with lettered cards. In this game, your
	 * goal is to collect as many of the same card as you can.
	 * 
	 * To score your hand, look through your cards one at a time. If this is the
	 * first time you've seen this card, score 1 point. Otherwise, you score the
	 * number of that card you've seen thus far + 1.
	 * 
	 * So for example, the hand "ABAA" is worth 7 points. 1 for the first A 1
	 * for the first B 2 for the second A 3 for the third A
	 * 
	 * This function computes the score of the given hand.
	 * 
	 * @param hand
	 * @return the score of the given hand
	 */
	public static int computeScore(String hand) {
		HashMap<Character, Integer> myScore = new HashMap<Character, Integer>();
		HashMap<Character, Integer> occurance = new HashMap<Character, Integer>();
		for (int i = 0; i < hand.length(); i++) {
			if (myScore.containsKey(hand.charAt(i))) {
				int summation = myScore.get(hand.charAt(i)) + occurance.get(hand.charAt(i));
				occurance.put(hand.charAt(i), occurance.get(hand.charAt(i)) +1);
				myScore.put(hand.charAt(i), summation);

			} else {
				myScore.put(hand.charAt(i), 1);
				occurance.put(hand.charAt(i), 2);
			}
		}
		int sum = 0;
		for (char key : myScore.keySet()) {
			sum += myScore.get(key);
		}
		return sum;
	}

	/**
	 * Takes a HashMap of Integers to Strings and converts it to a HashMap of
	 * Strings to Integers.
	 * 
	 * For example:
	 * 
	 * {1->"A",2->"B",3->"C"} yields {"A"->1,"B"->2,"C"->3}
	 * 
	 * You can assume that original HashMap has no duplicates in its values.
	 * E.g. a map like {1->"A",2->"A"} does not exist.
	 * 
	 * @param input
	 *            a HashMap of Integers to Strings
	 * @return a corresponding HashMap of Strings to Integers.
	 * 
	 */
	public static HashMap<String, Integer> reverseMap(HashMap<Integer, String> input) {
		HashMap<String, Integer> output = new HashMap<String, Integer>();
		for (int key : input.keySet()) {
			output.put(input.get(key), key);
		}
		return output;
	}

	/**
	 * Imagine a set of cities connected by one-way highways. The map of cities
	 * to other cities is represented by a HashMap, where the city name is
	 * associated with other cities it has a road to.
	 * 
	 * So for example, a map like this:
	 * 
	 * {"A"->["B","C"],"B"->["D"],"C"->[],"D"->[]}
	 * 
	 * Represents cities connected like this:
	 * 
	 * A->B->D \ ->C
	 * 
	 * Note that there can be loops (e.g. there might be a road from A to B AND
	 * a road from B to A)
	 * 
	 * Write a function to determine if there is a way to get from one city to
	 * another. So in the above example:
	 * 
	 * canTravelTo(map,"A","D") returns true canTravelTo(map,"C","A") returns
	 * false canTravelTo(map,"B","A") returns false
	 * 
	 * This is a slightly tougher problem. Try it, then ask for a hint if you
	 * need it.
	 * 
	 * @param cities
	 * @param startCity
	 * @param endCity
	 * @return true if there is a way to get from one city to another; false
	 *         otherwise.
	 */
	public static boolean canTravelTo(HashMap<String, ArrayList<String>> cities, String startCity, String endCity) {

		return false;
	}

}
