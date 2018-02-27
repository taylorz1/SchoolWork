package treedb;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

public class DataNodeTest {
	@Test
	public void testDataNodeObject() {
		DataNode node = new DataNode("abc");
		assertEquals(node.getData(), "abc");
		assertNotNull(node.getUUID());

		node = new DataNode();
		assertEquals(node.getData(), null);
		assertNotNull(node.getUUID());
	}

	@Test
	public void testSetData() {
		DataNode node = new DataNode();
		assertEquals(node.getData(), null);

		node.setData("abc");
		assertEquals(node.getData(), "abc");
	}
	
	@Test
	public void testGetKey() {
		DataNode node = new DataNode("abc");
		assertEquals(node.get("abc"), null);
	}
	

	@Test(expected = UnsupportedOperationException.class)
	public void testPut() {
		DataNode node = new DataNode("abc");
		node.put("abc", new DataNode("abc"));
	}

	@Test
	public void testGetElements() {
		DataNode node = new DataNode("abc");
		assertTrue(node.getElements().isEmpty());
	}

	@Test
	public void testRemove() {
		DataNode node = new DataNode("abc");
		assertEquals(node.remove("abc"), null);
	}

	@Test
	public void testRemoveAll() {
		DataNode node = new DataNode("abc");
		assertTrue(node.removeAll("abc").isEmpty());
	}

	@Test
	public void testSearchUUID() {
		DataNode node = new DataNode("abc");
		assertEquals(node.searchUUID(node.uuid), node);
		assertEquals(node.searchUUID("abc"), null);
	}

	@Test
	public void testSearchKey() {
		DataNode node = new DataNode("abc");
		assertTrue(node.searchKey("abc").isEmpty());
	}

	@Test
	public void testSearchValue() {
		DataNode node = new DataNode("abc");
		Collection<INode> result = node.searchValue("abc");
		assertTrue(result.size() == 1);
		assertTrue(result.contains(node));
		
		assertTrue(node.searchValue("def").isEmpty());
	}
	
	@Test
	public void testToString() {
		DataNode node = new DataNode("abc");
		assertTrue(node.toString().contains("abc"));
	}
}
