package sll;

import java.util.NoSuchElementException;

/**
 * This class provides a basic implementation of a singly linked list. It's
 * motivated by the implementation in Big Java, 5e, ch. 16.1.
 * 
 * @author TODO <YOUR_NAME> on <DATE>.
 * @param <T>
 *            The type of elements in the list
 */
public class LinkedList<T> implements List<T>, Iterable<T> {

	private ListNode<T> first;
	// Note that in addition to a head (first) pointer
	// this list contains a last pointer.  Be sure to
	// BOTH first and last up to date in all your code.
	private ListNode<T> last;

	/**
	 * Constructs a new, empty linked list.
	 */
	public LinkedList() {
		this.first = null;
		this.last = null;
	}

	@Override
	public String toString() {
		if (this.first == null) {
			return "[]";
		}
		String result = "[";
		ListNode<T> current = this.first;
		while (current != this.last) {
			result += (current.getElement() + ", ");
			current = current.getNext();
		}
		return result + current.getElement() + "]";
	}

	@Override
	public boolean add(T x) {
		// TODO 01 Implement the add(T x) method.

		return false;
	}

	@Override
	public int size() {
		// TODO 02 Implement the size() method.
		return -1;
	}

	@Override
	public void add(int i, T x) throws IndexOutOfBoundsException {
		// TODO 03 Implement the add(int i, T x) method.
                // Note that this adds at the specific index
		
	}

	@Override
	public boolean contains(T x) {
		// TODO 04 Implement the contains(T x) method.
		return false;
	}

	@Override
	public boolean remove(T x) {
		// TODO 05 Implement the remove(T x) method.

		return false;
	}


	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		// TODO 06 Implement the get(int index) method.
	
		return null;
	}


	@Override
	public int indexOf(T x) {
		// TODO 07 Implement the indexOf(T x) method.
		return -1;
	}

	@Override
	public T set(int index, T element) throws IndexOutOfBoundsException {
		// TODO 08 Implement the set(int index, T element) method.

		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is an iterator over this list.
	 * 
	 * @author TODO <YOUR_NAME> on <DATE>.
	 */
	private class LinkedListIterator implements Iterator<T> {

		private ListNode<T> current, previous;

		private LinkedListIterator() {
			this.current = null;
			this.previous = null;
		}

		@Override
		public boolean hasNext() {
			// TODO 09 Implement the hasNext() method.

			return false;
		}

		@Override
		public T next() throws NoSuchElementException {
			// TODO 10 Implement the next() method.

			return null;
		}

		@Override
		public void remove() {
			// TODO 11 Implement the remove() method.

		}
	}

}
