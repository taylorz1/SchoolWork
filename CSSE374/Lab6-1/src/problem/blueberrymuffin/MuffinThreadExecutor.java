package problem.blueberrymuffin;

import java.awt.Desktop.Action;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MuffinThreadExecutor implements Executor {
	
	private BlockingQueue<Runnable> jobs = new LinkedBlockingQueue<Runnable>();
	private static MuffinThreadExecutor instance = new MuffinThreadExecutor();
	private List<RealThread> workerThreads = new LinkedList<>();
	
	private MuffinThreadExecutor() {
		while(RealThread.getTotalThreads() <= 3) {
			RealThread rt = new RealThread(new QueuePoller());
			this.workerThreads.add(rt);
			rt.start();
		}
	}

	@Override
	public void execute(Runnable command) {
		jobs.add(command);
	}
	
	synchronized Runnable getNextJob() {
		try {
			return this.jobs.poll(100, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// done Auto-generated catch block
			return null;
		}
	}
	
	public static MuffinThreadExecutor getInstance() {
//		if (MuffinThreadExecutor.instance != null) {
//			return MuffinThreadExecutor.instance;
//		} else {
//			return new MuffinThreadExecutor();
//		}
		return MuffinThreadExecutor.instance;
	}
	
	public void addThread(RealThread rThread) {
		this.workerThreads.add(rThread);
		rThread.start();
	}
	
	

}
