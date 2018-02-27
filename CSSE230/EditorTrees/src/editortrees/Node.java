package editortrees;

// A node in a height-balanced binary tree with rank.
// Except for the NULL_NODE (if you choose to use one), one node cannot
// belong to two different trees.

public class Node {

	enum Code {
		SAME, LEFT, RIGHT;
		// Used in the displayer and debug string
		public String toString() {
			switch (this) {
			case LEFT:
				return "/";
			case SAME:
				return "=";
			case RIGHT:
				return "\\";
			default:
				throw new IllegalStateException();
			}
		}
	}

	// The fields would normally be private, but for the purposes of this class,
	// we want to be able to test the results of the algorithms in addition to
	// the
	// "publicly visible" effects

	char element;
	Node left, right; // subtrees
	int rank; // inorder position of this node within its own subtree.
	Code balance;
	Node parent = EditTree.NULL_NODE; // You may want this field.
	// Feel free to add other fields that you find useful
	DisplayableNodeWrapper displayableNodeWrapper;

	// You will probably want to add several other methods
	public Node(char ch, Node parent) {
		this.element = ch;
		this.left = EditTree.NULL_NODE;
		this.right = EditTree.NULL_NODE;
		this.balance = Code.SAME;
		this.parent = parent;
	}

	public Node() {
		// TODO Auto-generated constructor stub.
		this.left = EditTree.NULL_NODE;
		this.right = EditTree.NULL_NODE;
		this.balance = Code.SAME;
		this.parent = EditTree.NULL_NODE;
	}

	public Node(Node node) {
		this.left = node.left.copy();
		this.right = node.right.copy();
		this.element = node.element;
		this.rank = node.rank;
		this.balance = node.balance;

	}

	public Node(String s, Node parent) {
		this.NodeHelper(s, parent);
	}
	
	public void NodeHelper(String s, Node parent) {
		int strlen = s.length();
		if (strlen == 2) {
			this.parent = parent;
			this.element = s.charAt(0);
			this.right = new Node(s.charAt(1),this);
			this.rank = 0;
			this.left = EditTree.NULL_NODE;
			this.balance = Code.RIGHT;
			return;
		} else if (strlen == 1) {
			this.element = s.toCharArray()[0];
			this.parent = parent;
			this.rank = 0;
			this.right = EditTree.NULL_NODE;
			this.left = EditTree.NULL_NODE;
			this.balance = Code.SAME;
			return;
		} else if (strlen == 0) {
			return;
		}
		this.parent = parent;
		this.element = s.charAt(strlen/2);
		String s2 = s.substring(0,strlen/2);
		String s3 = s.substring(strlen/2+1,strlen);
		this.rank = s2.length();
		this.left = new Node(s2, this);
		this.right = new Node(s3, this);
		if (s2.length() > s3.length()) {
			this.balance = Code.LEFT;
		} else if (s2.length() < s3.length()) {
			this.balance = Code.RIGHT;
		} else {
			this.balance = Code.SAME;
		}
	}

	// For the following methods, you should fill in the details so that they
	// work correctly
	public int height() {
		if (this == EditTree.NULL_NODE) {
			return -1;
		}
		int max;
		if (this.balance == Code.LEFT) {
			max = this.left.height();
		} else if (this.balance == Code.RIGHT) {
			max = this.right.height();
		} else {
			max = this.left.height();
		}
		return max + 1;
	}

	public int size() {
		if (this == EditTree.NULL_NODE) {
			return 0;
		}
		int size = 0;
		Node current = this;
		size += current.rank + 1;
		while (current.right != EditTree.NULL_NODE) {
			current = current.right;
			size += current.rank + 1;
		}
		return size;
	}

	public void toString(StringBuilder toReturn) {
		if (this == EditTree.NULL_NODE) {
			return;
		}
		this.left.toString(toReturn);
		toReturn.append(this.element);
		this.right.toString(toReturn);
	}

	@Override
	public String toString() {
		return this.element + " Rank: " + this.rank + " Balance: " + this.balance + "\nLeft: " + this.left.element
				+ " Right: " + this.right.element;
	}

	public void singleLeft() {
		// DONE Auto-generated method stub.
		// We're A
		Node b = this.right;
		Node a = this;
		a.right = b.left;
		a.right.parent = a;
		b.parent = a.parent;
		if (b.parent.right == a) {
			b.parent.right = b;
		} else {
			b.parent.left = b;
		}
		a.parent = b;
		b.left = a;
		a.balance = Code.SAME;
		b.balance = Code.SAME;
		b.rank = a.rank + 1 + b.rank;
	}

	public char get(int pos, int currentRank) {
		// DONE Auto-generated method stub.
		if (pos < 0 || this == EditTree.NULL_NODE) {
			throw (new IndexOutOfBoundsException());
		}
		if (currentRank == pos) {
			return this.element;
		}
		if (currentRank < pos) {
			return this.right.get(pos - this.rank - 1, this.right.rank);
		}
		return this.left.get(pos, this.left.rank);
	}

