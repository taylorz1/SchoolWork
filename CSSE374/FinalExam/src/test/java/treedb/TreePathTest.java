package treedb;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TreePathTest {
	@Test
	public void testAtAndGetPath() {
		TreePath path = TreePath.at("abc");
		assertEquals("abc", path.getPath());

		path = TreePath.at("/abc/def/");
		assertEquals("abc/def", path.getPath());

		path = TreePath.at("/abc/def/", "/ghi", "klm/nop/", "/qrs/");
		assertEquals("abc/def/ghi/klm/nop/qrs", path.getPath());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAtIllegalEmpty() {
		TreePath.at("");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAtIllegalRoot() {
		TreePath.at("/");
	}
	
	@Test
	public void testGetPartialSegments() {
		TreePath path = TreePath.at("/abc/def/ghi/klm/nop/qrs/");
	
		assertEquals("abc/def/ghi/klm/nop/qrs", path.getPartialSegments(0, path.getSegmentsLength()));
		assertEquals("", path.getPartialSegments(0, 0));
		assertEquals("abc/def/ghi/klm/nop/qrs", path.getPartialSegments(0, -1));
		assertEquals("abc/def/ghi/klm/nop", path.getPartialSegments(0, -2));
		assertEquals("def/ghi/klm/nop", path.getPartialSegments(1, -2));
		assertEquals("ghi/klm/nop", path.getPartialSegments(2, -2));
		assertEquals("klm/nop", path.getPartialSegments(3, -2));
		assertEquals("klm", path.getPartialSegments(3, -3));
	}

	@Test
	public void testGetFirstAndLastSegment() {
		TreePath path = TreePath.at("/abc/def/ghi/klm/nop/qrs/");
		assertEquals("abc", path.getFirstSegment());
		assertEquals("qrs", path.getLastSegment());
	}

	@Test
	public void testGetSegmentAtAndSegmetLength() {
		TreePath path = TreePath.at("/abc/def/ghi/klm/nop/qrs/");
		assertEquals("abc", path.getSegmentAt(0));
		assertEquals("def", path.getSegmentAt(1));
		assertEquals("ghi", path.getSegmentAt(2));
		
		assertEquals("qrs", path.getSegmentAt(-1));
		assertEquals("nop", path.getSegmentAt(-2));
		
		assertEquals("qrs", path.getSegmentAt(path.getSegmentsLength() - 1));
		assertEquals("abc", path.getSegmentAt(-path.getSegmentsLength()));
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testGetSegments() {
		TreePath path = TreePath.at("/abc/def/");
		List<String> segments = path.getSegments();
	
		assertEquals(2, segments.size());
		assertTrue(segments.containsAll(Arrays.asList("abc", "def")));
		
		segments.set(0, "throw exception");
	}

	@Test
	public void testIterator() {
		TreePath path = TreePath.at("/abc/def/");
		assertNotNull(path.iterator());
	}

	@Test
	public void testEqualsObject() {
		TreePath path1 = TreePath.at("/abc/def/");
		TreePath path2= TreePath.at("/abc/def/ghi");
		TreePath path3 = TreePath.at("/abc/def/");

		assertEquals(path1, path1);
		assertNotEquals(path1, path2);
		assertEquals(path1, path3);
		assertEquals(path3, path1);

		assertNotEquals(path1, null);
		assertNotEquals(path1, "/abc/def/");
	}

	@Test
	public void testHashCode() {
		TreePath path = TreePath.at("/abc/def/");
		assertTrue(path.hashCode() != 0);
	}
}
