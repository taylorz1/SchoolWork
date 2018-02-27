package treedb;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.mockito.Mockito.*;

import treedb.CompositeNode;
import treedb.DataNode;
import treedb.DefaultDatabase;
import treedb.IDatabase;
import treedb.INode;
import treedb.TreeDatabaseException;

import static treedb.TreePath.at;

public class DefaultDatabaseTest {
	private IDatabase populateDB() throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		
		INode node = new CompositeNode();
		node.put("name", new DataNode("Chandan Rupakheti"));
		node.put("room", new DataNode("F212"));
		db.create(at("/rose-hulman/faculty/rupakheti"), node);

		node = new CompositeNode();
		node.put("name", new DataNode("Mark Hays"));
		node.put("room", new DataNode("F214"));
		db.create(at("/rose-hulman/faculty/hays"), node);
		
		node = new CompositeNode();
		node.put("name", new DataNode("Steve Chenoweth"));
		node.put("room", new DataNode("F214"));
		db.create(at("/rose-hulman/faculty/chenoweth"), node);
		
		node = new CompositeNode();
		node.put("name", new DataNode("Lynn Degler"));
		node.put("room", new DataNode("F231"));
		db.create(at("rose-hulman/staff/degler/"), node);
		
		return db;
	}
	
	@Test
	public void testCreateRead() throws TreeDatabaseException {
		IDatabase db = this.populateDB();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		
		INode result = db.read(at("rose-hulman/faculty/rupakheti"));
		assertEquals(result.get("room").getData(), "F212");

		result = db.read(at("/rose-hulman/staff/degler/"));
		assertEquals(result.get("name").getData(), "Lynn Degler");
		
		// Expecting the top level node here, i.e., "rose-hulman"
		result = result.getParent().getParent();
		result = result.get("faculty");

		INode mark = result.get("hays");
		assertEquals(mark.get("name").getData(), "Mark Hays");
		
		INode steve = result.get("chenoweth");
		assertEquals(steve.get("name").getData(), "Steve Chenoweth");
		
		INode node1 = db.create(at("test"), "Test Data");
		INode node2 = db.read(at("test"));
		assertEquals(node1, node2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateEmptyPath()  throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		db.create(at(""), "abc");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateEmptySegmentPath()  throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		db.create(at("/abc/ /def"), "abc");
	}
	
	@Test(expected = TreeDatabaseException.class)
	public void testCreateSubtreeRootedAtDataNode()  throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		db.create(at("root/data"), new DataNode("Some data"));
		db.create(at("root/data/subtree"), "A dat node cannot have subtree!");
	}

	@Test(expected = TreeDatabaseException.class)
	public void testCreateShouldNotUpdate()  throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		db.create(at("root/data"), new DataNode("Some data"));
		db.create(at("root/data"), "Should not create a new node at an existing path!");
	}

	@Test
	public void testUpdateSimple()  throws TreeDatabaseException {
		IDatabase db = this.populateDB();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		db.create(at("test"), new DataNode("test value"));
		
		INode result = db.read(at("test"));
		assertEquals("test value", result.getData());
		
		db.update(at("test"), "new test value");
		INode newResult = db.read(at("test"));
		assertEquals("new test value", result.getData());
		assertEquals(result.getUUID(), newResult.getUUID()); // Ids must match
	}

	@Test(expected=TreeDatabaseException.class)
	public void testUpdateNonExistent() throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.update(at("test"), new DataNode("test value"));
	}
	
	@Test
	public void testUpdateSubtree() throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());

		db.create(at("root"), new CompositeNode());
		db.create(at("root/test"), new CompositeNode());

		db.create(at("root/test/test1"), "test value 1");
		db.update(at("root/test/test1"), "new test value 1");
		INode result = db.read(at("root/test/test1"));
		assertEquals("new test value 1", result.getData());
		
		CompositeNode node = new CompositeNode();
		node.put("test3", new DataNode("test value 2"));
		db.create(at("root/test/test2"), node);
		db.update(at("root/test/test2/test3"), "new test value 2");
		result = db.read(at("root/test/test2/test3"));
		assertEquals("new test value 2", result.getData());
		
		db.update(at("root/test/test2/test3"), new DataNode("new test value 3"));
		result = db.read(at("root/test/test2/test3"));
		assertEquals("new test value 3", result.getData());
		
		db.update(at("root/test/test2"), "subtree replaced");
		result = db.read(at("root/test/test2"));
		assertEquals("subtree replaced", result.getData());
	}
	
	@Test(expected=TreeDatabaseException.class)
	public void testUpdateIllegalSubtree() throws TreeDatabaseException {
		IDatabase db = new DefaultDatabase();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());

		db.create(at("root/legal"), "Data Node");
		
		INode illegalNode = mock(INode.class);
		db.create(at("root/illegal"), illegalNode);
		db.update(at("root/illegal"), "Should throw exception!");
	}
	
	@Test
	public void testDelete() throws TreeDatabaseException {
		IDatabase db = this.populateDB();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		
		assertNotNull(db.read(at("rose-hulman/staff/degler/")));
		INode degler = db.delete(at("rose-hulman/staff/degler/"));
		assertNull(db.read(at("rose-hulman/staff/degler/")));
		assertEquals(degler.get("name").getData(), "Lynn Degler");
		
		assertNotNull(db.read(at("rose-hulman/faculty")));
		db.delete(at("rose-hulman/faculty"));
		assertNull(db.read(at("rose-hulman/faculty")));
	}
	
	@Test
	public void testDeleteNonExistent() throws TreeDatabaseException {
		IDatabase db = this.populateDB();
		db.addBehavior(String.class.toString(), new DataNodeBehavior());
		assertNull(db.delete(at("rose-hulman/staff/mouck/")));
	}
}
