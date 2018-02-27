import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringProbsTest {

	@Test
	public void testUppercaseIfExclaimation() {
		assertEquals("ABC!", StringProbs.uppercaseIfExclaimation("abc!"));
		assertEquals("abc", StringProbs.uppercaseIfExclaimation("abc"));
		assertEquals("A!B!C!", StringProbs.uppercaseIfExclaimation("a!b!c!"));
	}

	@Test
	public void testUppercaseThree() {
		assertEquals("ABCdef", StringProbs.uppercaseThree("abcdef"));
		assertEquals("AB", StringProbs.uppercaseThree("ab"));
		assertEquals("QQQqqqqqqq", StringProbs.uppercaseThree("qqqqqqqqqq"));
	}

	@Test
	public void testFirstMatchesLast() {
		assertTrue(StringProbs.firstMatchesLast("abca"));
		assertTrue(StringProbs.firstMatchesLast("zbdsadascz"));
		assertTrue(StringProbs.firstMatchesLast("h"));
		assertFalse(StringProbs.firstMatchesLast("bbca"));
		assertFalse(StringProbs.firstMatchesLast("qbdsadascz"));
		assertFalse(StringProbs.firstMatchesLast(""));
	}

	@Test
	public void testFirst4Match() {
		assertTrue(StringProbs.first4Match("aaaaBBBB", "aaaaCCCC"));
		assertTrue(StringProbs.first4Match("aaaaBBBB", "aaaa"));
		assertTrue(StringProbs.first4Match("abcdBBBB", "abcd"));
		assertFalse(StringProbs.first4Match("aaaBBBB", "aaaCCCC"));
		assertFalse(StringProbs.first4Match("abaaBBBB", "aaaa"));
		assertFalse(StringProbs.first4Match("abc", "abc"));
	}

}
