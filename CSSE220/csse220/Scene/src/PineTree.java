import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

/**
 * 
 * DONE this class draws a PineTree with the given specifications.
 *
 * @author taylorz1. Created Sep 19, 2016.
 */
public class PineTree {
	private int x;
	private int y;
	private int width;
	private int height;

	public PineTree(int x, int y, int width, int height) {
		// DONE: save off the parameters into instance variables
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	public void drawOn(Graphics2D g2) {
		// DONE: Draw the trees.
		g2.translate(this.x, this.y);
		Rectangle2D.Double treeTrunk = new Rectangle2D.Double(this.width * 1 / 3, this.height * 2 / 3, this.width / 3,
				this.height / 3);
		Color brown = new Color(145, 112, 33);
		g2.setColor(brown);
		g2.draw(treeTrunk);
		g2.fill(treeTrunk);

		Color green = new Color(40, 135, 22);
		g2.setColor(green);
		int[] xpoints = { 0, this.width / 2, this.width };
		int[] ypoints = { this.height * 2 / 3, 0, this.height * 2 / 3 };
		Polygon branches = new Polygon(xpoints, ypoints, 3);
		g2.fill(branches);
		g2.draw(branches);
		g2.translate(-this.x, -this.y);
	}
}