package insertEBST;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Test;

@SuppressWarnings("boxing")
public class Testing {

	private static int insertEBSTPoints = 0;

	
	@Test
	public void testInsertEBSTBasic() {
		ExtendedBinarySearchTree t = new ExtendedBinarySearchTree();
		assertEquals("[(-Inf:Inf)]",t.toString());
		t.insert(50);
		assertEquals("[(-Inf:50), 50, (50:Inf)]",t.toString());
		t.insert(40);
		assertEquals("[(-Inf:40), 40, (40:50), 50, (50:Inf)]",t.toString());
		t.insert(60);
		assertEquals("[(-Inf:40), 40, (40:50), 50, (50:60), 60, (60:Inf)]",t.toString());
		t.insert(20);
		assertEquals("[(-Inf:20), 20, (20:40), 40, (40:50), 50, (50:60), 60, (60:Inf)]",t.toString());
		insertEBSTPoints += 5;
	}	
	
	@Test
	public void testInsertEBSTBigger() {
		ExtendedBinarySearchTree t = new ExtendedBinarySearchTree();
		t.insert(40);
		t.insert(20);
		t.insert(60);
		t.insert(50);
		t.insert(70);
		String result1 = "[(-Inf:20), 20, (20:40), 40, (40:50), 50, (50:60), 60, (60:70), 70, (70:Inf)]";
		assertEquals(result1,t.toString());
		t.insert(10);
		t.insert(30);
		t.insert(15);
		t.insert(12);
		String result2 = "[(-Inf:10), 10, (10:12), 12, (12:15), 15, (15:20), 20, (20:30), 30, (30:40), "+
		                 "40, (40:50), 50, (50:60), 60, (60:70), 70, (70:Inf)]";
		assertEquals(result2,t.toString());
		t.insert(75);
		t.insert(78);
		t.insert(76);
		t.insert(55);
		String result3 = "[(-Inf:10), 10, (10:12), 12, (12:15), 15, (15:20), 20, (20:30), 30, (30:40), "+
		                "40, (40:50), 50, (50:55), 55, (55:60), 60, (60:70), 70, (70:75), 75, (75:76), "+
				        "76, (76:78), 78, (78:Inf)]";
		assertEquals(result3,t.toString());
		insertEBSTPoints += 5;
	}	
	
	@Test
	public void testInsertEBSTRandom() {
		Random ran = new Random();

		HashSet<Integer> set = new HashSet<Integer>();
		for (Integer i = 0; i < 200; i++) {          // Generate 200 random Integers and add them to the set
			int value = ran.nextInt(1000);           // (due to repetition, may end up with less)
			set.add(value);
		}
		
		ExtendedBinarySearchTree t = new ExtendedBinarySearchTree();
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Integer item : set) {                   // Insert all into the EBST and a list
			t.insert(item);
			list.add(item);
		}
		
		Collections.sort(list);                      // sort the list
		
		String result = "[(-Inf:"+list.get(0).toString()+"), ";
		for (int i = 0; i < list.size()-1; i++) {
			Integer curr = list.get(i);
			Integer next = list.get(i+1);
			result += curr.toString()+", ("+curr.toString()+":"+next.toString()+"), ";
		}
		Integer last = list.get(list.size()-1);
		result += last.toString()+", ("+last.toString()+":Inf)]";
		assertEquals(result,t.toString());
		insertEBSTPoints += 8;
	}
	
	


	@AfterClass
	public static void displayPoints() {
		System.out.printf("***   insertEBST points:                     %2d/18\n", insertEBSTPoints);
	}
}