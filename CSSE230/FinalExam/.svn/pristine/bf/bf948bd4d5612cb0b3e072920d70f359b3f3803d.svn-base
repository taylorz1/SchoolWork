package heapiterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryHeap {

	private ArrayList<Character> heap;
	int size; // number of items in the heap. Always 1 less than
				// heap.size() because of the burned 0th location.

	public BinaryHeap() {
		this.heap = new ArrayList<Character>();
		this.heap.add(null); // 0th location is unused; set it to null.
		this.size = 0;
	}

	/**
	 * Method to create a DepthFirstHeapIterator.
	 * 
	 * @return
	 */
	public Iterator<Character> depthFirstIterator() {
		return new DepthFirstHeapIterator();
	}

	/**
	 * Iterator that should traverse the heap in a depth-first manner (when the
	 * heap is viewed as a tree) analogous to that of a preorder iterator for a
	 * binary search tree.
	 * 
	 * @return
	 */
	public class DepthFirstHeapIterator implements Iterator<Character> {
		// DONE: implement this iterator!
		Stack<DepthWrapper> st = new Stack<DepthWrapper>();

		DepthFirstHeapIterator() {
			if (heap.get(1) != null) {
				st.push(new DepthWrapper(1, heap.get(1)));
				// Now we can track indexes and the object
			}
		}

		@Override
		public boolean hasNext() {
			// DONE Auto-generated method stub
			return !st.isEmpty();
		}

		@Override
		public Character next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			DepthWrapper curr = st.pop();
			int currR = curr.index * 2 + 1;
			int currL = curr.index * 2;
			if (currR <= size) {
				if (heap.get(currR) != null) {
					st.push(new DepthWrapper(currR, heap.get(currR)));
				}
			}
			if (currL <= size) {
				if (heap.get(currL) != null) {
					st.push(new DepthWrapper(currL, heap.get(currL)));
				}
			}
			return curr.car;
		}
	}

	public class DepthWrapper {
		Character car;
		int index;

		public DepthWrapper(int index, Character car) {
			this.index = index;
			this.car = car;
		}
	}

	public void insert(Character item) {
		size++;
		heap.add(item); // insert at the end of the heap. Must percolate up
		int loc = size;
		while (loc > 1) {
			int parentLoc = loc / 2;
			Character parent = heap.get(parentLoc);
			if (parent <= item) {
				break;
			}
			heap.set(loc, parent);
			loc = parentLoc;
		}
		heap.set(loc, item);
		return;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (heap.get(0) == null) {
			sb.append("null");
		}
		for (int i = 1; i <= size; i++) {
			sb.append(", ");
			sb.append(heap.get(i).toString());
		}
		sb.append("]");
		return sb.toString();
	}

}
