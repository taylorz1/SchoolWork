package treedb;

import java.util.Collection;
import java.util.Map;

public interface INode extends ISubject, IObserver{
	String getUUID();
	
	// Leaf element behavior
	Object getData();
	void setData(Object data);
	
	// Composite behavior
	INode getParent();
	void setParent(INode parent);

	String getKey();
	void setKey(String key);
	
	TreePath getPath();
	
	INode get(String key);
	INode put(String key, INode node);
	Map<String, INode> getElements();
	
	INode remove(String key);
	Collection<INode> removeAll(String key);

	INode searchUUID(String uuid);
	Collection<INode> searchKey(String key); 
	Collection<INode> searchValue(Object data); 
}
