import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Contains the general algorithm to search for doublets.
 *
 * @author John Lambrecht, Zachary Taylor Created Dec 17, 2016.
 */
public class Doublets {
	// DONE: implement this class.
	// Here we set up fields that other methods in this class will use.
	Links link;
	ChainManager chainmanager = null;
	Chain completedchain = null;
	String end;
	private int next = 0;
	int size = 0;
	int msize = 0;

	public Doublets(Links object) {
		this.link = object;
		// A very long print section.
		System.out.println("Welcome to Doublets, a game of \"verbal torture.\"");
		Scanner scan = new Scanner(System.in);
		while (true) {
			this.completedchain = null;
			this.chainmanager = null;
			System.out.println("Enter starting word: ");
			String start = scan.nextLine();
			int startL = start.length();

			System.out.println("Enter ending word: ");
			String end = scan.nextLine();
			int endL = end.length();

			if (startL != endL) {
				System.out.println("Start and end length must agree! Please try again.");
				continue;
			}
			HashSet<String> toCheck = object.list.get(start.length());
			if (!toCheck.contains(start)) {
				System.out.println("The word \"" + start + "\" is not valid! Please try again.");
				continue;
			}
			if (!toCheck.contains(end)) {
				System.out.println("The word \"" + end + "\" is not valid! Please try again.");
				continue;
			}

			System.out.println("Enter chain manager (s: stack, q: queue, x: exit");
			String chainm = scan.nextLine();

			if (chainm.equals("x")) {
				break;
			} else if (chainm.equals("s")) {
				this.chainmanager = new StackChainManager();
			} else if (chainm.equals("q")) {
				this.chainmanager = new QueueStackManager();
			} else if (chainm.equals("p")) {
				this.chainmanager = null;
			} else {
				System.out.println("invalid character entered! Please try again.");
				continue;
			}
			Chain toPrint = getChain(start, end);
			if (toPrint != null) {
				System.out.println(toPrint.toString());
			} else {
				System.out.println("No doublet chain exists from " + start + " to " + end);
				continue;
			}
			int length = toPrint.length();
			System.out.println("Length: " + length);
			System.out.println("Candidates: " + this.next);
			System.out.println("Max size " + this.msize);
		}
		scan.close();
	}

	// This method will get all the chains that we require for a given start and
	// end word
	// Essentially it handles all the work that will be done
	private Chain getChain(String start, String end) {
		// Here we basically do the first step as it is the only unique one
		this.end = end;
		Chain toReturn = new Chain();
		toReturn = toReturn.addLast(start);
		if (start.equals(end)) {
			return toReturn;
		}
		this.chainmanager.add(toReturn);
		Chain test = this.chainmanager.next();
		// We preform every single chain operation. Well, Basically.
		while (test != null) {
			chainsgenerator(test);
			if (this.completedchain != null) {
				return this.completedchain;
			}
			test = this.chainmanager.next();
			if (this.size > this.msize) {
				this.msize = this.size;
			}
			// Just keeping track of max stack size.
			this.size--;
			this.next++;
		}
		return null;
	}

	// Helper method for the while loop, as it needs to generate an iterator and
	// candidates list
	private void chainsgenerator(Chain input) {
		String last = input.getLast();
		HashSet<String> candidates = this.link.getCandidates(last);
		// Basically we're at the end of a chain if candidates is null
		if (candidates != null) {
			Iterator<String> iterator = candidates.iterator();
			for (int i = 0; i < candidates.size(); i++) {
				String toadd = iterator.next();
				if (input.contains(toadd)) {
					continue;
				}
				Chain tosub = input.addLast(toadd);
				if (toadd.equals(this.end)) {
					this.completedchain = tosub;
					break;
				}
				this.chainmanager.add(tosub);
				this.size++;
			}
		} else {
			return;
		}
	}

	// The first step is getting the dictionary and generating a doublet.
	public static void main(String[] args) {
		System.out.println("Enter dictionary: ");
		Scanner scan = new Scanner(System.in);
		String dict = scan.nextLine();
		Links link = new Links(dict);
		Doublets doublet = new Doublets(link);
		scan.close();
	}
}
