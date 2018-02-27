package treedb;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractNode implements INode {
	String uuid;
	INode parent;
	String key;
	List<IObserver> observers = new LinkedList<>();
	ISubject subject;
	Map<String, INode> UUIDcache = new HashMap<>();
	Map<String, Collection<INode>> Keycache = new HashMap<>();

	public AbstractNode() {
		// This produces Universally Unique ID (UUID) for each node
		this.uuid = UUID.randomUUID().toString();
	}

	@Override
	public String getUUID() {
		return this.uuid;
	}

	@Override
	public INode getParent() {
		return this.parent;
	}

	@Override
	public void setParent(INode parent) {
		this.parent = parent;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public void setKey(String key) {
		String oldkey = this.key;
		this.key = key;
		if (oldkey != null && this.key != null) {
			this.notifyObserver(oldkey, 0);
		}
	}

	@Override
	public TreePath getPath() {
		return TreePath.from(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + uuid.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractNode other = (AbstractNode) obj;
		return uuid.equals(other.uuid);
	}

	@Override
	public void registerObserver(IObserver node) {
		// TODO Auto-generated method stub
		this.observers.add(node);
	}

	@Override
	public void removeObserver(IObserver node) {
		// TODO Auto-generated method stub
		this.observers.remove(node);
	}

	@Override
	public void notifyObserver(String oldkey, int status) {
		// TODO Auto-generated method stub
		for (IObserver o : this.observers) {
			o.update(this, oldkey, status);
		}
	}

	@Override
	public void update(INode data, String key, int status) {
		// TODO Auto-generated method stub
		// Never bother updating the UUID cache because it doesn't matter.
		if (status == 0) {
			Collection<INode> newNodes = this.searchKey(data.getKey());
			Collection<INode> oldNodes = this.Keycache.get(key);
			oldNodes.remove(data);
			newNodes.add(data);
		} else { // We want to remove the entire keymap pair.
			this.Keycache.remove(key);
		}
	}

}
