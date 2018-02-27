import java.lang.reflect.Array;
import java.util.Comparator;

public class BinaryHeap<T extends Comparable<? super T>> {

	private static final int INITIAL_CAPACITY = 5;
	private T[] heap;
	private Class<T> type;
	int size;
	private Comparator<T> comp;

	@SuppressWarnings("unchecked")
	public BinaryHeap(Class<T> type) {
		this.type = type;
		this.heap = (T[]) Array.newInstance(this.type, this.INITIAL_CAPACITY);
		this.size = 0;
		this.comp = new NaturalComparator();
	}

	public T deleteMin() {

		return null;
	}

	public void insert(T i) {
		size++;
		if (this.size  == heap.length) {
			resize();
		}
		int hole = ++size;
		heap[0]  = i;
		for (; i.compareTo(heap[hole/2]) < 0; hole /= 2) {
			heap[hole] = heap[hole/2];
		}
		heap[hole] = i;
	}

	private void resize() {
		// TODO Auto-generated method stub.
		
	}

	public void percolatedown() {

	}

	@Override
	public String toString() {
		return heap.toString();
	}

	private static class NaturalComparator<T extends Comparable<? super T>> implements Comparator<T> {
		@Override
		public int compare(T arg0, T arg1) {
			return arg0.compareTo(arg1);
		}
	}

	private static class ReverseComparator<T extends Comparable<? super T>> implements Comparator<T> {
		@Override
		public int compare(T arg0, T arg1) {
			return arg1.compareTo(arg0);
		}
	}
}
