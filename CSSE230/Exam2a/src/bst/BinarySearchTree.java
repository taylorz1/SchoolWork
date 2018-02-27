package bst;

import java.util.ArrayList;

/**
 *
 * Exam 2a. Tree methods.
 * 
 * @author
 */

/*
 * TODO: Directions: Implement the methods below. See the paper for details.
 */
public class BinarySearchTree {

	BinaryNode root;
	int onechild = 0;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17); 

	public BinarySearchTree() {
		root = NULL_NODE;
	}
	
	/**
	 * Counts the number of nodes in the tree that have only a single child.
	 */
	public int countOneChildParents() {
		// TODO: Implement this method!
		onechild = 0;
		if (root == NULL_NODE) {
			return 0;
		}
		root.countOneChildParents();
		return this.onechild;
	}

	/**
	 * Prunes (removes) all leaves in the tree.
	 */
	public void pruneLeaves() {
		// TODO: Implement this method!
		if (root == NULL_NODE) {
			return;
		}
		if (root.left == NULL_NODE && root.right == NULL_NODE) {
			root = NULL_NODE;
			return;
		}
		root.pruneLeaves(root);
	}

	/**
	 * Given two Integers, consider the path by which a binary search on the BST
	 * would search for these Integers. The method returns the value of the "branch point":
	 * i.e., the deepest common node of these two search paths. If both searches would be 
	 * unsuccessful and there were no branch points, then the method should return null. 
	 */
	public Integer branchPoint(Integer x, Integer y) {
		// TODO: Implement this method!
		if (x==y) {
			if(root.contains(x)) {
				return x;
			}
		}
		return root.branchPoint(x,y);
	}



	// The next methods are used by the unit tests
	public void insert(Integer e) {
		root = root.insert(e);
	}

	/**
	 * Feel free to call from tests to use to verify the shapes of your trees
	 * while debugging. Just remove the calls you are done so the output isn't
	 * cluttered.
	 * 
	 * @return A string showing a traversal of the nodes where children are
	 *         indented so that the structure of the tree can be determined.
	 * 
	 */
	public String toIndentString() {
		return root.toIndentString("");
	}


	public String toListString() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		root.toArrayList(al);
		return al.toString();
	}
	
	@Override
	public String toString() {
		return root.toString();
	}

	
	// /////////////// BinaryNode
	public class BinaryNode {

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public boolean contains(Integer x) {
			// TODO Auto-generated method stub.
			if (this == NULL_NODE) {
				return false;
			}
			int n = x.compareTo(this.data);
			if (n == 0) {
				return true;
			}
			if (n > 0) {
				return this.right.contains(x);
			}
			if (n < 0) {
				return this.left.contains(x);
			}
			return false;
		}

		public Integer branchPoint(Integer x, Integer y) {
			// DONE Auto-generated method stub.
			if (this != NULL_NODE) {
				int xcomp = this.data.compareTo(x);
				int ycomp = this.data.compareTo(y);
				if (xcomp != ycomp) {
					return this.data;
				}
				if (xcomp > 0) {
					return this.left.branchPoint(x, y);
				} else if (xcomp < 0) {
					return this.right.branchPoint(x, y);
				}
			}
			return null;
		}

		public void pruneLeaves(BinaryNode parent) {
			// DONE Auto-generated method stub.
			if (this != NULL_NODE) {
				int n = parent.data.compareTo(this.data);
				if (this.left == NULL_NODE && this.right == NULL_NODE) {
					if (n < 0) {
						parent.right = NULL_NODE;
						return;
					} else if (n > 0) {
						parent.left = NULL_NODE;
						return;
					}
				} else {
					this.right.pruneLeaves(this);
					this.left.pruneLeaves(this);
					return;
				}
			}
			
		}

		public void countOneChildParents() {
			// DONE Auto-generated method stub.
			BinaryNode curr = this;
			while (curr != NULL_NODE) {
				if (curr.left != NULL_NODE && curr.right == NULL_NODE) {
					onechild++;
					curr = curr.left;
					curr.countOneChildParents();
					break;
				} else if (curr.right != NULL_NODE && curr.left == NULL_NODE) {
					onechild++;
					curr = curr.right;
					curr.countOneChildParents();
					break;
				} else if (curr.left == NULL_NODE && curr.right == NULL_NODE) {
					return;
				} else {
					curr = curr.left;
					curr.countOneChildParents();
					curr = this.right;
					curr.countOneChildParents();
					break;
				}
			}
		}

		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e < data) {
				left = left.insert(e);
			} else if (e > data) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data + right.toString();
		}


		public void toArrayList(ArrayList<Integer> al) {
			if (this == NULL_NODE) {
				return;
			}
			left.toArrayList(al);
			al.add(this.data);
			right.toArrayList(al);
		}

		public String toIndentString(String indent) {
			if (this == NULL_NODE) {
				return "";
			}
			String myInfo = indent + String.format("%d\n", this.data);
			return myInfo + this.left.toIndentString(indent + "  ") + this.right.toIndentString(indent + "  ");
		}
	}

}