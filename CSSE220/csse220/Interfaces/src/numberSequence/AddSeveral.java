package numberSequence;

public class AddSeveral implements Sequence{

	private int current, add;
	
	public AddSeveral(int several) {
		this.current = 0;
		this.add = several;
	}

	@Override
	public int next() {
		if (this.current == 0) {
			this.current = 1;
			return 1;
		}
		this.current += this.add;
		return this.current;
	}

	@Override
	public void reset() {
		this.current = 1;
	}
}
