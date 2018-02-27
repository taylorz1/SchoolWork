package problem.sprites;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class CompositeSpriteIterator implements Iterator<ISprite> {
	

	Stack<Iterator<ISprite>> stack = new Stack<Iterator<ISprite>>();
	
	public CompositeSpriteIterator(Iterator<ISprite> iterator) {
		stack.push(iterator);
	}

	@Override
	public boolean hasNext() {
		if(stack.empty()) {
			return false; 
		}
		Iterator<ISprite> iter = stack.peek();
		if(!iter.hasNext()) {
			stack.pop();
			return hasNext();
		} else {
			return true;
		}
	}

	@Override
	public ISprite next() {
		if(hasNext()) {
			Iterator<ISprite> iterator = stack.peek();
			ISprite sprite = iterator.next();
			stack.push(sprite.iterator());
			return sprite;
		} else {
			return null;
		}
	}

}
