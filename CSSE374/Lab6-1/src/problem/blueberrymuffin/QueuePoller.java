package problem.blueberrymuffin;

public class QueuePoller implements Runnable{

	public void run() {
		MuffinThreadExecutor e = MuffinThreadExecutor.getInstance();
		while(true) {
			Runnable r = e.getNextJob();
			if (r != null) {
				r.run();
			} else {
				return;
			}
		}
	}
}
