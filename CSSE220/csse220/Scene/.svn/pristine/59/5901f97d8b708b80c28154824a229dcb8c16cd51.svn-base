import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * 
 * DONE this class creates the scene to be displayed.
 *
 * @author taylorz1. Created Sep 19, 2016.
 */
public class SceneComponent extends JComponent {
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;

		Rectangle2D.Double sky = new Rectangle2D.Double(0, 0, 750, 375);
		graphics2.setColor(Color.BLUE);
		graphics2.fill(sky);
		graphics2.draw(sky);

		Rectangle2D.Double grass = new Rectangle2D.Double(0, 375, 750, 600);
		graphics2.setColor(Color.GREEN);
		graphics2.fill(grass);
		graphics2.draw(grass);

		int x = 200;
		int y = 350;
		for (int i = 0; i < 25; i++) {
			PineTree tree = new PineTree(x + 15 * i, y, 10, 40);
			tree.drawOn(graphics2);
		}
		for (int i = 0; i < 15; i++) {
			PineTree tree = new PineTree(x + 25 * i, y + 30, 20, 80);
			tree.drawOn(graphics2);
		}
		for (int i = 0; i < 5; i++) {
			House house = new House(120 + 115 * i, y + 130, Color.RED);
			house.drawOn(graphics2);
		}
		Sun sun = new Sun();
		sun.drawOn(graphics2);
	}
}
