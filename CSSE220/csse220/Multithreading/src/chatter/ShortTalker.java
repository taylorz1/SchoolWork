package chatter;

/**
 * This runnable class should print:
 * 
 * Message 1 of 3
 * 
 * wait 2 seconds
 * 
 * Message 2 of 3
 * 
 * wait 2 seconds
 * 
 * Message 3 of 3....I'm exiting forever
 * 
 * and then it should exit
 * 
 */
public class ShortTalker implements Runnable{

	//Your awesome code goes here
	private final String message;
	private final int messages;
	private int repeats;

	/**
	 * Constructs an object to print the given message at the given rate.
	 * 
	 * @param message
	 * @param messagesPerMinute
	 */
	public ShortTalker(String message, int messages) {
		this.message = message;
		this.messages = messages;
		this.repeats = 1;
	}

	@Override
	public void run() {
		try {
			/*
			 * Main "brains" inside an infinite loop, so the thread keeps
			 * running:
			 */
			while (true) {
				// Work of the thread can include calls to other methods:
				if (this.repeats >= this.messages) {
					System.out.println("Printing message " + this.repeats + " of " + this.messages + ": " + this.message + "....I'm exiting forever");
					break;
				}
				System.out.println("Printing message " + this.repeats + " of " + this.messages + ": " + this.message);
				this.repeats++;

				/*
				 * After the work is done, put the thread to sleep so other
				 * threads can work. (Or the CPU can rest if no other threads
				 * are active.)
				 */
				Thread.sleep(2000);
			}
		} catch (InterruptedException exception) {
			/*
			 * This Runnable object just exits when interrupted, so there isn't
			 * anything to do here.  Sometimes threads will use _very_ long sleep
			 * times, with interrupts used to wake them up and do something.  That
			 * is, we don't always exit when interrupted.
			 */
		}
	}


}
