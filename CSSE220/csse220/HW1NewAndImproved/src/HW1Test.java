import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Class: HW1Test
 * @author wilkin
 * Purpose: This is a JUnit test case class to test the HW1 class to make sure
 * it returns the correct result.
 * 
 * Use:
 * 		You will not instantiate this class, simply run this and if everything is
 * 		correct, you will see green; otherwise, you will see red, which indicates
 * 		that an incorrect result was returned.
 *
 */
public class HW1Test {

	@Test
	public void testAddFractionGoodNumbers() {
		assertEquals(HW1.addFraction(1, 2, 1, 4), 0.75, 0.01);
		assertEquals(HW1.addFraction(1, 2, 1, 2), 1.0, 0.01);
		assertEquals(HW1.addFraction(1, 4, 1, 4), 0.5, 0.01);
		assertEquals(HW1.addFraction(1, 10, 3, 5), 0.7, 0.01);
	}

	@Test
	public void testAddFractionZero() {
		assertEquals(HW1.addFraction(0, 2, 0, 4), 0, 0.01);
	}
	
	@Test
	public void testAddFractionNegative() {
		assertEquals(HW1.addFraction(-1, 10, 3, 5), 0.5, 0.01);
		assertEquals(HW1.addFraction(-1, 10, -3, 5), -0.7, 0.01);
		assertEquals(HW1.addFraction(-1, 2, -1, 4), -0.75, 0.01);
	}
}
