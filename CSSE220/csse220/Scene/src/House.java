import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

/**
 * 
 * DONE this class contains the methods required for drawing a house on a given
 * canvas.
 *
 * @author taylorz1. Created Sep 19, 2016.
 */
public class House {
	private static final int HEIGHT = 50;
	private static final int WIDTH = 100;
	private static final int ROOF_HEIGHT = 20;

	private Color color;
	private int x;
	private int y;

	public House(int x, int y, Color color) {
		// DONE: save off the parameters into instance variables
		this.x = x;
		this.y = y;
		this.color = color;
	}

	/**
	 * 
	 * DONE This draws a house using a polygon and and a rectangle..
	 *
	 * @param g2
	 */
	public void drawOn(Graphics2D g2) {
		// DONE: Draw the house body (a rectangle) and the roof (3 lines or a
		// Polygon)
		Rectangle2D.Double rect = new Rectangle2D.Double(this.x, this.y, WIDTH, HEIGHT);
		g2.setColor(color);
		g2.fill(rect);
		g2.draw(rect);
		int[] xpoints = { this.x, this.x + WIDTH / 2, this.x + WIDTH };
		int[] ypoints = { this.y, this.y - ROOF_HEIGHT, this.y };

		Polygon tri = new Polygon(xpoints, ypoints, 3);
		g2.draw(tri);
	}

}