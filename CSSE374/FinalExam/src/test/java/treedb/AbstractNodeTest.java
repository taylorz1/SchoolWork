package treedb;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractNodeTest {
	private class ConcreteNode extends AbstractNode {
		@Override
		public Object getData() {
			return null;
		}

		@Override
		public void setData(Object data) {
		}

		@Override
		public INode get(String key) {
			return null;
		}

		@Override
		public INode put(String key, INode node) {
			return null;
		}

		@Override
		public Map<String, INode> getElements() {
			return null;
		}

		@Override
		public INode remove(String key) {
			return null;
		}

		@Override
		public Collection<INode> removeAll(String key) {
			return null;
		}

		@Override
		public INode searchUUID(String uuid) {
			return null;
		}

		@Override
		public Collection<INode> searchKey(String key) {
			return null;
		}

		@Override
		public Collection<INode> searchValue(Object data) {
			return null;
		}
	};
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashCodeEquals() {
		// Hash code
		ConcreteNode node1 = new ConcreteNode();
		assertTrue(node1.hashCode() != 0);
		assertTrue(node1.equals(node1));

		// Happy case
		ConcreteNode node2 = new ConcreteNode();
		node2.uuid = node1.uuid;
		assertTrue(node1.equals(node2));
		
		assertFalse(node1.equals(null));
		assertFalse(node1.equals(new Object()));

		// Different ids
		node2.uuid = "234";
		assertFalse(node1.equals(node2));
	}

	@Test
	public void testAbstractNodeAndUUID() {
		ConcreteNode node1 = new ConcreteNode();
		ConcreteNode node2 = new ConcreteNode();
		
		assertNotNull(node1.uuid);
		assertNotNull(node2.uuid);
		assertNotEquals(node1.uuid, node2.uuid);
		assertEquals(node1.uuid, node1.getUUID());
	}

	@Test
	public void testGetSetParent() {
		ConcreteNode node1 = new ConcreteNode();
		assertEquals(node1.getParent(), null);
		
		
		ConcreteNode node2 = new ConcreteNode();
		node2.setParent(node1);
		assertEquals(node2.getParent(), node1);
	}

}
