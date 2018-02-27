import java.util.Stack;

/**
 * 
 * DONE: This is a stack type implementation of the chainmanager interface.
 *
 * @author Zachary Taylor, John Lambrecht.
 *         Created Dec 19, 2016.
 */

public class StackChainManager extends ChainManager {
	
	Stack<Chain> chains = new Stack<Chain>();
	
	@Override
	public void add(Chain chain) {
		// DONE Auto-generated method stub.
		this.chains.push(chain);
	}

	@Override
	public Chain next() {
		// DONE Auto-generated method stub.
		if (!this.chains.isEmpty()) {
			return this.chains.pop();
		}
		return null;
	}

}
