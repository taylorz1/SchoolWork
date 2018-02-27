package numberSequence;

public class PowersOfTwo implements Sequence{
	private int base, current;
	
	public PowersOfTwo() {
		this.base = 0;
		this.current = this.base;
	}

	@Override
	public int next() {
		if (this.current == 0) {
			this.current = 1;
			return 1;
		}
		this.current = this.current * 2;
		return this.current;
	}

	@Override
	public void reset() {
		this.current = this.base;
	}
}
