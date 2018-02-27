//TODO Implement the SumThread class
public class SumThread implements Runnable {

	private int[] data;
	private int startIndex;
	private int endIndex;
	private long result;

	public SumThread(int[] data, int startIndex, int endIndex) {
		this.data = data;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.result = 0;
	}

	@Override
	public void run() {
		for (int i = this.startIndex; i < this.endIndex; i++) {
			this.result += this.data[i];
		}
	}

	public long getResult() {
		return this.result;
	}

}
