package problem.blueberrymuffin;

import java.util.concurrent.Executor;

public class VirtualThread{
	
	private Executor muffinThread;
	private Runnable r;

	public VirtualThread(Runnable r, Executor muffinThread) {
		this.muffinThread = muffinThread;
		this.r = r;
	}
	
	
	public void start() {
		muffinThread.execute(r);
	}

}
