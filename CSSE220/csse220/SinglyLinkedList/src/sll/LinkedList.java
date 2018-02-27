package sll;

import java.util.NoSuchElementException;

/**
 * This class provides a basic implementation of a singly linked list. It's
 * motivated by the implementation in Big Java, 5e, ch. 16.1.
 * 
 * @author DONE Zachary Taylor on 10/30/2016.
 * @param <T>
 *            The type of elements in the list
 */
public class LinkedList<T> implements List<T>, Iterable<T> {

	private ListNode<T> first;
	// Note that in addition to a head (first) pointer
	// this list contains a last pointer. Be sure to
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
		// DONE 01 Implement the add(T x) method.
		if (this.first == null) {
			this.first = new ListNode<T>(x, null);
			this.last = this.first;
		} else {
			ListNode<T> current = this.last;
			current.setNext(new ListNode<T>(x));
			this.last = current.getNext();
		}
		return false;
	}

	@Override
	public int size() {
		// DONE 02 Implement the size() method.
		int size = 0;
		if (this.first == null) {
			return 0;
		}
		ListNode<T> current = this.first;
		while (current != null) {
			current = current.getNext();
			size++;
		}
		return size;
	}

	@Override
	public void add(int i, T x) throws IndexOutOfBoundsException {
		// DONE 03 Implement the add(int i, T x) method.
		// Note that this adds at the specific index
		if (this.size() == 0) {
			add(x);
			return;
		}
		if (i > this.size() + 1) {
			throw new IndexOutOfBoundsException("Cannot add to an imporper index");
		}
		ListNode<T> current = this.first;
		ListNode<T> next;
		ListNode<T> toAdd = new ListNode(x);
		int index = 1;
		if (i == 0) {
			// adding at the beginning;
			toAdd.setNext(this.first);
			this.first = toAdd;
			return;
		}
		while (index != i) {
			index++;
			current = current.getNext();
		}

		if (index == this.size()) {
			// adding after the last
			current.setNext(toAdd);
			toAdd.setNext(null);
			this.last = toAdd;
			return;
		}

		next = current.getNext();
		current.setNext(toAdd);
		toAdd.setNext(next);
	}

	@Override
	public boolean contains(T x) {
		// DONE 04 Implement the contains(T x) method.
		if (this.size() == 0) {
			return false;
		}
		ListNode<T> current = this.first;
		while (current.getElement() != x) {
			if (current == this.last) {
				return false;
			}
			current = current.getNext();
		}
		return true;
	}

	@Override
	public boolean remove(T x) {
		// DONE 05 Implement the remove(T x) method.
		ListNode<T> current = this.first;
		if (this.first == null) {
			return false;
		}
		ListNode<T> previous = current;
		while (current.getElement() != x) {
			previous = current;
			current = current.getNext();
			if (current == null) {
				return false;
			}
		}
		if (current == this.first) {
			this.first = current.getNext();
			return true;
		}
		previous.setNext(current.getNext());
		return true;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		// DONE 06 Implement the get(int index) method.
		if (index > this.size() || index < 0 || index == -0) {
			throw new IndexOutOfBoundsException("too far");
		}
		ListNode<T> current = this.first;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public int indexOf(T x) {
		// DONE 07 Implement the indexOf(T x) method.
		if (this.size() == 0) {
			return -1;
		}
		ListNode<T> current = this.first;
		int index = 0;
		while (current != null) {
			if (index > this.size()) {
				break;
			}
			if (current.getElement() == x) {
				return index;
			}
			current = current.getNext();
			index++;
		}
		return -1;
	}

	@Override
	public T set(int index, T element) throws IndexOutOfBoundsException {
		// DONE 08 Implement the set(int index, T element) method.
		if (this.size() == 0) {
			throw new IndexOutOfBoundsException("out of bounds");
		}

		ListNode<T> current = this.first;
		ListNode<T> previous = current;

		T ele = get(index);
		while (current.getElement() != ele) {
			previous = current;
			current = current.getNext();
		}
		previous.setNext(new ListNode<T>(element, current.getNext()));
		return ele;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is an iterator over this list.
	 * 
	 * @author DONE Zachary Taylor on 10/30/2016.
	 */
	private class LinkedListIterator implements Iterator<T> {

		private ListNode<T> current, previous, preprevious;
		private boolean firsttime, nextcalled;

		private LinkedListIterator() {
			this.current = null;
			this.previous = null;
			this.firsttime = false;
			this.preprevious = null;
			this.nextcalled = false;

		}

		@Override
		public boolean hasNext() {
			// DONE (?) 09 Implement the hasNext() method.
			if (size() == 0) {
				return false;
			}
			if (!firsttime) {
				this.current = first;
				this.firsttime = true;
			}
			return (this.current != null);
		}

		@Override
		public T next() throws NoSuchElementException {
			// DONE 10 Implement the next() method.
			if (!hasNext()) {
				throw new NoSuchElementException("Nothing to see");
			}
			if (this.previous == null) {
				this.previous = this.current;
			} else {
				this.previous = this.previous.getNext();
			}
			if (this.preprevious == null) {
				this.preprevious = this.previous;
			} else {
				this.preprevious = this.preprevious.getNext();
			}
			T ele = this.current.getElement();
			this.current = this.current.getNext();
			this.nextcalled = true;
			// Which is really the element of this.previous
			return ele;
		}

		@Override
		public void remove() {
			// DONE 11 Implement the remove() method.
			if (this.nextcalled == false) {
				throw new IllegalStateException();
			}
			if (this.previous == first) {
				first = first.getNext();
			} else {
				this.preprevious.setNext(this.current);
			}
			this.nextcalled = false;
		}
	}

}
