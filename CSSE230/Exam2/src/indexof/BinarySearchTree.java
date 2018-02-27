package indexof;

import java.util.NoSuchElementException;

/**
 * @author DONE: Zachary Taylor
 */
public class BinarySearchTree {
	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode();

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * Returns the index (in an inorder traversal) of the given value in the
	 * BST. If the value is not in the tree, throws a NoSuchElementException.
	 */
	public int indexOf(Integer value) {
		// DONE: Write me!
		BinaryNode temp = this.root;
		int index = this.root.left.size();
		while (true) {
			if (value > temp.data) {
				if (temp.right == NULL_NODE) {
					throw new NoSuchElementException();
				}
				temp = temp.right;
				//Update index.
				int mod = 0;
				if (temp.left != NULL_NODE) {
					mod = temp.left.size();
				}
				index = index + mod + 1;
			} else if (value < temp.data) {
				if (temp.left == NULL_NODE) {
					throw new NoSuchElementException();
				}
				temp = temp.left;
				int mod = 0;
				if (temp.right != NULL_NODE) {
					mod = temp.right.size();
				}
				index = index - (mod + 1);
				
			} else {
				return index;
			}
		}
	}

	public boolean isEmpty() {
		return root == NULL_NODE;
	}

	public void insert(Integer item) {
		root = root.insert(item);
	}

	private class BinaryNode {
		private Integer data;
		private BinaryNode left;
		private BinaryNode right;

		public BinaryNode() {
			this.data = null;
			this.left = null;
			this.right = null;
		}

		public int size() {
			// DONE Auto-generated method stub.
			if (this.left == NULL_NODE && this.right == NULL_NODE) {
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

		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode insert(Integer item) {
			if (this == NULL_NODE) {
				return new BinaryNode(item);
			}
			int comp = item.compareTo(data);
			if (comp < 0) {
				left = left.insert(item);
			} else if (comp > 0) {
				right = right.insert(item);
			}
			return this;
		}

	}

}
