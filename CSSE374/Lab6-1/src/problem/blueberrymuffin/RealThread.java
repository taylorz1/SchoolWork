package problem.blueberrymuffin;

import javax.swing.JOptionPane;

/**
 * Simulates threading on the Blueberry device.
 * 
 * The Blueberry can only handle 4 threads.
 * Your computer can handle more than 4, so this class simulates that constraint for testing purposes.
 *
 */
public class RealThread {
	private Thread underlying;
	public RealThread(Runnable r) {
		underlying = new Thread(r); // using the JVM thread only to simulate the real threading code on the device.
	}

	// Do not change this code.
	public synchronized void start() {
		if(getTotalThreads() > 3){
			try{
				throw new IllegalStateException("Maximum allowed RealThread count exceeded.");
			}finally{
				JOptionPane.showMessageDialog(null, "Maximum allowed RealThread count exceeded. BLUEBERRY SIMULATION TERMINATED.", 
						"CRITICAL ERROR", JOptionPane.ERROR_MESSAGE);
				System.err.println("CRITICAL: Maximum allowed RealThread count exceeded. BLUEBERRY SIMULATION TERMINATED.");
				System.exit(1);
			}
		}
		underlying.start();
	}

	public static int getTotalThreads(){
		return Thread.currentThread().getThreadGroup().activeCount();
	}
	
	public static void printTotalThreads(){
		System.err.println(getTotalThreads());
	}
}
