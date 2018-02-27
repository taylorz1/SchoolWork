package insertEBST;

/**
 * CSSE230 Final Exam.
 * 
 * @author <<<Nate Chenette and DONE: Zachary Taylor>>>.
 * @param <Integer>
 */
public class ExtendedBinarySearchTree {

	EBTNode root;


	public ExtendedBinarySearchTree() {
		root = new ExternalNode(null,null);
	}
	
	/**
	 * Insert an element into the EBST. Should replace an existing external node with an
	 * internal node (containing the given data) with two external nodes. The external nodes
	 * should be labeled with the appropriate upper and lower bounds for the corresponding 
	 * partition of the real number line. (null indicates an infinite bound.)
	 * @param x
	 */
	public void insert(Integer x) {
		// TODO: Write me!
		this.root = this.root.insert(x);
	}

	@Override
	public String toString() {
		String result = root.toString();
		return "[" + result.substring(0, result.length()-2) + "]";
	}


	public abstract class EBTNode {
		public abstract String toString(); // Polymorphic method. Has different definitions
		      							   // for internal and external nodes.

		public abstract EBTNode insert(Integer x);
	}

	public class InternalNode extends EBTNode {
		public Integer data;
		public EBTNode left;
		public EBTNode right;

		public InternalNode(Integer data, EBTNode left, EBTNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public String toString() {
			return left.toString() + data.toString() + ", " + right.toString();
		}

		@Override
		public EBTNode insert(Integer x) {
			// DONE Auto-generated method stub.
			if(x.compareTo(this.data) > 0) {
				this.right = this.right.insert(x);
				return this;
			}
			this.left = this.left.insert(x);
			return this;
		}

	}

	public class ExternalNode extends EBTNode {
		Integer lower;
		Integer upper;
		
		public ExternalNode(Integer low, Integer high) {
			this.lower = low;
			this.upper = high;
		}

		public String toString() {
			String lowerString = (lower == null)? "-Inf" : lower.toString();
			String upperString = (upper == null)? "Inf" : upper.toString();
			return "(" + lowerString + ":" + upperString + "), ";
		}

		@Override
		public EBTNode insert(Integer x) {
			// TODO Auto-generated method stub.
			EBTNode left = new ExternalNode(lower, x);
			EBTNode right = new ExternalNode(x, upper);
			return new InternalNode(x, left, right);
		}


	}
}