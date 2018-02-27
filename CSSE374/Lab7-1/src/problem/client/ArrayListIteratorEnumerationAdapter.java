package problem.client;

import java.util.Enumeration;
import java.util.Iterator;

public class ArrayListIteratorEnumerationAdapter<T> implements java.util.Enumeration<T> {

	Iterator<T> iter;
	
	public ArrayListIteratorEnumerationAdapter(Iterator<T> iter) {
		this.iter = iter;
	}

	@Override
	public boolean hasMoreElements() {
		// TODO Auto-generated method stub
		return iter.hasNext();
	}

	@Override
	public T nextElement() {
		// TODO Auto-generated method stub
		return iter.next();
	}
}
