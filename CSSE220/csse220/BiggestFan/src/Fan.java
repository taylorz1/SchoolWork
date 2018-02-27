import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * This class is a graphics shape that draws a fan. It's an abstract fan that
 * just shows the fan blades.
 * 
 * @author Curt Clifton. Created Dec 8, 2010.
 */
public class Fan {
	/**
	 * Used to calculate blade width from the given blade length.
	 */
	private static final double BLADE_ASPECT_RATIO = 0.618033989;

	private static final Color BORDER_COLOR = Color.BLACK;

	private double centerX;
	private double centerY;
	private double bladeLength;
	private int numberOfBlades;
	private Color color;
	private double angleInRadians = 0.0;

	/**
	 * Constructs a new fan with the given parameters.
	 * 
	 * @param centerX
	 * @param centerY
	 * @param bladeLength
	 * @param numberOfBlades
	 * @param color
	 */
	public Fan(double centerX, double centerY, double bladeLength, int numberOfBlades, Color color) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.bladeLength = bladeLength;
		this.numberOfBlades = numberOfBlades;
		this.color = color;
	}

	/**
	 * Draws the fan onto the given Graphics2D object.
	 * 
	 * @param graphics2
	 *            Graphics object onto which to draw
	 */
	public void drawOn(Graphics2D graphics2) {
		double width = this.bladeLength;
		double heigth = this.bladeLength * Fan.BLADE_ASPECT_RATIO;
		graphics2.translate(this.centerX, this.centerY);
		for (int i = 0; i < this.numberOfBlades; i++) {
			graphics2.rotate((2 * Math.PI / this.numberOfBlades) * i);
			Rectangle2D.Double rect = new Rectangle2D.Double(0, -heigth / 2, width, heigth);
			graphics2.setColor(Fan.BORDER_COLOR);
			graphics2.setColor(this.color);
			graphics2.fill(rect);
			graphics2.setColor(BORDER_COLOR);
			// graphics2.fill(rect);
			graphics2.draw(rect);
			graphics2.rotate(-(2 * Math.PI / this.numberOfBlades) * i);
		}
		graphics2.translate(-this.centerX, -this.centerY);
	}

	// TODO: Add a helper method for drawing fan blades.

	/**
	 * Translates the fan by the given amount.
	 * 
	 * @param translateX
	 *            Amount to translate in the x direction
	 * @param translateY
	 *            Amount to translate in the y direction
	 */
	public void translate(double translateX, double translateY) {
		this.centerX += translateX;
		this.centerY += translateY;
	}

	/**
	 * Rotates the fan by the given number of degrees.
	 * 
	 * @param degreesToRotate
	 *            Number of degrees to rotate the fan (e.g. 180 to turn the fan
	 *            upside down)
	 */
	public void rotate(double degreesToRotate) {
		double radiansToRotate = Math.toRadians(degreesToRotate);
		this.angleInRadians += radiansToRotate;
	}

}
