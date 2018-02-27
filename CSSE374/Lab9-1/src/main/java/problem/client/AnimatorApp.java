package problem.client;

import problem.graphics.MainWindow;
import problem.sprites.CircleSprite;
import problem.sprites.RectangleSprite;
import problem.sprites.SpriteFactory;
import problem.sprites.SquareCircleSprite;
import problem.sprites.TowerSprite;

public class AnimatorApp {

	public static void main(String[] args) {
		SpriteFactory factory = SpriteFactory.getInstance();
		
		// TODO: Load the new composite sprite classes here
		factory.load(RectangleSprite.class, CircleSprite.class, TowerSprite.class, SquareCircleSprite.class);
		
		MainWindow mainWindow = new MainWindow("Animator Application Window", 50);		
		mainWindow.show();
	}
}
