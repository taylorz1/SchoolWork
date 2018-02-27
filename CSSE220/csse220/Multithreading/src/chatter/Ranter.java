package chatter;

/**
 * Shouts (to the console) at random intervals
 * 
 * @author Curt Clifton, modified by Buffalo
 */
public class Ranter implements Runnable{
	private static final int RANGE = 5000;
	private static final int MINIMUM = 250;

	private final String message;

	/**
	 * Creates a ranter with the given message.
	 * 
	 * @param message
	 */
	public Ranter(String message) {
		this.message = message;
	}

	/**
	 * Prints the message and then sleeps.  
	 * You shouldn't need to modify this method.
	 * 
	 * @throws InterruptedException
	 */
	
	@Override
	public void run() {
		try {
			while (true) {
				printMessageAndSleepRandomAmountofTime();
			}
		} catch (InterruptedException excetion){
			/*
			 * This Runnable object just exits when interrupted, so there isn't
			 * anything to do here.  Sometimes threads will use _very_ long sleep
			 * times, with interrupts used to wake them up and do something.  That
			 * is, we don't always exit when interrupted.
			 */
		}
	}
	
	public void printMessageAndSleepRandomAmountofTime() throws InterruptedException {
		System.out.println(this.message);
		Thread.sleep(getRandomSleepTime());
	}

	private long getRandomSleepTime() {
		return (long) (Math.random() * RANGE + MINIMUM);
	}

}
