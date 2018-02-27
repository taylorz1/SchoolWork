package treedb;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import utils.TreeBuilder;

import static org.mockito.Mockito.*;

public class CompositeNodeTest {

	@Test
	public void testCompositeNode() {
		CompositeNode node = new CompositeNode();
		assertNotNull(node.uuid);
		assertTrue(node.elements.isEmpty());
	}

	int i = 0;
	@Test
	public void testGetElements() {
		CompositeNode node = new CompositeNode();
		
		String key1 = "abc";
		DataNode data1 = new DataNode("abc");

		String key2 = "def";
		DataNode data2 = new DataNode("def");

		node.put(key1, data1);
		assertEquals(node.getElements().size(), 1);
		
		// Keys are not duplicated
		node.put(key1, data1);
		assertEquals(node.getElements().size(), 1);

		node.put(key2, data2);
		assertEquals(node.getElements().size(), 2);
		
		// Insertion ordering must be guaranteed
		node.getElements().forEach((k, v) -> {
			if(i == 0) {
				assertEquals(k, key1);
				assertEquals(v, data1);
			}
			else {
				assertEquals(k, key2);
				assertEquals(v, data2);
			}
			++i;
		});
	}

	@Test
	public void testPutAndGet() {
		CompositeNode node = new CompositeNode();
		
		String key1 = "abc";
		DataNode data1 = new DataNode("abc");

		String key2 = "def";
		DataNode data2 = new DataNode("def");
		
		node.put(key1, data1);
		node.put(key2, data2);

		assertEquals(node.get(key1), data1);
		assertEquals(node.get(key2), data2);
	}

	@Test
	public void testGetData() {
		CompositeNode node = new CompositeNode();
		assertEquals(node.getData(), null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetData() {
		CompositeNode node = new CompositeNode();
		node.setData(new Object());
	}

	@Test
	public void testRemove() {
		CompositeNode node = new CompositeNode();
		
		String key1 = "abc";
		DataNode data1 = new DataNode("abc");

		String key2 = "def";
		DataNode data2 = new DataNode("def");

		node.put(key1, data1);
		node.put(key2, data2);
		
		assertEquals(node.getElements().size(), 2);

		node.remove(key1);
		assertEquals(node.getElements().size(), 1);
		assertEquals(node.get(key1), null);
		
		node.remove(key2);
		assertEquals(node.getElements().size(), 0);
		assertEquals(node.get(key2), null);
	}
	
	@Test
	public void testRemoveAll() {
		INode root = new CompositeNode();
		INode node1 = new CompositeNode();
		root.put("abc", node1);

		String searchKey = "search";
		node1.put("def", new DataNode(456));
		node1.put("pqr", new DataNode(678));
		node1.put(searchKey, new DataNode(1));
		
		INode node2 = new CompositeNode();
		node1.put("ijk", node2);
		
		INode node3 = new CompositeNode();
		node2.put(searchKey, node3);

		node3.put("xyz", new DataNode(3));
		node3.put(searchKey, new DataNode(2));
		
		Collection<INode> result = root.removeAll(searchKey);
		assertEquals(2, result.size());
		result.forEach(node -> {
			if(node instanceof DataNode)
				assertEquals(1, node.getData());
			else {
				assertEquals(2, node.getElements().size());
			}
		});
	}

	@Test
	public void testSearchUUID() {
		INode node = TreeBuilder.create()
				.setMaxDepth(4)
				.setDataFanOut(3)
				.setCompositeFanOut(2)
				.setCompositeKeyPattern("composite-%d-%d-key")
				.setDataKeyPattern("data-%d-%d-key")
				.setDataValuePattern("data-%d-%d-value")
				.build();
		
		INode searchNode = mock(INode.class);
		when(searchNode.getUUID()).thenReturn("abc");
		
		INode lastComposite = this.getLastComposite(node);
		lastComposite.put("search", searchNode);
		
		INode resultNode = node.searchUUID("abc");
		assertTrue(searchNode == resultNode);
		
		// Should not be able to find def uuid
		assertEquals(node.searchUUID("def"), null);
	}
	
	private INode getLastComposite(INode node) {
		INode lastComposite = node;

		for (INode tempNode : lastComposite.getElements().values()) {
			if (tempNode instanceof CompositeNode) {
				lastComposite = tempNode;
			}
		}
		
		if(node != lastComposite)
			return this.getLastComposite(lastComposite);

		return node;
	}

	@Test
	public void testSearchKey() {
		INode node = TreeBuilder.create()
				.setMaxDepth(4)
				.setDataFanOut(3)
				.setCompositeFanOut(2)
				.setCompositeKeyPattern("composite-%d-%d-key")
				.setDataKeyPattern("data-%d-%d-key")
				.setDataValuePattern("data-%d-%d-value")
				.build();
		
		// Find top-level elements
		assertFalse(node.searchKey("composite-1-1-key").isEmpty());
		assertFalse(node.searchKey("composite-1-2-key").isEmpty());
		assertFalse(node.searchKey("data-1-1-key").isEmpty());
		assertFalse(node.searchKey("data-1-3-key").isEmpty());

		// Find elements around the middle of the tree
		assertFalse(node.searchKey("composite-2-1-key").isEmpty());
		assertFalse(node.searchKey("composite-3-2-key").isEmpty());
		assertFalse(node.searchKey("data-3-1-key").isEmpty());
		assertFalse(node.searchKey("data-3-2-key").isEmpty());

		// Find elements around the bottom of the tree
		assertFalse(node.searchKey("composite-4-1-key").isEmpty());
		assertFalse(node.searchKey("composite-4-2-key").isEmpty());
		assertFalse(node.searchKey("data-4-2-key").isEmpty());
		assertFalse(node.searchKey("data-4-3-key").isEmpty());
		
		// Find non-existant key
		assertTrue(node.searchKey("data-5-1-key").isEmpty());
	}

	@Test
	public void testSearchValue() {
		INode node = TreeBuilder.create()
				.setMaxDepth(4)
				.setDataFanOut(3)
				.setCompositeFanOut(2)
				.setCompositeKeyPattern("composite-%d-%d-key")
				.setDataKeyPattern("data-%d-%d-key")
				.setDataValuePattern("data-%d-%d-value")
				.build();

		// Find top-level elements
		assertFalse(node.searchValue("data-1-1-value").isEmpty());
		assertFalse(node.searchValue("data-1-3-value").isEmpty());

		// Find elements around the middle of the tree
		assertFalse(node.searchValue("data-3-2-value").isEmpty());
		assertFalse(node.searchValue("data-3-3-value").isEmpty());

		// Find elements around the bottom of the tree
		assertFalse(node.searchValue("data-4-1-value").isEmpty());
		assertFalse(node.searchValue("data-4-3-value").isEmpty());
		
		// Find non-existent key
		assertTrue(node.searchValue("data-5-1-value").isEmpty());
	}
	
	@Test
	public void testToString() {
		INode node = new CompositeNode();
		node.put("key", new DataNode("value"));
		
		String display = node.toString();
		assertTrue(display.contains("key"));
		assertTrue(display.contains("value"));
	}
}
