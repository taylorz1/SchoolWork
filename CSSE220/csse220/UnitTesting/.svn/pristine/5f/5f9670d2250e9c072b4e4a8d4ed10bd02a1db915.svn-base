/* This imports the assertEquals function from the Assert class 
 * (from the org.junit package). */
import static org.junit.Assert.assertEquals;

/* This imports the Rectangle class from the java.awt package. */
import java.awt.Rectangle;

/* This imports the Test "annotation" from the org.junit package. */
import org.junit.Test;

/**
 * This class demonstrates how we can use JUnit to test the translate method of
 * Rectangle. Compare to Big Java ch02/rectangle/MoveTester.java, which does a
 * similar test using plain Java code.
 * 
 * @author Curt Clifton. Created Sep 9, 2008.
 */
public class JUnitMoveTester {
	/**
	 * Tests the translate method.
	 */
	@Test
	public void testTranslate() {
		// Runs the method to be tested:
		Rectangle box = new Rectangle(5, 10, 20, 30);
		box.translate(15, 25);

		// Checks that the result is as expected:
		double delta = 0.001; // allowed difference in comparison
		assertEquals(20.0, box.getX(), delta);
		assertEquals(35.0, box.getY(), delta);
	}
}
