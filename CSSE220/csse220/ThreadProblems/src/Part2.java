/**
 * 
 * Imagine a calculation the requires 3 steps.
 * Step 1 must be finished before steps 2A & 2B start.
 * Step 2A & 2B can be run in parallel.
 *
 * Of course, as in part 1, multiple calculations should be 
 * run in parallel.
 * 
 * You'll probably want to reuse your inner class
 * from Part 1, but you'll also need a new inner
 * class for this one.
 * 
 * If you do this correctly, running a single job
 * should look like this:
 * 
 * Starting Buffalo job Step 1
 * Finishing Buffalo job Step 1
 * Starting Buffalo job Step 2A
 * Starting Buffalo job Step 2B
 * Finishing Buffalo job Step 2A
 * Finishing Buffalo job Step 2B
 * 
 * Running 2 jobs should look something like this:
 * 
 * Starting Job 2 Part 1
 * Starting Job 1 Part 1
 * Finishing Job 1 Part 1
 * Finishing Job 2 Part 1
 * Starting Job 2 Part 2A
 * Starting Job 2 Part 2B
 * Starting Job 1 Part 2B
 * Starting Job 1 Part 2A
 * Finishing Job 1 Part 2A
 * Finishing Job 2 Part 2A
 * Finishing Job 1 Part 2B
 * Finishing Job 2 Part 2B
 * 
 * When you finish, go on to the next file for part 2.
 * 
 * @author hewner.
 *         Created Nov 7, 2016.
 */
public class Part2 {
	
	public void runCalculations() throws InterruptedException {
		
		Jobber alpha = new Jobber("Job 1");
		Jobber beta = new Jobber("Buffalo");
		Thread a = new Thread(alpha);
		Thread b = new Thread(beta);
		a.start();
		b.start();
		
//		Calculation.doCalculation("Job 1 Part 1");
//		Calculation.doCalculation("Job 1 Part 2A");
//		Calculation.doCalculation("Job 1 Part 2B");
//		
//		Calculation.doCalculation("Job 2 Part 1");
//		Calculation.doCalculation("Job 2 Part 2A");
//		Calculation.doCalculation("Job 2 Part 2B");
	}
	
	public static void main(String[] args) throws InterruptedException {
		// not doing the work in main to make it easier
		// to use inner classes
		Part2 p = new Part2();
		p.runCalculations();

	}
private class JobThread implements Runnable{
		
		private String name;
		
		public JobThread(String name) {
			this.name = name;
			
		}

		@Override
		public void run() {
			Calculation.doCalculation(this.name);
		}
	}

private class Jobber implements Runnable {
	
	private String name;
	
	public Jobber(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		JobThread a = new JobThread(this.name+" Part 1");
		JobThread b = new JobThread(this.name+" Part 2A");
		JobThread c = new JobThread(this.name + " Part 2B");
		Thread ta = new Thread(a);
		Thread tb = new Thread(b);
		Thread tc = new Thread(c);
		ta.start();
		try {
			ta.join();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		tb.start();
		tc.start();
	}
	
	
}

}
