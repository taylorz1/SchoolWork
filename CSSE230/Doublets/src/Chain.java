import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * 
 * DONE: This class is a general implementation of a chain
 *
 * @author Zachary Taylor, John Lambrecht.
 *         Created Dec 19, 2016.
 */

public class Chain implements Iterable<String>{
	private LinkedHashSet<String> set;
	private int length;
	
	public Chain() {
		this.length = 0;
		this.set = new LinkedHashSet<String>();
	}
	
	public Chain(LinkedHashSet<String> oldSet, int length) {
		this.length = length;
		this.set = oldSet;
	}

	
	
	@Override
	public Iterator<String> iterator() {
		return this.set.iterator();
	}


//Generates a new chain without modifying this chain.
	public Chain addLast(String string) {
		LinkedHashSet<String> toPass = (LinkedHashSet<String>) this.set.clone();
		toPass.add(string);
		Chain toReturn = new Chain(toPass, this.length + 1);
		return toReturn;
	}



	public String getLast() {
		Iterator<String> iter = this.iterator();
		String toReturn = "";
		while(iter.hasNext()) {
			toReturn = iter.next();
		}
		return toReturn;
	}



	public boolean contains(String string) {
		return this.set.contains(string);
	}



	public int length() {
		return this.length;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		Iterator itt = this.iterator();
		for (int i = 0; i < this.length; i++) {
			String cur = (String) itt.next();
			if (i== this.length - 1) {
				sb.append(cur);
			} else {
				sb.append(cur + ", ");
			}
			
		}
		sb.append(']');
		
		return sb.toString();
	}

}