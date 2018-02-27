import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
/**
 * 
 * This is an extension of wall. It is almost entirely the same, except it can be destroyed.
 *
 * @author taylorz1.
 *         Created Nov 9, 2016.
 */
public class BrickWall extends Wall implements Drawable, Temporal {

	private Color color;
	private Rectangle shape;

	public BrickWall(GameWorld world, Double position) {
		super(world, position);
		CreateWall(position);
	}
	
	@Override
	public void CreateWall(Point2D.Double position) {
		this.color = Color.MAGENTA;
		int x = (int) position.getX();
		int y = (int) position.getY();
		this.shape = new Rectangle(x, y, 60, 60); //Place then width height.
	}
	
	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Shape getShape() {
		return this.shape;
	}
	
	@Override
	public void onBombDamage() {
		this.die();
	}
}