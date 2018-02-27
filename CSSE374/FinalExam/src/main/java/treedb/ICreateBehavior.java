package treedb;

public interface ICreateBehavior {

	public INode create(TreePath path, Object value) throws TreeDatabaseException;
}
