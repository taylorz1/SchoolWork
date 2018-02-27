package treedb;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultDatabase implements IDatabase {
	INode root;
	Map<String, ICreateBehavior> behaviors = new HashMap<>();
	Map<String, INode> cache = new HashMap<>();
	
	public DefaultDatabase() {
		this.root = new CompositeNode();
	}
	
	public void addBehavior(String key, ICreateBehavior value) {
		this.behaviors.put(key, value);
	}
	
	protected void checkBeforeCreate(TreePath path) throws TreeDatabaseException {
		INode node = this.root;

		for (int i = 0; i < path.getSegmentsLength(); ++i) {
			if(!(node instanceof ICompositeNode)) {
				String message = String.format(
						"There is a non-composite element at the path [%s]. "
						+ "You will need to delete this element first before expanding the subtree through the create method.",
						path.getPartialSegments(0, i)
				);
				throw new TreeDatabaseException(message);
			}
			
			String segment = path.getSegmentAt(i);
			node = node.get(segment);			
			if (node == null)
				break;
		}
		
		if(node != null) {
			String message = String.format(
					"The path [%s] is already taken. Use the update method instead.",
					path
			);
			throw new TreeDatabaseException(message);
		}
	}
	
	@Override
	public INode create(TreePath path, Object value) throws TreeDatabaseException {
		this.checkBeforeCreate(path);
		
		INode node = this.root;
		int length = path.getSegmentsLength();
		for (int i = 0; i < length; ++i) {
			boolean isLastSegment = i == length - 1;
			String segment = path.getSegmentAt(i);
			
			INode newNode = node.get(segment);
			if (newNode == null) {
				if (isLastSegment) {
					if(this.behaviors.containsKey(value.getClass().toString())) {
						newNode = this.behaviors.get(value.getClass().toString()).create(path, value);
					} else {
						newNode = (INode)value;
					}
//					if(value instanceof INode) {
//						newNode = (INode)value;
//					}
//					else {
//						newNode = new DataNode(value);
//					}
				}
				else {
					newNode = new CompositeNode();
				}

				node.put(segment, newNode);
			}
			node = newNode;
		}
		return node;
	}

	@Override
	public INode read(TreePath path) throws TreeDatabaseException {

		INode node = this.root;
		for (int i = 0; i < path.getSegmentsLength(); ++i) {
			String segment = path.getSegmentAt(i);

			node = node.get(segment);			
			if (node == null)
				break;
		}
		
		return node;
	}

	@Override
	public INode update(TreePath path, Object value) throws TreeDatabaseException {
		INode node = this.read(path);
		if(node == null) {
			String message = String.format(
					"The path [%s] does not have any elements assigned to it. Use the create method instead.",
					path
			);
			throw new TreeDatabaseException(message);
		}

		INode parentNode = node.getParent();
		
		if (value instanceof INode) {
			node = (INode) value;
		} 
		else if (node instanceof IDataNode) { // value is not an INode and node is a DataNode
			node.setData(value);
		} 
		else if (node instanceof ICompositeNode) { // value is not an INode and node is a CompositeNode
			node = new DataNode(value);
		} 
		else {
			String message = String.format("The path [%s] has an unknown INode. "
					+ "The only supported database node types are CompositeNode and DataNode.", path);
			throw new TreeDatabaseException(message);
		}

		parentNode.put(path.getLastSegment(), node);
		return node;
	}

	@Override
	public INode delete(TreePath path) throws TreeDatabaseException {
		INode node = this.read(path);
		if(node == null)
			return node;
	
		INode parentNode = node.getParent();
		return parentNode.remove(path.getLastSegment());
	}

	@Override
	public INode searchUUID(String uuid) {
		return this.root.searchUUID(uuid);
	}

	@Override
	public Collection<INode> searchKey(String key) {
		return this.root.searchKey(key);
	}

	@Override
	public Collection<INode> searchValue(Object data) {
		return this.root.searchValue(data);
	}
}
