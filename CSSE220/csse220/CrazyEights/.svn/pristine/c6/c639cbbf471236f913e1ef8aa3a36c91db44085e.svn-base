import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * This class handles the deck and most card "movement".
 *
 * @author taylorz1. Created Sep 29, 2016.
 */
public class Deck {
	private ArrayList<Card> Cards;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> UsedCards;
	private Card topCard;

	public Deck() {
		this.setCards(new ArrayList<Card>());
		this.setUsedCards(new ArrayList<Card>());
		this.playerCards = new ArrayList<Card>();

	}

	// Generates a deck from the test deck, by turning string objects into card
	// object.
	public void addDeck(boolean tester) {
		// Adding deck
		String[] deck;
		if (tester) {
			deck = new String[] { "QH", "7H", "JC", "9D", "8S", "4C", "JD", "AH", "10D", "4S", "5H", "QC", "3D", "AD",
					"KC", "KD", "2S", "3C", "6S", "QD", "5C", "7S", "6D", "3S", "AS", "KH", "10H", "6C", "2D", "QS",
					"8C", "8D", "2C", "9H", "6H", "4H", "2H", "7D", "8H", "10C", "JS", "5S", "9C", "KS", "3H", "AC",
					"9S", "JH", "4D", "5D", "10S", "7C" };
		} else {
			deck = new String[] { "AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AD",
					"2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AC", "2C", "3C", "4C",
					"5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AS", "2S", "3S", "4S", "5S", "6S", "7S",
					"8S", "9S", "10S", "JS", "QS", "KS" };
		}

		for (int i = 0; i < deck.length; i++) {
			Card card = new Card(deck[i]);
			this.getCards().add(card);
		}
	}

	// Handles the top card.
	public Card getTopCard() {
		return this.topCard;
	}

	// Handles setting of the top card.
	public void setTopCard() {
		Card card = this.getCards().get(0);
		this.topCard = card;

		// Remove the top card
		this.Cards.remove(card);
		this.UsedCards.add(card);
	}

	public void setTopCard(Card card) {
		this.topCard = card;
	}

	// Handles what happens when you draw a card from the deck. Basically it
	// keeps track of positions.
	public Card drawCard() {
		if (this.getCards().isEmpty()) {
			// Ideally this loop adds all the cards except the top card (if
			// you've been paying close attention to my indexing you'll see it's
			// actually the bottom card now, as in the most recent card) to the
			// this.cards field and then shuffles them to make it usable.
			for (int i = 0; i < this.getUsedCards().size() - 1; i++) {
				this.getCards().add(this.getUsedCards().get(i));
			}
			Collections.shuffle(this.getCards());
			this.setUsedCards(new ArrayList<Card>());
			Card card = this.getCards().get(0);
			this.playerCards.add(card);
			this.getCards().remove(0);
			return card;
		}
		Card card = this.getCards().get(0);
		this.playerCards.add(card);
		this.getCards().remove(0);
		return card;
	}

	// Handles adding things to the discard pile.
	public void addDiscard(Card card, Player player) {
		this.getUsedCards().add(card);

		// Handles Player behavior of cards.
		ArrayList<Card> hand = player.getHandofCard();
		hand.remove(card);
		player.setHandofCard(hand);
	}

	// This should shuffle the array randomly.
	public void shuffle() {
		Collections.shuffle(this.getCards());
	}

	public ArrayList<Card> getCards() {
		return Cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.Cards = cards;
	}

	public ArrayList<Card> getUsedCards() {
		return UsedCards;
	}

	public void setUsedCards(ArrayList<Card> usedCards) {
		this.UsedCards = usedCards;
	}
}
