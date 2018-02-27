import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<GameWindowPanel> worlds = constructSimulations();
		JFrame frame = new GameWorldFrame(worlds);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	private static ArrayList<GameWindowPanel> constructSimulations() {
		ArrayList<GameWindowPanel> result = new ArrayList<>();
		Color c = Color.WHITE;
		GameWindowPanel gwp = new GameWindowPanel(1050,840, c);
		result.add(gwp);
		return result;
	}
}