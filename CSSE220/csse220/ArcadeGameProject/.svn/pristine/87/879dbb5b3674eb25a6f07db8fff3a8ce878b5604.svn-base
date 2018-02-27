import java.awt.Color;

import javax.swing.JPanel;


/**
 * 
 * An intermediate step. Creates GameWindowComponents. This exists incase we
 * ever decide to make more than one GameWindowComponent (we want to draw more
 * than one frame for instance).
 *
 * @author taylorz1.
 *         Created Oct 27, 2016.
 */
public class GameWindowPanel extends JPanel{
	public GameWindowPanel(int width, int height, Color color) {
		GameWorld world = new GameWorld(width, height, color);
		GameWindowComponent worldComponent = new GameWindowComponent(world);
		add(worldComponent);
	}
}
