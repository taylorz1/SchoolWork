import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
/**
 * 
 * To create 3 additional tests for the CrazyEights Class.
 *
 * @author taylorz1.
 *         Created Oct 11, 2016.
 */
public class CrazyEightsTest {

	@Test
	public void Cardtest() {
		Card card1 = new Card("10H");
		String suit = card1.getSuit();
		String value = card1.getRank();
		assertTrue(suit.equals("H"));
		assertTrue(value.equals("10"));
	}

	@Test
	public void TopCard() {
		Deck deck1 = new Deck();
		deck1.addDeck(false);
		deck1.setTopCard();
		Card topcard = deck1.getTopCard();
		assertTrue(topcard.getCardValue().equals("AH"));
	}
	
	@Test
	public void DeckTest() {
		Deck deck2 = new Deck();
		deck2.addDeck(false);
		deck2.setTopCard();
		ArrayList<Card> used = deck2.getUsedCards();
		assertTrue(used.get(0).getCardValue().equals("AH"));
		
	}
}
