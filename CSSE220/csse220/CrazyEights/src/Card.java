/**
 * 
 * This handles methods on a card object and is mostly just a data class.
 *
 * @author taylorz1. Created Sep 29, 2016.
 */
public class Card {
	private String suit;
	private String rank;
	private String cardValue;

	//
	private boolean eight;

	public Card(String initialCard) {
		this.suit = initialCard.substring(initialCard.length() - 1);
		this.rank = initialCard.substring(0, initialCard.length() - 1);

		//
		this.cardValue = initialCard;

		//
		if (this.rank.equals("8")) {
			this.eight = true;
		} else {
			this.eight = false;
		}
		//
	}

	public String getSuit() {
		return this.suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public boolean getEight() {
		return this.eight;
	}

	public String getCardValue() {
		return this.cardValue;
	}

	@Override
	public String toString() {
		return this.rank + this.suit;
	}
}
