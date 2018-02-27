package edu.rosehulman.gaming;

import java.util.HashMap;
import java.util.Map;

import edu.rosehulman.client.wordguessing.ICLIGame;
import edu.rosehulman.client.wordguessing.WordGuessingGame;
import edu.rosehulman.numberguessing.NumberGuessingGame;
public class GameFramework {
	
	Map<String, ICLIGame> nameToGame = new HashMap<>();
	public void play(String gameType, int users) throws Exception {
		if(nameToGame.get(gameType) == null) {
			throw new RuntimeException();
		} else {
			nameToGame.get(gameType).run(users);
		}
	}
	
	public void addGame(String gameType, ICLIGame game) {
		this.nameToGame.put(gameType, game);
	}
}
