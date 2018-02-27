import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * A hash set implementation for Strings. Cannot insert null into the set. Other
 * requirements are given with each method.
 *
 * @author Matt Boutell and Zachary Taylor. Created Oct 6, 2014.
 */
public class StringHashSet {

	// The initial size of the internal array.
	private static final int DEFAULT_CAPACITY = 5;
	private Node[] array;
	private int size; //number of nodes.

	// You'll want fields for the size (number of elements) and the internal
	// array of Nodes. I also added one for the capacity (the length
	// of the internal array).

	private class Node {
		// DONE: Implement this class . These are just linked-list style
		// nodes, so you will need at least fields for the data and a reference
		// to the next node, plus a constructor. You can choose to use a
		// NULL_NODE at the end, or not. I chose not to do so this time.
		Node next = null;
		String data;
		

		public Node(String element) {
			this.data = element;
		}

		public boolean hasNext() {
			return !(this.next == null);
		}
	}

	/**
	 * Creates a Hash Set with the default capacity.
	 */
	public StringHashSet() {
		// Recall that using this as a method calls another constructor
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates a Hash Set with the given capacity.
	 */
	public StringHashSet(int initialCapacity) {
		initialize(initialCapacity);
	}

	private void initialize(int initialCapacity) {
		// DONE: Set the capacity to the given capacity, and initialize the
		// other fields.
		// Why did we pull this out into a separate method? Perhaps another
		// method needs to initialize the hash set as well? (Hint)
		this.array = new Node[initialCapacity];
		for (int i = 0; i < this.array.length; i++) {
			this.array[i] = null;
		}
		this.size = 0;
	}

	/**
	 * Calculates the hash code for Strings, using the x=31*x + y pattern.
	 * Follow the specification in the String.hashCode() method in the Java API.
	 * Note that the hashcode can overflow the max integer, so it can be
	 * negative. Later, in another method, you'll need to account for a negative
	 * hashcode by adding Integer.MAX_VALUE before you mod by the capacity
	 * (table size) to get the index.
	 *
	 * This method is NOT the place to calculate the index though.
	 *
	 * @param item
	 * @return The hash code for this String
	 */
	public static int stringHashCode(String item) {
		// DONE: Write this.
		return item.hashCode();
	}

	/**
	 * Adds a new node if it is not there already. If there is a collision, then
	 * add a new node to the -front- of the linked list.
	 * 
	 * Must operate in amortized O(1) time, assuming a good hashcode function.
	 *
	 * If the number of nodes in the hash table would be over double the
	 * capacity (that is, lambda > 2) as a result of adding this item, then
	 * first double the capacity and then rehash all the current items into the
	 * double-size table.
	 *
	 * @param item
	 * @return true if the item was successfully added (that is, if that hash
	 *         table was modified as a result of this call), false otherwise.
	 */
	public boolean add(String item) {
		// DONE: Write this
		int hashcode = stringHashCode(item);
		if (hashcode < 0) {
			hashcode = hashcode + Integer.MAX_VALUE;
		}
		int index = hashcode % this.array.length;
		if (this.array[index] == null) {
			this.array[index] = new Node(item);
		} else {
			Node top = this.array[index];
			Node toadd = new Node(item);
			Node temp = top;
			while (temp != null) {
				if (temp.data == toadd.data) {
					return false;
				}
				temp = temp.next;
			}
			toadd.next = top;
			this.array[index] = toadd;

		}
		this.size++;
		if (this.size > this.array.length*2) {
			resize(this.array);
		}
		return true;
	}

	private void resize(Node[] array2) {
		// DONE Auto-generated method stub.
		Collection<String> temp = new ArrayList<String>();
		initialize(array2.length*2);
		int index = 0;
		for (int i = 0; i < array2.length; i++) {
			Node curr = array2[i];
			while (curr != null) {
				temp.add(curr.data);
				index++;
				curr = curr.next;
			}
			index++;
		}
		this.addAll(temp);
	}

	/**
	 * Prints an array value on each line. Each line will be an array index
	 * followed by a colon and a list of Node data values, ending in null. For
	 * example, inserting the strings in the testAddSmallCollisions example
	 * gives "0: shalom hola null 1 bonjour null 2 caio hello null 3 null 4 hi
	 * null". Use a StringBuilder, so you can build the string in O(n) time.
	 * (Repeatedly concatenating n strings onto a growing string gives O(n^2)
	 * time)
	 * 
	 * @return A slightly-formatted string, mostly used for debugging
	 */
	public String toRawString() {
		// DONE: Write this
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.array.length; i++) {
			sb.append(i+": ");
			Node curr = this.array[i];
			while (curr != null) {
				sb.append(curr.data + " ");
				curr = curr.next;
			}
			if (curr == null) {
				sb.append("null\n");
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * Checks if the given item is in the hash table.
	 * 
	 * Must operate in O(1) time, assuming a good hashcode function.
	 *
	 * @param item
	 * @return True if and only if the item is in the hash table.
	 */
	public boolean contains(String item) {
		// DONE: Write this
		int hashcode = stringHashCode(item);
		if (hashcode < 0) {
			hashcode = hashcode + Integer.MAX_VALUE;
		}
		int index = hashcode % this.array.length;
		if (this.array[index] == null) {
			return false;
		}
		Node tocheck = this.array[index];
		if (tocheck.data.equals(item)) {
			return true;
		}
		while (tocheck.hasNext()) {
			tocheck = tocheck.next;
			if (tocheck.data.equals(item)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the number of items added to the hash table. Must operate in O(1)
	 * time.
	 *
	 * @return The number of items in the hash table.
	 */
	public int size() {
		// DONE: Write this
		return this.size;
	}

	/**
	 * @return True iff the hash table contains no items.
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Removes all the items from the hash table. Resets the capacity to the
	 * DEFAULT_CAPACITY
	 */
	public void clear() {
		// DONE: Write this. Should take 1 line if you read carefully above.
		initialize(DEFAULT_CAPACITY);
	}

	/**
	 * Removes the given item from the hash table if it is there. You do NOT
	 * need to resize down if the load factor decreases below the threshold.
	 * 
	 * @param item
	 * @return True If the item was in the hash table (or equivalently, if the
	 *         table changed as a result).
	 */
	public boolean remove(String item) {
		// DONE: Write this.
		if (this.contains(item)) {
			int hashcode = stringHashCode(item);
			if (hashcode < 0) {
				hashcode = hashcode + Integer.MAX_VALUE;
			}
			int index = hashcode % this.array.length;
			Node top = this.array[index];
			Node parent = null;
			while (true) {
				if (top.data.equals(item)) {
					this.size--;
					if (parent == null) {
						this.array[index] = top.next;
						return true;
					}
					parent.next = top.next;
					return true;
				}
				parent = top;
				top = top.next;
			}
		}
		return false;
	}

	/**
	 * Adds all the items from the given collection to the hash table.
	 *
	 * @param collection
	 * @return True if the hash table is modified in any way.
	 */
	public boolean addAll(Collection<String> collection) {
		// DONE: Write this.
		boolean changed = false;
		Iterator<String> noder = collection.iterator();
		while(noder.hasNext()) {
			changed = true;
			this.add(noder.next());
		}
		return changed;
	}

	/**
	 * 
	 * Challenge Feature: Returns an iterator over the set. Return the items in
	 * any order that you can do efficiently. Should throw a
	 * NoSuchElementException if there are no more items and next() is called.
	 * Should throw a ConcurrentModificationException if next() is called and
	 * the set has been mutated since the iterator was created.
	 *
	 * @return an iterator.
	 */
	public Iterator<String> iterator() {
		return null;
	}

	// Challenge Feature: If you have an iterator, this is easy. Use a
	// StringBuilder, so you can build the string in O(n) time. (Repeatedly
	// concatenating n strings onto a string gives O(n^2) time)
	// Format it like any other Collection's toString (like [a, b, c])
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < this.array.length; i++) {
			Node curr = this.array[i];
			while (curr != null) {
				sb.append(curr.data + ", ");
				curr = curr.next;
			}
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append("]");
		return sb.toString();
	}
}
