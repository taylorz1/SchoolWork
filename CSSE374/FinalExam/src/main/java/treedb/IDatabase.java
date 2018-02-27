package treedb;

import java.util.Collection;

public interface IDatabase {
	
	INode create(TreePath path, Object value) throws TreeDatabaseException;
	INode read(TreePath path) throws TreeDatabaseException;
	INode update(TreePath path, Object value) throws TreeDatabaseException;
	INode delete(TreePath path) throws TreeDatabaseException;
	void addBehavior(String key, ICreateBehavior value);

	INode searchUUID(String uuid);
	Collection<INode> searchKey(String key); 
	Collection<INode> searchValue(Object data); 
}
