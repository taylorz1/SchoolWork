import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BadFracTest {
	
	//TODO: Add unit tests for the isReduced method and the add method
	//		Make sure and look at the method descriptions to figure out
	//		your corner cases.

	private BadFrac tester1 = new BadFrac(1, 2);
	private BadFrac tester2 = new BadFrac(1, 4);
	private BadFrac tester3 = new BadFrac(2, 4);
	private BadFrac tester4 = new BadFrac(0, 1);
	//YOU WILL DEFINITELY need more objects with which to test
	@Test
	public void testgcm() {
		assertEquals(1, tester1.gcm(1, 2));
	}
	@Test
	public void testisReduced() {
		tester2.isReduced();
		tester1.isReduced();
		tester3.isReduced();
		tester4.isReduced();
	}
	@Test
	public void testAdd() {
		assertEquals(1,tester1.add(1/2,1/2));
		assertEquals(1,tester2.add(3, 4));
	}
	
}
