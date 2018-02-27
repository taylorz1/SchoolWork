package anagram;

import java.util.ArrayList;

/**
 * This utility class can test whether two strings are anagrams.
 *
 * @author Claude Anderson.
 * Edited by Zachary Taylor
 */
public class Anagram {

	/**
	 * We say that two strings are anagrams if one can be transformed into the
	 * other by permuting the characters (and ignoring case).
	 * 
	 * For example, "Data Structure" and "Saturated Curt" are anagrams, as are
	 * "Elvis" and "Lives".
	 * 
	 * @param s1
	 *            first string
	 * @param s2
	 *            second string
	 * @return true iff s1 is an anagram of s2
	 */
	public static boolean isAnagram(String s1, String s2) {
		ArrayList<Character> chars = new ArrayList<>();
		ArrayList<Character> chars2 = new ArrayList<>();
		if (s1.length() == s2.length()) {
			String s1L = s1.toLowerCase();
			String s2L = s2.toLowerCase();
			for (int i = 0; i < s1.length(); i++) {
				chars.add(s1L.charAt(i));
				chars2.add(s2L.charAt(i));
			}
			if (chars.containsAll(chars2)) {
				return true;
			}
		}
		return false;
	}
}
