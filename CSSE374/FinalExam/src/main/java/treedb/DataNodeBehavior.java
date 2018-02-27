package treedb;

public class DataNodeBehavior implements ICreateBehavior {

	@Override
	public INode create(TreePath path, Object value) throws TreeDatabaseException {
		return new DataNode(value);
	}

}
