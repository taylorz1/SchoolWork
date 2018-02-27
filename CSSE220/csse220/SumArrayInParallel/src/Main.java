/**
 * This class runs the SumArrayInParallel application.
 * 
 * @author wilkin. Updated by defoe on Nov 9, 2015
 *
 */
public class Main {

	public static int[] dataToSum;

	public static void main(String[] args) throws InterruptedException {
		int n = 200000000;
		int numThreads = 8;
		long startTime = System.currentTimeMillis();
		setData(n);
		long endTime = System.currentTimeMillis();

		System.out.println("Done creating array, took " + (endTime - startTime) + " ms");
		System.out.flush();

		startTime = System.currentTimeMillis();
		long c = sumSerial(dataToSum);
		endTime = System.currentTimeMillis();

		System.out.println("Done summing array serially, took " + (endTime - startTime) + " ms");
		System.out.println("result: " + c);
		System.out.flush();

		// TODO Create sumParallel and call it here
		startTime = System.currentTimeMillis();
		c = sumParallel(dataToSum, numThreads);
		endTime = System.currentTimeMillis();

		System.out.println("Done summing array parallel, took " + (endTime - startTime) + " ms");
		System.out.println("result: " + c);
		System.out.flush();
	}

	public static void setData(int n) {
		dataToSum = new int[n];
		for (int i = 0; i < n; i++) {
			double randSign = Math.random();
			double randVal = Math.random();
			int sign = (randSign < 0.5) ? -1 : 1;
			dataToSum[i] = (int) (randVal * n) * sign;
		}
	}

	public static long sumSerial(int[] data) {
		long result = 0;
		for (int i = 0; i < data.length; i++)
			result += data[i];
		return result;
	}

	// TODO Create a sumParallel method that takes in the
	// data array and the number of threads to use
	public static long sumParallel(int[] data, int numThreads) throws InterruptedException {
		int chunksize = data.length / numThreads;
		SumThread[] s = new SumThread[numThreads];
		long sum = 0;
		for (int i = 0; i < numThreads - 1; i++) {
			s[i] = new SumThread(data, i * chunksize, (i + 1) * chunksize);
		}
		s[numThreads - 1] = new SumThread(data, (numThreads - 1) * chunksize, data.length);
		Thread[] g = new Thread[numThreads];
		for (int i = 0; i < numThreads; i++) {
			g[i] = new Thread(s[i]);
			g[i].start();
		}
		for (Thread t : g) {
			t.join();
		}
		for (int i = 0; i < numThreads; i++) {
			sum += s[i].getResult();
		}
		return sum;
	}

}
