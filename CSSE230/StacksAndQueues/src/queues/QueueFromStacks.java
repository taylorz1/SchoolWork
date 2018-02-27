package queues;

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueFromStacks<T> implements SimpleQueue {

	private Class<T> type;
	private Stack<Object> stack;
	private Stack<Object> stack2;
	private int size = 0;

	public QueueFromStacks() {
		// DONE Auto-generated constructor stub.
		this.stack = new Stack<>(); // The stack to add to
		this.stack2 = new Stack<>(); // The stack to flip to
	}

	@Override
	public void clear() {
		// DONE Auto-generated method stub.
		this.stack.clear();
		this.stack2.clear();
		this.size = 0;
	}

	@Override
	public void enqueue(Object item) {
		// DONE Auto-generated method stub.
		this.stack.push(item);
		this.size++;
	}

	@Override
	public Object dequeue() throws NoSuchElementException {
		// DONE Auto-generated method stub.
		Object k = null;
		if (this.size == 0) {
			throw (new NoSuchElementException());
		}
		for (int i = 0; i < size - 1; i++) {
			k = this.stack.pop();
			this.stack2.push(k);
		}
		k = this.stack.pop();
		for (int i = 0; i < size - 1; i++) {
			Object j = this.stack2.pop();
			this.stack.push(j);
		}
		this.size--;
		return k;
	}

	@Override
	public Object peek() throws NoSuchElementException {
		// DONE Auto-generated method stub.
		Object k = null;
		if (this.size == 0) {
			throw (new NoSuchElementException());
		}
		for (int i = 0; i < size; i++) {
			k = this.stack.pop();
			this.stack2.push(k);
		}
		for (int i = 0; i < size; i++) {
			Object j = this.stack2.pop();
			this.stack.push(j);
		}
		return k;
	}

	@Override
	public boolean isEmpty() {
		// DONE Auto-generated method stub.
		return this.size == 0;
	}

	@Override
	public int size() {
		// DONE Auto-generated method stub.
		return this.size;
	}

	@Override
	public boolean contains(Object item) {
		// done Auto-generated method stub.
		return this.stack.contains(item) || this.stack2.contains(item);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		Object k;
		if (!(size() == 0)) {
			for (int i = 0; i < this.size; i++) {
				k = this.stack.pop();
				this.stack2.push(k);
			}
			for (int i = 0; i < this.size; i++) {
				k = this.stack2.pop();
				this.stack.push(k);
				s.append(k + ", ");
			}
			s.delete(s.length() - 2, s.length());
		}
		s.append("]");
		return s.toString();
	}

	@Override
	public String debugString() {
		// DONE Auto-generated method stub.
		return null;
	}

}
