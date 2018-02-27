package queues;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A circular queue that grows as needed.
 * 
 * @author Matt Boutell and Zachary Taylor, Chase Horne
 * @param <T>
 */
public class GrowableCircularArrayQueue<T> implements SimpleQueue<T> {
	// DONE: Declare this class to implement SimpleQueue<T>, then add the
	// missing methods (Eclipse will help).
	// DONE: The javadoc for overridden methods is in the SimpleQueue interface.
	// Read it!

	private static final int INITIAL_CAPACITY = 5;

	private boolean empty = true;
	private int beginning = 0;
	private int end = 0;
	private T[] array;
	private Class<T> type;

	/**
	 * Creates an empty queue with an initial capacity of 5
	 * 
	 * @param type
	 *            So that an array of this type can be constructed.
	 */
	@SuppressWarnings("unchecked")
	public GrowableCircularArrayQueue(Class<T> type) {
		this.type = type;
		// This is a workaround due to a limitation Java has with
		// constructing generic arrays.
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
	}

	/**
	 * Displays the contents of the queue in insertion order, with the
	 * most-recently inserted one last, in other words, not wrapped around. Each
	 * adjacent pair will be separated by a comma and a space, and the whole
	 * contents will be bounded by square brackets. See the unit tests for
	 * examples.
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		int index = this.beginning;
		if (!isEmpty()) {
			while (true) {
				if (index == array.length) {
					index = 0;
				}
				s.append(array[index] + ", ");
				if (index == end) {
					break;
				}
				index++;
			}
		s.delete(s.length() - 2, s.length());
		}
		s.append("]");
		return s.toString();
	}

	@Override
	public void clear() {
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.beginning = 0;
		this.end = 0;
		this.empty = true;
	}

	@Override
	public void enqueue(T item) {
		// DONE Auto-generated method stub.
		if (isEmpty()) {
			this.empty = false;
			this.array[end] = item;
			return;
		}
		if (size() + 1 > array.length) {
			grow();
		}
		this.end++;
		if (this.end == array.length) {
			this.end = 0;
		}
		this.array[end] = item;

	}

	private void grow() {
		// DONE Auto-generated method stub.
		int k = this.array.length;
		T[] q = (T[]) Array.newInstance(this.type, k * 2);
		int index = this.beginning;
		int i = 0;
		while (true) {
			if (index == this.array.length) {
				index = 0;
			}
			q[i] = this.array[index];
			if (index == this.end) {
				break;
			}
			i++;
			index++;
		}
		this.beginning = 0;
		this.end = k - 1;
		this.array = q;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		// DONE Auto-generated method stub.
		if (isEmpty()) {
			throw (new NoSuchElementException("The array is empty!"));
		}
		T toReturn = this.array[this.beginning];
		if (size() == 1) {
			this.empty = true;
			this.beginning = 0;
			this.end = 0;
		} else {
			this.beginning++;
			if (this.beginning > this.array.length) {
				this.beginning = 0;
			}
		}
		return toReturn;
	}

	@Override
	public T peek() throws NoSuchElementException {
		// TODO Auto-generated method stub.
		if (isEmpty()) {
			throw (new NoSuchElementException("The array is empty!"));
		}
		T toReturn = this.array[this.beginning];
		return toReturn;
	}

	@Override
	public boolean isEmpty() {
		// DONE Auto-generated method stub.
		return this.empty;
	}

	@Override
	public int size() {
		// DONE Auto-generated method stub.
		int j;
		if (!isEmpty()) {
			if (beginning > end) {
				j = array.length - beginning + end + 1;
			} else {
				j = end - beginning + 1; // Size is inclusive
			}
		} else {
			j = 0;
		}

		return j;
	}

	@Override
	public boolean contains(T item) {
		// DONE Auto-generated method stub.
		int index = this.beginning;
		while (true) {
			if (index == this.array.length) {
				index = 0;
			}
			if (this.array[index].equals(item)) {
				return true;
			}
			if (index == this.end) {
				break;
			}
			index++;
		}
		return false;
	}

	@Override
	public String debugString() {
		// TODO Auto-generated method stub.
		return null;
	}

}