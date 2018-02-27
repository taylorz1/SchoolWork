import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * DONE: This class is the implementation of a queue type chianmanager.
 *
 * @author Zachary Taylor, John Lambrecht.
 *         Created Dec 19, 2016.
 */

public class QueueStackManager extends ChainManager {
	Queue<Chain> chains = new LinkedList<Chain>();
	
	@Override
	public void add(Chain chain) {
		// DONE Auto-generated method stub.
		this.chains.add(chain);
		
	}

	@Override
	public Chain next() {
		// DONE Auto-generated method stub.
		return this.chains.poll();
	}

}
