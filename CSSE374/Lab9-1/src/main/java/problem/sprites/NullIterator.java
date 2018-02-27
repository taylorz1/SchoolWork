package problem.sprites;

import java.util.Iterator;

public class NullIterator implements Iterator<ISprite> {

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public ISprite next() {
		return null;
	}

}
