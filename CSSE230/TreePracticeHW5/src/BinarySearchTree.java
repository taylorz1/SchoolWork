/**
 * More Binary Tree practice problems. This problem creates BSTs of type
 * Integer: 1. Neither problem makes use of the BST ordering property; I just
 * found insert() to be a convenient way to build trees for testing. 2. I used
 * Integer instead of T since the makeTree method sets the data value of each
 * node to be a depth, which is an Integer.
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>.
 * @param <T>
 */

/*
 * TODO: 0 You are to implement the methods below. Use recursion!
 */
public class BinarySearchTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * This constructor creates a full tree of Integers, where the value of each
	 * node is just the depth of that node in the tree.
	 * 
	 * @param maxDepth
	 *            The depth of the leaves in the tree.
	 */
	public BinarySearchTree(int maxDepth) {
		// DONE: 1 Write this.
		// Hint: You may find it easier if your recursive helper method is
		// outside of the BinaryNode class.
		if (maxDepth == -1) {
			root = NULL_NODE;
			return;
		}
		root = updateTree(maxDepth, root, 0);
	}
	
	public BinaryNode updateTree(int maxdepth, BinaryNode node, int currentdepth) {
		if (currentdepth == maxdepth) {
			return new BinaryNode(maxdepth);
		}
		BinaryNode node2 = new BinaryNode(currentdepth);
		node2.right = updateTree(maxdepth, node2.right, currentdepth+1);
		node2.left = updateTree(maxdepth, node2.left, currentdepth+1);
		return node2;
	}

	public int getSumOfHeights() {
		// DONE. 2 Write this.
		// Can you do it in O(n) time instead of O(n log n) by avoiding repeated
		// calls to height()?
		if (root == NULL_NODE) {
			return -1;
		}
		return root.height().totalHeight;
	}

	// These are here for testing.
	public void insert(Integer e) {
		root = root.insert(e);
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}
	
	public class wrapper {
		
		int totalHeight;
		int nodeHeight;
		
		public wrapper(int totalHeight, int nodeHeight) {
			this.totalHeight = totalHeight;
			this.nodeHeight = nodeHeight;
		}
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public wrapper height() {
			// DONE Auto-generated method stub.
			if (this == NULL_NODE) {
				return new wrapper(0,-1);
			}
			wrapper one = this.right.height();
			wrapper two = this.left.height();
			int max = Math.max(one.nodeHeight, two.nodeHeight);
			return new wrapper(one.totalHeight + two.totalHeight + max + 1, max + 1);
		}

		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(data) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(data) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "[" + left.toStructuredString() + this.data
					+ right.toStructuredString() + "]";
		}

	}
}