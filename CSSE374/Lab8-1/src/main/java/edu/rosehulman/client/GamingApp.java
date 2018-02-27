package edu.rosehulman.client;

import java.util.Scanner;

import edu.rosehulman.client.wordguessing.LetterGuessingGame;
import edu.rosehulman.client.wordguessing.WordGuessingGame;
import edu.rosehulman.gaming.GameFramework;
import edu.rosehulman.numberguessing.NumberGuessingGame;

public class GamingApp {

	public static void main(String[] args) throws Exception {

		GameFramework framework = new GameFramework();
		framework.addGame("numberguessing", new NumberGuessingGame());
		framework.addGame("wordguessing", new WordGuessingGame());
		framework.addGame("letterguessinggame", new LetterGuessingGame());

		System.out.println("Type in the game you would like to play [numberguessing, wordguessing, letterguessinggame]: ");
		Scanner scanner = new Scanner(System.in);
		String game = scanner.nextLine();
		
		int player = 1;
		if(!game.equals("numberguessing")) {
			System.out.println("Enter the number of players: ");
			player = scanner.nextInt();
		}
		
		framework.play(game, player);
		scanner.close();
	}
}
