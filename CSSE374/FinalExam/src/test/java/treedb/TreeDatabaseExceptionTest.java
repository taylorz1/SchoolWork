package treedb;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreeDatabaseExceptionTest {

	@Test
	public void testTreeDatabaseException() {
		TreeDatabaseException e = new TreeDatabaseException();
		assertNotNull(e);
	}

	@Test
	public void testTreeDatabaseExceptionString() {
		TreeDatabaseException e = new TreeDatabaseException("abc");
		assertNotNull(e);
	}

	@Test
	public void testTreeDatabaseExceptionThrowable() {
		TreeDatabaseException e = new TreeDatabaseException(new Exception());
		assertNotNull(e);
	}

	@Test
	public void testTreeDatabaseExceptionStringThrowable() {
		TreeDatabaseException e = new TreeDatabaseException("abc", new Exception());
		assertNotNull(e);
	}

	@Test
	public void testTreeDatabaseExceptionStringThrowableBooleanBoolean() {
		TreeDatabaseException e = new TreeDatabaseException("abc", new Exception(), false, false);
		assertNotNull(e);
	}
}
