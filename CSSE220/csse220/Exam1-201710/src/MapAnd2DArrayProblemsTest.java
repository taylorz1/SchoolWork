import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

public class MapAnd2DArrayProblemsTest {

	@Test
	public void badLuckTest() {
		int[][] test1 = {{1,2,3},	
						 {4,1,1}};
		int[][] test2 = {{1,2,3,4,3},	
				 	     {4,1,1,1,1}};
		int[][] test3 = {{1,2,3},	
				 	     {4,1,1},
				 	     {18,1,1}};
		int[][] test4 = {{1,2,10},	
				         {22,1,1}};
		int[][] test5 = {{1,2,3},	
				 		 {4,1,1},
				 		 {8,10,5}};
		int[][] test6 = {{1,1,1,1,1,1,1,1,1,1,1,1,1},	
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1}};
		int[][] test7 = {{1,1,1,1,1,1,1,1,1,1,1,1},	
		 			     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1},
					     {1,1,1,1,1,1,1,1,1,1,1,1}};
		int[][] test8 = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1},	
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						 {1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		int[][] test9 = {{13},	
				 		 {0}};
		int[][] test10 = {{11},	
		 		          {12}};
		
		assertFalse(MapAnd2DArrayProblems.hasBadLuck(test1));
		assertTrue(MapAnd2DArrayProblems.hasBadLuck(test2));
		assertFalse(MapAnd2DArrayProblems.hasBadLuck(test3));
		assertTrue(MapAnd2DArrayProblems.hasBadLuck(test4));
		assertFalse(MapAnd2DArrayProblems.hasBadLuck(test5));
		assertTrue(MapAnd2DArrayProblems.hasBadLuck(test6));
		assertFalse(MapAnd2DArrayProblems.hasBadLuck(test7));
		assertFalse(MapAnd2DArrayProblems.hasBadLuck(test8));
		assertTrue(MapAnd2DArrayProblems.hasBadLuck(test9));
		assertFalse(MapAnd2DArrayProblems.hasBadLuck(test10));
	}
	
	@Test
	public void histogramTest() {
		
        char[] test = new char[] {'a', 'b', 'a', 'c', 'z', 'c', 'c'};
        HashMap<Character, String> result = MapAnd2DArrayProblems.letterHistogram(test);
        System.out.println(result);
        assertEquals("**", result.get('a'));
        assertEquals("*", result.get('b'));
        assertEquals("***", result.get('c'));
        assertEquals("*", result.get('z'));
        assertEquals(4, result.size()); // make sure that they don�t have any extra junk?

        char[] test2 = new char[] {'q','z','z','q','q','q'};
        HashMap<Character, String> result2 = MapAnd2DArrayProblems.letterHistogram(test2);
        assertEquals("****", result2.get('q'));
        assertEquals("**", result2.get('z'));
	}

}
