package list;

/**
 * 
 * @author anderson
 * 
 * @param <T>
 *            Any Comparable type
 * 
 *            A linked list whose elements are kept in sorted order.
 */
public class SortedLinkedList<T extends Comparable<T>> extends
		DoublyLinkedList<T> {

	/**
	 * Create an empty list
	 * 
	 */
	public SortedLinkedList() {
		super();
	}
	
	private class InternalNode extends Node {
		@Override
		void buildString(StringBuilder sb) {
			sb.append(this.data);
				sb.append(',');
				this.next.buildString(sb);
			return;
		}
	}
	/**
	 * Creates a sorted list containing the same elements as the parameter
	 * 
	 * @param list
	 *            the input list
	 */
	public SortedLinkedList(DoublyLinkedList<T> list) {
		super();
		// DONE: finish implementing this constructor
		DLLIterator<T> j = list.iterator();
		while(j.hasNext()) {
			this.add(j.next());
		}
	}

	/**
	 * Adds the given element to the list, keeping it sorted.
	 */
	@Override
	public void add(T element) {
		// DONE: implement this method
		if (this.head.next == this.tail) {
			this.head.addAfter(element);
			return;
		}
		Node current = this.head;
		while(current.next != this.tail) {
			if (current.next.data.compareTo(element) > 0) {
				current.addAfter(element);
				return;
			}
			current = current.next;
		}
		current.addAfter(element);
		return;
	}

	@Override
	public void addFirst(T element) {
		// DONE: throw UnsupportedOperationException exception
		throw new UnsupportedOperationException();
	}

	@Override
	public void addLast(T element) {
		// DONE: throw UnsupportedOperationException exception
		throw new UnsupportedOperationException();
	}
}
