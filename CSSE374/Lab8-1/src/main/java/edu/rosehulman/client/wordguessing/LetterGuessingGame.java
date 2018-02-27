package edu.rosehulman.client.wordguessing;

import java.util.Scanner;

public class LetterGuessingGame extends AbstractCLIGame {
	private boolean end;
	private boolean win;
	private String user;
	
	private int high;
	private int low;
	private int mid;
	
	private Scanner scanner;
	
	private int attempt;
	
	public LetterGuessingGame() {
		super();
		this.win = false;
		this.end = false;
		scanner = new Scanner(System.in);
	}
	
	private void readUserName() {
		System.out.println("Enter your name: ");
		this.user = scanner.nextLine();
		
	}
	
	@Override
	protected void init() {
		System.out.println("Welcome to the Letter Guessing Game!");

		this.readUserName();

		System.out.println("Howdy, " + this.user + "! Think of a letter and I will guess it within 7 attempts.");
		System.out.println("Press return if you are ready!");
		this.scanner.nextLine();
		
		this.high = 90;
		this.low = 64;
		this.mid = (high - low) / 2 + low;
		
		this.attempt = 0;
	}

	@Override
	protected void nextTurn(int user) {
		System.out.format("[Attempt #%d] Is your letter %c? Guess [after / before / correct] ", ++this.attempt, (char) this.mid);
		String option = scanner.nextLine();
		option = option.toLowerCase();
		if(option.startsWith("a")) {
			low = mid;
		}
		else if(option.startsWith("b")) {
			high = mid;
		}
		else {
			this.win = true;
			this.end = true;
		}
		
		int delta = high - low;
		if(delta > 0) {
			this.mid = this.low + delta / 2;
		}
		else {
			this.win = false;
			this.end = true;
		}
	}

	@Override
	protected boolean isGameOver() {
		return this.end;
	}

	@Override
	protected void showResults() {
		if(this.win) {
			System.out.format("Hurrah! I won, " + this.user + "!");
		}
		else {
			System.out.format("Hey, " + this.user + "! You won! Congratulations!");
		}
	}
}
