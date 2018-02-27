import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * 
 * ALl objects that move extend this. Essentially it adds velocity and the
 * ability to update your position.
 *
 * @author taylorz1. Created Nov 9, 2016.
 */
public abstract class GameObjMov extends GameObject {

	protected double dx;
	protected double dy;

	public GameObjMov(GameWorld world, Point2D.Double position) {
		super(world, position);
		this.dx = 0;
		this.dy = 0;
	}

	public void setDyDx(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	// Time
	@Override
	public void updatePosition() {

		double x = this.getPosition().getX() + this.dx;
		double y = this.getPosition().getY() + this.dy;
		if (!super.getWorld().isInsideWorld(getPosition())) {
			this.setPosition(this.getPreviousposition());
			return;
		}
		setPreviousposition(getPosition());
		this.setPosition(new Point2D.Double(x, y));
	}

	// Draw
	@Override
	public Shape getShape() {
		double x = getPosition().getX();
		double y = getPosition().getY();
		double size = getSize();
		return new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);
	}
}
