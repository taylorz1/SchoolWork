import java.util.HashMap;

public class MapAnd2DArrayProblems {
	
	/**
	 * Returns whether the 2-dimensional array has "bad luck."
	 * 
	 * A 2-D array that has bad luck has one or more rows where the
	 * elements add up to the value 13.  If this is the case, then 
	 * the array has "bad luck" and you should return
	 * true.  Otherwise, the array is fine and you should return 
	 * false.
	 * 
	 * For example, given the array:
	 * ({1,  4, 2, 6},
	 *  {33, 5, 6,  4},
	 *  {0,  4, 15, 9})
	 *  
	 * You should return True here because of the first row
	 * (indexed 0) because 1+4+2+6 = 13.  This means the array
	 * has bad luck. 
	 *  
	 * Given:
	 * ({1,  4, 2, 99, 0},
	 *  {33, 5, 6,  4, 12},
	 *  {0,  4, 16, 9, 100})
	 *  
	 *  You should return False since the array does not have bad 
	 *  luck (i.e., no rows totaling 13). 
	 *  
	 *  Note, columns adding to 13 do NOT 
	 *  indicate bad luck.  Also, the arrays can be of varying
	 *  size, the above are examples only and could have been
	 *  arrays of any size.
	 * 
	 * @param data - the input data
	 * @return True/False indicating if the array has bad luck
	 */
	public static boolean hasBadLuck(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			int sum = 0;
			for (int j = 0; j < data[0].length; j++) {
				sum+=data[i][j];
			}
			if (sum == 13) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Given an integer array of characters,
	 * return a map of strings that provide a histogram of
	 * the letters for each grade value.  They key of the map should
	 * be the grade itself
	 * 
	 * For example, you will be given an array similar to:
	 * ({z, z, a, c, c, d, z, b, z, c, z, d, z}) 
	 * 
	 * Given this array, you are to return an map of strings that
	 * correspond to the grades, giving one star (asterisk) for each 
	 * occurrence of the grade:
	 * 
	 * Key: {a="*", b="*", c="***", d="**", z="******"}
	 * 
	 * In this example, you can see that the letter a occurred once,
	 * as did the letter of b.  The letter c occurred three 
	 * times, etc. The letter q did not occur at all, so it's not 
	 * in the map.
	 * 
	 * @param grades
	 * @return map histogram of grades
	 */
	public static HashMap<Character,String> letterHistogram(char[] grades) {
		HashMap<Character, String> histogram = new HashMap<Character, String>();
		for (int i = 0; i < grades.length; i++) {
			if (histogram.containsKey(grades[i])) {
				histogram.put(grades[i], histogram.get(grades[i])+"*");
			} else {
				histogram.put(grades[i],"*");
			}
		}
		return histogram;
	}
}
