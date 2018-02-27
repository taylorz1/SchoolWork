package numberSequence;

public class Fibonacci implements Sequence{
	private int current, previous1;
	
	public Fibonacci() {
		this.current = 0;
		this.previous1 = 1;
	}

	@Override
	public int next() {
		int next = this.current + this.previous1;
		this.previous1 = this.current;
		this.current = next;
		return next;
	}

	@Override
	public void reset() {
		this.current = 1;
	}
}
