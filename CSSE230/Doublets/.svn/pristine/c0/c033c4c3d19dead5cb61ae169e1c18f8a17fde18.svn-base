
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 
 * DONE generates a bunch of potential links from a dictionary.
 *
 * @author Zachary Taylor, John Lambrecht Created Dec 19, 2016.
 */

public class Links {
	// This type of setup will let us sort sets of words by length
	// The set also has O(1) contains time and additionally iterates in order.
	HashMap<Integer, HashSet<String>> list = new HashMap<Integer, HashSet<String>>();

	public Links(String filename) {
		linksHelper(filename);
	}

	private void linksHelper(String filename) {
		// Populating links
		File file = new File(filename);
		Scanner scan = null;

		try {
			scan = new Scanner(file);
			while (scan.hasNext()) {
				String dict = scan.next();
				int length = dict.length();
				if (list.containsKey(length)) {
					HashSet<String> q = list.get(length);
					q.add(dict);
					list.put(length, q);
				} else {
					HashSet<String> newset = new HashSet<String>();
					newset.add(dict);
					list.put(length, newset);
				}
			}
			scan.close();
		} catch (FileNotFoundException exception) {
			// DONE Auto-generated catch-block stub.
			exception.printStackTrace();
		}

	}

	public HashSet<String> getCandidates(String input) {
		char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		int length = input.length();
		char[] charinput = input.toCharArray();
		char[] change = null;
		HashSet<String> toCheck = list.get(length);
		if (toCheck == null) {
			return null;
		}
		HashSet<String> toReturn = new HashSet<String>();
		// Set it up so we differ the word by a character at a time because I
		// love enhanced loops
		for (int i = 0; i < length; i++) {
			for (char token : alphabet) {
				if (token == charinput[i]) {
					continue;
				}
				change = charinput.clone();
				change[i] = token;
				String changee = String.valueOf(change);
				if (toCheck.contains(changee)) {
					toReturn.add(changee);
				}
			}

		}
		return toReturn.isEmpty() ? null : toReturn;
	}
}
