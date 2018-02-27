package heapiterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.AfterClass;
import org.junit.Test;

public class Testing {

	private static int heapIteratorPoints = 0;

	@Test
	public void testHeapIteratorBasic() {
		BinaryHeap h = new BinaryHeap();
		h.insert('a');
		h.insert('b');
		h.insert('c');
		h.insert('d');
		h.insert('e');
		h.insert('f');
		Iterator<Character> iter = h.depthFirstIterator();
		assertEquals(new Character('a'),iter.next());
		assertEquals(new Character('b'),iter.next());
		assertEquals(new Character('d'),iter.next());
		assertEquals(new Character('e'),iter.next());
		assertEquals(new Character('c'),iter.next());
		assertEquals(new Character('f'),iter.next());
		heapIteratorPoints += 6;
	}
	
	

	@Test
	public void testHeapIteratorQwerty() {
		BinaryHeap h = new BinaryHeap();
		h.insert('q');
		h.insert('w');
		h.insert('e');
		h.insert('r');
		h.insert('t');
		h.insert('y');
		h.insert('u');
		h.insert('i');
		h.insert('o');
		h.insert('p');
		Iterator<Character> iter = h.depthFirstIterator();
		assertEquals(new Character('e'),iter.next());
		assertEquals(new Character('i'),iter.next());
		assertEquals(new Character('o'),iter.next());
		assertEquals(new Character('w'),iter.next());
		assertEquals(new Character('r'),iter.next());
		assertEquals(new Character('p'),iter.next());
		assertEquals(new Character('t'),iter.next());
		assertEquals(new Character('q'),iter.next());
		assertEquals(new Character('y'),iter.next());
		heapIteratorPoints += 4;
		assertTrue(iter.hasNext());
		assertEquals(new Character('u'),iter.next());
		assertFalse(iter.hasNext());
		heapIteratorPoints += 2;
		try {
			iter.next();
			fail("Did not throw NoSuchElementException");
		} catch (Exception e) {
			if (!(e instanceof NoSuchElementException)) {
				fail("Did not throw NoSuchElementException");
			}
		}
		heapIteratorPoints += 1;
	}

	
	
	@Test
	public void testHeapIteratorAlphabet() {
		BinaryHeap h = new BinaryHeap();
		for (int i = 0; i < 26; i++) {
			h.insert((char)('a'+i));
		}
		char[] results = new char[] {'a','b','d','h','p','q','i','r','s','e',
				'j','t','u','k','v','w','c','f','l','x','y','m','z','g','n','o'};
		Iterator<Character> iter = h.depthFirstIterator();
		for (int i = 0; i < 26; i++) {
			assertEquals((Character) results[i],iter.next());
		}
		heapIteratorPoints += 7;
	}
		

	
	
	@AfterClass
	public static void displayPoints() {
		System.out.printf("***   heapIterator points:                   %2d/20\n", heapIteratorPoints);
		
	}
}
