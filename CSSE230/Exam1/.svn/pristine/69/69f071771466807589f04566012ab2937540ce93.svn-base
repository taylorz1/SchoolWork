package quack;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * A class to implement the Quack ADT.
 *
 * @author Nate Chenette and Zachary Taylor. Created Dec 15, 2016.
 */

public class Quack {
	SinglyLinkedList list; // Data must be stored here.
	boolean inStackMode; // true if Quack in stack mode, false if in queue mode.
	int size;
	ArrayList<Node> nodes = new ArrayList<Node>();

	public Quack() {
		list = new SinglyLinkedList();
		inStackMode = true; // Quack always starts in stack mode
		size = 0;
	}

	/**
	 * Converts and sets the Quack to stack mode. If already in stack mode, stay
	 * there.
	 */
	public void toStackMode() {
		// DONE Write me!
		if (this.inStackMode) {
			return;
		}
		Stack<String> alpha = new Stack<>();
		Node nd = list.head;
		while (nd.next != null) {
			alpha.push(nd.data);
			nd = nd.next;
		}
		alpha.push(nd.data);
		// Now pop off in reverse order.
		String toadd = alpha.pop();
		list.head = new Node(toadd, null);
		System.out.println(list.head.data);
		while (!alpha.isEmpty()) {
			String k = alpha.pop();
			System.out.println("going to insert " + k);
			insert(k);
		}
		this.inStackMode = true;

	}

	/**
	 * Converts and sets the Quack to queue mode. If already in queue mode, stay
	 * there.
	 */
	public void toQueueMode() {
		// DONE Write me!
		if (!this.inStackMode) {
			return;
		}
		this.inStackMode = false;
		Stack<String> alpha = new Stack<>();
		Node nd = list.head;
		while (nd.next != null) {
			alpha.push(nd.data);
			nd = nd.next;
		}
		alpha.push(nd.data);
		// Now pop off in reverse order.
		String toadd = alpha.pop();
		list.head = new Node(toadd, null);
		System.out.println(list.head.data);
		while (!alpha.isEmpty()) {
			String k = alpha.pop();
			System.out.println("going to insert " + k);
			insert(k);
		}

	}

	/**
	 * Insert an item into the Quack.
	 */
	public void insert(String newitem) {
		// DONE Write me!
		if (size == 0) {
			list.head = new Node(newitem, null);
			size++;
			nodes.add(list.head);
		} else {
			//If queue
			if (!inStackMode) {
			Node nd = list.head;
			while (nd.next != null) {
				nd = nd.next;
			}
			nd.next = new Node(newitem, null);
			size++;
			nodes.add(nd.next);
			} else {
				//if Stack
				Node nd = list.head;
				list.head = new Node(newitem, nd);
				size++;
			}
		}
	}

	/**
	 * Retrieve the next item from the Quack according to mode: FIFO if in queue
	 * mode LIFO if in stack mode
	 * 
	 * If the Quack is empty, throws a NoSuchElementException.
	 */
	public String retrieve() {
		// DONE Write me!
		if (size == 0) {
			throw (new NoSuchElementException());
		}
			Node nd = list.head.next;
			Node toreturn = list.head;
			list.head = nd;
			size--;
			return toreturn.data;
	}

	// ------- Code below this point should not be changed -------

	// For testing purposes
	public String toString() {
		return list.toString();
	}

	/**
	 * Basic singly linked list.
	 */
	public class SinglyLinkedList {
		Node head;

		SinglyLinkedList() {
			head = null;
		}

		// For testing/debugging purposes
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("[");
			Node nd = head;
			if (nd != null) {
				s.append(nd.data);
				nd = nd.next;
			}
			while (nd != null) {
				s.append(", ");
				s.append(nd.data);
				nd = nd.next;
			}
			s.append("]");
			return s.toString();
		}
	}

	/**
	 * Basic node.
	 */
	class Node {
		String data;
		Node next;

		Node(String item, Node nextNode) {
			data = item;
			next = nextNode;
		}
	}

}
