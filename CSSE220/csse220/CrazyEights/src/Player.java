import java.util.ArrayList;

/**
 * 
 * This class handles a player object that is a container and has methods for
 * acting on the deck.
 *
 * @author taylorz1. Created Sep 29, 2016.
 */
public class Player {
	private String playername;
	private ArrayList<Card> HandofCard;
	private int Playernumber;

	public Player(int Playernumber) {
		this.playername = "player" + Playernumber;
		this.HandofCard = new ArrayList<Card>();
	}

	public void addCardtoHand(Card cardname) {
		this.HandofCard.add(cardname);
	}

	public String getPlayername() {
		return this.playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public ArrayList<Card> getHandofCard() {
		return this.HandofCard;
	}

	public void setHandofCard(ArrayList<Card> handofCard) {
		this.HandofCard = handofCard;
	}

	public int getPlayernumber() {
		return this.Playernumber;
	}

	public void setPlayernumber(int playernumber) {
		this.Playernumber = playernumber;
	}

	public String printHand() {
		String tortn = "Your cards are ";
		for (Card card : this.HandofCard) {
			tortn = tortn + card.getRank() + card.getSuit() + " ";
		}
		return tortn;
	}

	public Card getCard(String cardValue) {
		for (int i = 0; i < this.HandofCard.size(); i++) {
			if (this.HandofCard.get(i).getCardValue().equals(cardValue)) {
				Card card = this.HandofCard.get(i);
				return card;
			}
		}
		System.out.println("You have tried to play a card you do not have!");
		return null;

	}

	public boolean getWin() {
		return this.HandofCard.size() == 0;
	}
}