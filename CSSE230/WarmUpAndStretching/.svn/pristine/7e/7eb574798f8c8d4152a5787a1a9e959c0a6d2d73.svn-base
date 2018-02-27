package priorityQueue;

import java.util.ArrayList;

/**
 * An implementation of the Priority Queue interface using an array list.
 * 
 * @author Matt Boutell and <<DONE: Zachary Taylor>>>. Created Mar 29, 2014.
 * 
 * @param <T>
 *            Generic type of PQ elements
 */
public class ArrayListMinPQ<T extends Comparable<T>> {
	// Requirement: You must use this instance variable without changes.
	private ArrayList<T> items;

	private int arraysize;

	public ArrayListMinPQ() {
		// DONE: implement
		items = new ArrayList<T>();
	}

	public T findMin() {
		// This is also known as peekMin
		// DONE: implement
		if (arraysize == 0) {
			return null;
		}
		return items.get(size() - 1);
	}

	public T deleteMin() {
		// DONE: implement
		if (size() == 0) {
			return null;
		}
		arraysize--;
		return items.remove(arraysize);
	}

	public void insert(T item) {
		// DONE: implement
		int size = this.size();
		if (size == 0) {
			items.add(item);
			arraysize++;
			return;
		}
		if (size == 1) {
			int bool = items.get(0).compareTo(item);
			if (bool > 0) {
				items.add(item);
			} else {
				items.add(0, item);
			}
			arraysize++;
			return;
		}
		int j;
		if (items.get(0).compareTo(item) < 0) {
			items.add(0, item);
			arraysize++;
			return;
		}
		for (j = 0; j < size - 1; j++) {
			if (items.get(j).compareTo(item) >= 0 && items.get(j + 1).compareTo(item) <= 0) {
				break;
			}

		}
		items.add(j + 1, item);
		arraysize++;
	}

	public int size() {
		return this.arraysize;
	}

	public boolean isEmpty() {
		// DONE: implement
		return arraysize == 0;
	}

	public void clear() {
		// DONE: implement
		items.clear();
		arraysize = 0;
	}
}
