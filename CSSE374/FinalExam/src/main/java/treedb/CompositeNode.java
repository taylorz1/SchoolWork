package treedb;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class CompositeNode extends AbstractNode implements ICompositeNode {
	Map<String, INode> elements;
	
	public CompositeNode() {
		super();
		this.elements = new LinkedHashMap<>();
	}

	@Override
	public Map<String, INode> getElements() {
		return Collections.unmodifiableMap(elements);
	}
	
	@Override
	public INode get(String key) {
		return this.elements.get(key);
	}

	@Override
	public INode put(String key, INode node) {
		node.setParent(this);
		node.setKey(key);
		node.registerObserver(this);
		
		INode oldNode = this.elements.put(key, node);

		if(oldNode != null && !node.equals(oldNode)) {
			oldNode.setParent(null);
			oldNode.setKey(null);
		}
		
		return oldNode;
	}

	@Override
	public Object getData() {
		return null;
	}

	@Override
	public void setData(Object data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public INode remove(String key) {
		INode removed = this.elements.remove(key);
		if(removed != null) {
			removed.setParent(null);
			removed.setKey(null);
		}
		this.notifyObserver(key, 1);
		return removed;
	}

	@Override
	public Collection<INode> removeAll(String key) {
		final Collection<INode> removedNodes = Lists.newArrayList();

		// Base case
		INode removed = this.remove(key);
		if(removed != null)
			removedNodes.add(removed);
		
		// Recursive step
		this.elements.values().forEach(node -> {
			removedNodes.addAll(node.removeAll(key));
		});
		
		return removedNodes;
	}
	
	@Override
	public INode searchUUID(String uuid) {
		// Depth-First implementation of uuid search
		if(this.UUIDcache.containsKey(uuid)) {
			return this.UUIDcache.get(uuid);
		}
		for(INode node: this.elements.values()) {
			if(node.getUUID().equals(uuid)) {
				this.UUIDcache.put(uuid, node);
				return node;
			}

			// Recursively check down the subtree
			INode result = node.searchUUID(uuid);
			if(result != null) {
				this.UUIDcache.put(uuid, result);	
				return result;
			}
		}
		
		// Exhausted all subtree nodes, but no result found
		return null;
	}

	@Override
	public Collection<INode> searchKey(String key) {
		// Depth-First implementation of key search
		if(this.Keycache.containsKey(key)) {
			return this.Keycache.get(key);
		}
		final List<INode> result = Lists.newArrayList();
		this.elements.forEach((aKey, node) -> {
			// Check your key
			if(aKey.equals(key))
				result.add(node);
			
			// Recursively check and add down the subtree
			result.addAll(node.searchKey(key));
		});
		this.Keycache.put(key, result);
		return result;
	}
	
	@Override
	public List<INode> searchValue(Object data) {
		// Depth-First implementation of data search
		final List<INode> result = Lists.newArrayList();
		this.elements.forEach((key, node) -> {
			result.addAll(node.searchValue(data));
		});
		return result;
	}

	@Override
	public String toString() {
		return "CompositeNode"+ elements;
	}
}