	public Node getNode(int pos, int currentRank) {
		if (this == EditTree.NULL_NODE || pos < 0) {
			throw (new IndexOutOfBoundsException());
		}
		if (currentRank == pos) {
			return this;
		}
		if (currentRank < pos) {
			return this.right.getNode(pos - this.rank - 1, this.right.rank);
		}
		this.rank--;
		return this.left.getNode(pos, this.left.rank);
	}

	public void toDebugString(StringBuilder toReturn) {
		if (this == EditTree.NULL_NODE) {
			return;
		}
		toReturn.append(String.valueOf(this.element) + Integer.toString(this.rank) + this.balance.toString() + ", ");
		this.left.toDebugString(toReturn);
		this.right.toDebugString(toReturn);
	}

	public int slowHeight() {
		if (this == EditTree.NULL_NODE)
			return -1;
		return Math.max(left.slowHeight(), right.slowHeight()) + 1;
	}

	public int slowSize() {
		if (this == EditTree.NULL_NODE) {
			return 0;
		}
		if (left == EditTree.NULL_NODE && right == EditTree.NULL_NODE) {
			return 1;
		}
		if (left == EditTree.NULL_NODE) {
			return 1 + right.slowSize();
		}
		if (right == EditTree.NULL_NODE) {
			return 1 + left.slowSize();
		}
		return (1 + left.size() + right.slowSize());
	}

	public DisplayableNodeWrapper getDisplayableNodePart() {
		// TODO Auto-generated method stub.
		return displayableNodeWrapper;
	}

	public boolean hasLeft() {
		// TODO Auto-generated method stub.
		return this.left != EditTree.NULL_NODE;
	}

	public boolean hasRight() {
		// TODO Auto-generated method stub.
		return this.right != EditTree.NULL_NODE;
	}

	public boolean hasParent() {
		return this.parent != EditTree.NULL_NODE;
	}

	public Node getParent() {
		// TODO Auto-generated method stub.
		return this.parent;
	}

	public Node getLeft() {
		// TODO Auto-generated method stub.
		return this.left;
	}

	public Node getRight() {
		// TODO Auto-generated method stub.
		return this.right;
	}

	public Object getBalance() {
		// TODO Auto-generated method stub.
		return this.balance;
	}

	public char getElement() {
		// TODO Auto-generated method stub.
		return this.element;
	}

	public int getRank() {
		// TODO Auto-generated method stub.
		return this.rank;
	}

	public addPosWrapper posNodeFinder(int pos, int curPos, boolean right) {
		if (pos <= curPos) {
			if (this.left == EditTree.NULL_NODE) {
				return new addPosWrapper(curPos, this);
			}
			if (!right) {
				curPos = this.left.rank;
			} else {
				curPos = curPos - this.rank + this.left.rank;
			}
			this.rank++;
			return this.left.posNodeFinder(pos, curPos, right);
		}
		if (this.right == EditTree.NULL_NODE) {
			return new addPosWrapper(curPos, this);
		}
		return this.right.posNodeFinder(pos, curPos + this.right.rank + 1, true);
	}

	public void singleRight() {
		// DONE Auto-generated method stub.
		// We're A
		Node b = this.left;
		Node a = this;
		int rankFactor = a.rank - b.rank - 1;
		a.left = b.right;
		a.left.parent = a;
		b.parent = a.parent;
		if (b.parent.left == a) {
			b.parent.left = b;
		} else {
			b.parent.right = b;
		}
		a.parent = b;
		b.right = a;
		a.balance = Code.SAME;
		b.balance = Code.SAME;
		a.rank = rankFactor;
	}

	public void doubleLeft() {
		// We are C
		Node a = this;
		Node parent = this.parent;
		Node.Code bBal = a.left.balance;
		boolean atreeExist = parent.left != EditTree.NULL_NODE;
		boolean cTreeExist = a.right != EditTree.NULL_NODE;
		this.singleRight();
		parent.singleLeft();

		if (bBal == Node.Code.RIGHT && atreeExist) {
			parent.balance = Node.Code.LEFT;
		} else if (bBal == Node.Code.LEFT && cTreeExist) {
			a.balance = Node.Code.RIGHT;
		}

	}

	public void doubleRight() {
		Node a = this;
		Node parent = this.parent;
		Node.Code bBal = a.right.balance;
		boolean atreeExist = parent.right != EditTree.NULL_NODE;
		boolean cTreeExist = a.left != EditTree.NULL_NODE;
		this.singleLeft();
		parent.singleRight();

		if (bBal == Node.Code.LEFT && atreeExist) {
			parent.balance = Node.Code.RIGHT;
		} else if (bBal == Node.Code.RIGHT && cTreeExist) {
			a.balance = Node.Code.LEFT;
		}
	}

	public Node copy() {
		if (this == EditTree.NULL_NODE) {
			return EditTree.NULL_NODE;
		}
		return new Node(this);
	}

	public void parentpointers(Node parent) {
		if (this == EditTree.NULL_NODE) {
			return;
		}
		this.parent = parent;
		this.left.parentpointers(this);
		this.right.parentpointers(this);
	}
}