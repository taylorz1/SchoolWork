package chatter;

/**
 * This is the main starting point for the Chatter example.
 *
 * @author Curt Clifton.
 *         Created Nov 9, 2009.
 */
public class Main {

	/**
	 * Starts the chatter.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) {
		// TODO: Experiment with different rates of chattering.
		// TODO: What happens if the rates are the same?
		Runnable r1 = new Chatter("foo", 60);
		Runnable r2 = new Chatter("bar", 120);
		Runnable r3 = new Ranter("FOO");
		Runnable r4 = new ShortTalker("Help me break free", 3);
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		Thread t4 = new Thread(r4);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		// TODO 1: Modify Ranter.java so it can be run in 
		// its own thread too, then call it here
		
		// TODO 2: Write the class ShortTalker, then add it as
		// a class as well.  (A description of how ShortTalker
		// should work is in the class itself)
		
		System.out.println("***Main function finished!***");
	}

}
