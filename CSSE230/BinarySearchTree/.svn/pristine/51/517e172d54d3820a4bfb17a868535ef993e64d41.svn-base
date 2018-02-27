import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 * Implementation of most of the Set interface operations using a Binary Search
 * Tree
 *
 * @author Matt Boutell and Zachary Taylor.
 * @param <T>
 */

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {

	private BinaryNode root;
	int modifications = 0;

	// Most of you will prefer to use NULL NODES once you see how to use them.
	final BinaryNode NULL_NODE = new BinaryNode();

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	// For manual tests only
	void setRoot(BinaryNode n) {
		this.root = n;
	}

	// Not private, since we need access for manual testing.
	class BinaryNode {
		private T data;
		private BinaryNode left;
		private BinaryNode right;

		public BinaryNode() {
			this.data = null;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode(T element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public T getData() {
			return this.data;
		}

		public BinaryNode getLeft() {
			return this.left;
		}

		public BinaryNode getRight() {
			return this.right;
		}

		// For manual testing
		public void setLeft(BinaryNode left) {
			this.left = left;
		}

		public void setRight(BinaryNode right) {
			this.right = right;
		}

		public int size() {
			// DONE Auto-generated method stub.
			if (left == NULL_NODE && right == NULL_NODE) {
				return 1;
			}
			if (left == NULL_NODE) {
				return 1 + right.size();
			}
			if (right == NULL_NODE) {
				return 1 + left.size();
			}
			return (1 + left.size() + right.size());
		}

		public int height() {
			if (this == NULL_NODE)
				return -1;
			return Math.max(left.height(), right.height()) + 1;
		}

		public void toArrayList(ArrayList<T> list) {
			// DONE Auto-generated method stub.
			if (this == NULL_NODE) {
				return;
			}
			left.toArrayList(list);
			list.add(this.data);
			right.toArrayList(list);
		}

		public boolean containsNonBST(T i) {
			// DONE Auto-generated method stub.
			if (this == NULL_NODE) {
				return false;
			}
			if (this.data == i) {
				return true;
			}
			return left.containsNonBST(i) || right.containsNonBST(i);
		}

		public boolean insert(T i) {
			// DONE Auto-generated method stub.
			if (i.compareTo(this.data) > 0) {
				if (this.right == NULL_NODE) {
					this.right = new BinaryNode(i);
					return true;
				}
				return this.right.insert(i);
			}
			if (i.compareTo(this.data) < 0) {
				if (this.left == NULL_NODE) {
					this.left = new BinaryNode(i);
					return true;
				}
				return this.left.insert(i);
			}
			return false;
		}

		public boolean contains(T i) {
			// DONE Auto-generated method stub.
			if (this == NULL_NODE) {
				return false;
			}
			int n = i.compareTo(this.data);
			if (n == 0) {
				return true;
			}
			if (n > 0) {
				return this.right.contains(i);
			}
			if (n < 0) {
				return this.left.contains(i);
			}
			return false;
		}

		public BinaryWrapper remove(T i) {
			// DONE Auto-generated method stub.
			if (this == NULL_NODE) {
				return new BinaryWrapper(false, null);
			}
			int n = i.compareTo(this.data);
			if (n == 0) {
				if (this.left != NULL_NODE && this.right != NULL_NODE) {
					BinaryNode curr = this.left;
					BinaryNode par = this;
					boolean hasentered = false;
					while (curr.right != NULL_NODE) {
						hasentered = true;
						par = curr;
						curr = curr.right;
					}
					this.data = curr.data;
					if (hasentered) {
						par.right = NULL_NODE;
					} else {
						par.left = NULL_NODE;
					}
					return new BinaryWrapper(true, this);
				}
				if (this.left != NULL_NODE) {
					return new BinaryWrapper(true, this.left);
				}
				if (this.right != NULL_NODE) {
					return new BinaryWrapper(true, this.right);
				}
				return new BinaryWrapper(true, NULL_NODE);
				
			}
			if (n > 0) {
				BinaryWrapper returned = this.right.remove(i);
				if (returned.success) {
					this.right = returned.toChild;
					return new BinaryWrapper(true, this);
				}
				return new BinaryWrapper(false, null);
			}
			if (n < 0) {
				BinaryWrapper returned = this.left.remove(i);
				if (returned.success) {
					this.left = returned.toChild;
					return new BinaryWrapper(true, this);
				}
				return new BinaryWrapper(false, null);
			}
			return null;
		}
		
	}
	class BinaryWrapper {
		public boolean success;
		public BinaryNode toChild;
		
		BinaryWrapper(boolean success, BinaryNode toChild) {
			this.success = success;
			this.toChild = toChild;
		}
	}

	public int size() {
		// DONE Auto-generated method stub.
		if (root == NULL_NODE) {
			return 0;
		}
		return root.size();
	}

	// DONE: Implement your 3 iterator classes here, plus any other inner helper
	// classes you'd like.

	public int height() {
		// DONE Auto-generated method stub.
		if (root == NULL_NODE) {
			return -1;
		}
		if (root.left == NULL_NODE && root.right == NULL_NODE) {
			return 0;
		}
		return 1 + Math.max(this.root.left.height(), this.root.right.height());
	}

	ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<>();
		root.toArrayList(list);
		return list;
	}

	public Iterator<T> inefficientIterator() {
		// DONE Auto-generated method stub.

		return new ArrayListIterator(this);
	}

	public class ArrayListIterator implements Iterator<T> {
		ArrayList<T> list;
		private int modification_count = modifications;
		int pos;
		T toremove;

		public ArrayListIterator(BinarySearchTree<T> tree) {
			// DONE Auto-generated constructor stub.
			list = tree.toArrayList();
			pos = 0;
		}

		@Override
		public boolean hasNext() {
			// DONE Auto-generated method stub.
			return pos < list.size();
		}

		@Override
		public T next() {
			// DONE Auto-generated method stub.
			if (!hasNext()) {
				throw (new NoSuchElementException());
			}
			if (modification_count != modifications) {
				throw (new ConcurrentModificationException());
			}
			toremove = list.get(pos++);
			return toremove;
		}
		
		@Override
		public void remove() {
			if (toremove == null) {
				throw (new IllegalStateException());
			}
				BinarySearchTree.this.remove(toremove);
				toremove = null;
		}

	}

	public boolean insert(T i) {
		// DONE Auto-generated method stub.
		if (i == null) {
			throw new IllegalArgumentException();
		}
		if (root == NULL_NODE) {
			root = new BinaryNode(i);
			modifications++;
			return true;
		}
		modifications++;
		return root.insert(i);
	}

	public Iterator<T> preOrderIterator() {
		// DONE Auto-generated method stub.
		return new PreOrderIterator();
	}

	public class PreOrderIterator implements Iterator<T> {
		Stack<BinaryNode> st = new Stack<BinaryNode>();
		private int modification_count = modifications;
		T toremove;
		PreOrderIterator() {
			if (root != NULL_NODE) {
				st.push(root);
			}
		}

		@Override
		public boolean hasNext() {
			// DONE Auto-generated method stub.
			return !st.isEmpty();
		}

		@Override
		public T next() {
			// DONE Auto-generated method stub.
			if (!hasNext()) {
				throw (new NoSuchElementException());
			}
			if (this.modification_count != BinarySearchTree.this.modifications) {
				throw (new ConcurrentModificationException());
			}
			BinaryNode curr = st.pop();
			if (curr.right != NULL_NODE) {
				st.push(curr.right);
			}
			if (curr.left != NULL_NODE) {
				st.push(curr.left);
			}
			this.toremove = curr.data;
			return curr.data;
		}
		@Override
		public void remove() {
			if (toremove == null) {
				throw (new IllegalStateException());
			}
				BinarySearchTree.this.remove(toremove);
				toremove = null;
		}
	}

	public boolean isEmpty() {
		// DONE Auto-generated method stub.
		return root == NULL_NODE;
	}

	public boolean containsNonBST(T i) {
		// DONE Auto-generated method stub.
		if (root == NULL_NODE) {
			return false;
		}
		return root.containsNonBST(i);
	}

	@Override
	public String toString() {
		return this.toArrayList().toString();
	}

	public Object[] toArray() {
		// DONE Auto-generated method stub.
		return this.toArrayList().toArray();
	}

	public Iterator<T> iterator() {
		// DONE Auto-generated method stub.

		return new inoriterator();
	}

	public class inoriterator implements Iterator<T> {
		Stack<BinaryNode> st = new Stack<BinaryNode>();
		private int modification_count = modifications;
		T toremove;
		inoriterator() {
			if (root != NULL_NODE) {
				BinaryNode curr = root;
				while (curr != NULL_NODE) {
					st.push(curr);
					curr = curr.left;
				}
			}
		}

		@Override
		public boolean hasNext() {
			// DONE Auto-generated method stub.
			return !st.isEmpty();
		}

		@Override
		public T next() {
			// DONE Auto-generated method stub.
			if (!hasNext()) {
				throw (new NoSuchElementException());
			}
			if (modification_count != modifications) {
				throw (new ConcurrentModificationException());
			}
			BinaryNode curr = st.pop();
			T data = curr.data;
			if (curr.right != NULL_NODE) {
				curr = curr.right;
				while (curr != NULL_NODE) {
					st.push(curr);
					curr = curr.left;
				}
			}
			toremove = data;
			return data;
		}
		
		@Override
		public void remove() {
			if (toremove == null) {
				throw (new IllegalStateException());
			}
				BinarySearchTree.this.remove(toremove);
				toremove = null;
		}
	}

	public boolean contains(T i) {
		return root.contains(i);
	}

	public boolean remove(T i) {
		if (i == null) {
			throw new IllegalArgumentException();
		}
		BinaryWrapper toParent = this.root.remove(i);
		if (toParent.success) {
			this.root = toParent.toChild;
			modifications++;
		}
		return toParent.success;
	}
}
