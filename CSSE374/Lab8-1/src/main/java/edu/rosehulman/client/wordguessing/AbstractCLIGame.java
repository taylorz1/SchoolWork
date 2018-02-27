package edu.rosehulman.client.wordguessing;

import java.util.Scanner;

public abstract class AbstractCLIGame implements ICLIGame {

	private int users;
	Scanner scanner;
	
	public AbstractCLIGame() {
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void run(int users) throws Exception {
		this.users = users;
		this.init();
		int user = this.users;
		while(!(this.isGameOver())) {
			this.nextTurn(user);
			user = (user + 1) % this.users;
		}
		this.showResults();
	}
	
	protected Scanner getScanner() {
		return this.scanner;
	}
	
	protected int getUsers() {
		return this.users;
	}
	
	abstract protected void init() throws Exception;
	
	abstract protected void nextTurn(int user);
	
	abstract protected boolean isGameOver();
	
	abstract protected void showResults();

}
