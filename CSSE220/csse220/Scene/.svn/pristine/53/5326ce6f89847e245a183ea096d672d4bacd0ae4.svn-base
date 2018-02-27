import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * DONE this class draws a sun with given parameters or with no inputed
 * parameters.
 *
 * @author taylorz1. Created Sep 19, 2016.
 */
public class Sun {

	private static final Color BORDER_COLOR = Color.BLACK;
	private static final int NUMBER_OF_RAYS = 8;
	private static final double RAY_LENGTH_SCALE = 0.5;
	private static final double RAY_WIDTH_SCALE = 0.1;
	private static final double RAY_DISTANCE_FROM_SUN_SCALE = .2;
	private static final double DEFAULT_SUN_DIAMETER = 100.0;
	private static final double DEFAULT_SUN_X = 100.0;
	private static final double DEFAULT_SUN_Y = 100.0;
	private static final Color DEFAULT_SUN_COLOR = Color.YELLOW;
	private static final double LITTLE_SUNS_X_OFFSET = 50;

	private double x;
	private double y;
	private double circleDiameter;
	private double rayLength;
	private double rayWidth;
	private double rayDistanceFromSun;
	private Color color;

	public Sun() {
		this.x = DEFAULT_SUN_X;
		this.y = DEFAULT_SUN_Y;
		this.circleDiameter = DEFAULT_SUN_DIAMETER;
		this.color = DEFAULT_SUN_COLOR;
		this.rayLength = DEFAULT_SUN_DIAMETER * RAY_LENGTH_SCALE;
		this.rayWidth = DEFAULT_SUN_DIAMETER * RAY_WIDTH_SCALE;
		this.rayDistanceFromSun = DEFAULT_SUN_DIAMETER * RAY_DISTANCE_FROM_SUN_SCALE;
	}

	public Sun(double x, double y, double circleDiameter, Color color) {
		this.x = x;
		this.y = y;
		this.circleDiameter = circleDiameter;
		this.rayLength = circleDiameter * RAY_LENGTH_SCALE;
		this.rayWidth = circleDiameter * RAY_WIDTH_SCALE;
		this.rayDistanceFromSun = circleDiameter * RAY_DISTANCE_FROM_SUN_SCALE;
		this.color = color;
	}

	public void drawOn(Graphics2D g2) {
		// DONE: Draw the sun without rays
		Ellipse2D.Double circle = new Ellipse2D.Double(this.x, this.y, this.circleDiameter, this.circleDiameter);
		g2.setColor(this.color);
		g2.fill(circle);
		g2.draw(circle);

		double radius = this.circleDiameter / 2;
		double rot = 2 * Math.PI / NUMBER_OF_RAYS;
		g2.translate(this.x + radius, this.y + radius);
		for (int i = 0; i < NUMBER_OF_RAYS; i++) {
			Rectangle2D.Double ray = new Rectangle2D.Double(0, radius + this.rayDistanceFromSun, this.rayWidth,
					this.rayLength);
			g2.fill(ray);
			g2.draw(ray);
			g2.rotate(rot);

		}
		g2.translate(-this.x - radius, -this.y - radius);
	}

}
