import java.util.Scanner;

/**
 * 
 * This class is responsible for initialization of crazy eights.
 *
 * @author taylorz1. Created Sep 29, 2016.
 */
public class CrazyEights {
	private Deck deck;
	private int turn;
	private Player[] players;

	/**
	 * Initialize your fields here (then change this documentation).
	 */
	public CrazyEights() {

	}

	/**
	 * Decodes a command and invokes the right method. You may update this
	 * method if you find it's necessary for your design, but be careful you
	 * don't break anything. If you make changes to this method, do it in very
	 * small increments and frequently run the tests.
	 * 
	 * @param command
	 *            The command to decode
	 * @return the results of the command. "ok" if success but no result.
	 */
	public String handleCommand(String command) {
		Scanner input = new Scanner(command);
		String commandType = input.next();
		String toReturn = null;

		if (commandType.equals("start-game")) {
			int numPlayers = input.nextInt();
			String deckName = null;
			boolean shuffle = true;
			if (input.hasNext()) {
				deckName = input.next();
			}
			if (input.hasNextBoolean()) {
				shuffle = input.nextBoolean();
			}
			toReturn = handleStartGame(numPlayers, deckName, shuffle);
		} else if (commandType.equals("play-card")) {
			String cardValue = input.next();
			toReturn = handlePlayCard(cardValue);

		} else if (commandType.equals("draw-card")) {
			toReturn = handleDrawCard();
		} else if (commandType.equals("exit")) {
			input.close();
			System.exit(0);
		} else {
			toReturn = "Unknown command " + commandType + "\n" + "Player " + ((this.turn % this.players.length) + 1)
					+ ", " + "your turn." + "\n" + this.players[this.turn % this.players.length].printHand()
					+ "\nThe top discard is " + this.deck.getTopCard().getCardValue();

		}
		input.close();
		return toReturn;
	}

	/**
	 * Handles starting a game for the specified number of players with the
	 * specified deck.
	 */
	// These are variables

	private String handleStartGame(int numPlayers, String deckName, boolean shuffle) {
		// Set Deck
		if (deckName == null) {
			deckName = "standardDeck";
		}
		if (numPlayers < 2 || numPlayers > 4) {
			return "Incorrect number of players, must be between 2 and 4.";
		}
		this.deck = new Deck();
		this.deck.addDeck(deckName.equals("testDeck"));
		// Shuffle ?
		if (shuffle) {
			this.deck.shuffle();
		}
		// Create Players
		this.players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			Player player = new Player(i);
			this.players[i] = player;
		}

		// Dealing
		int i = 0;
		while (i < 7) {
			for (int j = 0; j < numPlayers; j++) {
				Card card = this.deck.drawCard();
				this.players[j].addCardtoHand(card);
			}
			i++;
		}
		// Set Top Card
		this.deck.setTopCard();
		System.out.println(this.deck.getTopCard());

		// Setting turn.
		this.turn = 0;
		String str = "Cards dealt." + "\nPlayer 1, your turn." + "\n"
				+ this.players[this.turn % this.players.length].printHand() + "\nThe top discard is "
				+ this.deck.getTopCard().getRank() + this.deck.getTopCard().getSuit();
		return str;
	}

	/**
	 * Handles the play-card command.
	 * 
	 * @param cardValue
	 *            - The value (rank and suit) of the card to play.
	 * @return The string message to display to the user before the game state
	 *         text.
	 */

	// Check Status concerning the string checking.
	private String handlePlayCard(String cardValue) {

		Card card = this.players[this.turn % this.players.length].getCard(cardValue);
		if (card == null) {
			return errorDraw();
		}
		Card topcard = this.deck.getTopCard();
		if (card.getEight() || card.getRank().equals(topcard.getRank()) || card.getSuit().equals(topcard.getSuit())) {
			this.deck.setTopCard(card);
			this.deck.addDiscard(card, this.players[this.turn % this.players.length]);
			this.turn++;
			if (this.players[(this.turn - 1) % this.players.length].getWin()) {
				String str = "Player" + " " + this.turn % this.players.length + " wins!";
				return str;
			}
			String str = "Card " + card.getCardValue() + " played." + "\nPlayer "
					+ (this.turn % this.players.length + 1) + ", " + "your turn." + "\n"
					+ this.players[this.turn % this.players.length].printHand() + "\nThe top discard is "
					+ this.deck.getTopCard().getCardValue();
			return str;
		}
		return errorDraw();
	}

	/**
	 * Handles the draw-card command.
	 * 
	 * @return The string message to display to the user before the game state
	 *         text.
	 */
	private String handleDrawCard() {
		Card card = this.deck.drawCard();
		this.players[turn % this.players.length].addCardtoHand(card);
		return "Card " + card + " was drawn.\nPlayer " + (turn % this.players.length + 1) + ", your turn.\n"
				+ this.players[turn % this.players.length].printHand() + "\nThe top discard is "
				+ this.deck.getTopCard();

	}

	private String errorDraw() {
		return "Card was not valid for play. Please try again." + "\nPlayer " + ((this.turn % this.players.length) + 1)
				+ ", " + "your turn." + "\n" + this.players[this.turn % this.players.length].printHand()
				+ "\nThe top discard is " + this.deck.getTopCard().getCardValue();
	}

	/**
	 * 
	 * Run the CrazyEights in console mode (that is, input is to come from the
	 * console).
	 * 
	 * THIS METHOD IS WRITTEN FOR YOU - no need to edit
	 * 
	 * @param args
	 *            Command line arguments (ignored)
	 */
	public static void main(String[] args) {
		CrazyEights game = new CrazyEights();
		System.out.println("Welcome to CrazyEights.  Enter commands.  Type 'exit' to end.");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String commandLine = scanner.nextLine();
			String result = game.handleCommand(commandLine);
			System.out.println(result);
		}

	}

}