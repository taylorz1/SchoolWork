package treedb;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.Lists;

public class DataNode extends AbstractNode implements IDataNode {
	Object data;

	public DataNode() {
		super();
	}

	public DataNode(Object data) {
		super();
		this.data = data;
	}

	@Override
	public Object getData() {
		return this.data;
	}

	@Override
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public INode get(String key) {
		return null;
	}

	@Override
	public INode put(String key, INode node) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, INode> getElements() {
		return Collections.emptyMap();
	}

	@Override
	public INode remove(String uuid) {
		return null;
	}

	@Override
	public Collection<INode> removeAll(String key) {
		return Collections.emptyList();
	}
	
	@Override
	public INode searchUUID(String uuid) {
		if(this.UUIDcache.containsKey(uuid)) {
			return this.UUIDcache.get(uuid);
		}
		if(this.uuid.equals(uuid)) {
			this.UUIDcache.put(uuid, this);
			return this;
		}

		return null;
	}

	@Override
	public Collection<INode> searchKey(String key) {
		if(this.Keycache.containsKey(key)) {
			return this.Keycache.get(key);
		}
		this.Keycache.put(key, Collections.emptyList());
		return Collections.emptyList();
	}

	@Override
	public Collection<INode> searchValue(Object data) {
		if(data.equals(this.data)) {
			return Lists.newArrayList(this);
		}
		
		return Collections.emptyList();
	}

	@Override
	public String toString() {
		return "DataNode[" + data + "]";
	}
}
