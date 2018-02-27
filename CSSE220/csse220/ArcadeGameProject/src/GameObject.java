import java.awt.geom.Point2D;

/**
 * 
 * Every object extends from this abstract class. It contains collision
 * functionality as well as drawing descriptors.
 *
 * @author taylorz1. Created Nov 9, 2016.
 */
public abstract class GameObject implements Drawable, Temporal {

	protected GameWorld world;
	private Point2D.Double position;
	private double size;
	private Point2D.Double previousposition;

	public GameObject(GameWorld world, Point2D.Double position) {
		this.world = world;
		this.position = position;
		this.size = 10;
	}

	public Point2D.Double getPosition() {
		return this.position;
	}

	public void moveToPosition(Point2D.Double center) {
		this.position = center;
	}

	protected void setPosition(Point2D.Double position) {
		this.position = position;
	}

	protected void setSize(double i) {
		this.size = i;
	}

	protected double getSize() {
		return this.size;
	}

	protected GameWorld getWorld() {
		return this.world;
	}

	/// Time
	@Override
	public void die() {
		this.getWorld().removeObj(this);
	}

	@Override
	public void timePassed() {
		updatePosition();
	}

	public abstract void updatePosition();

	// Drawing generic obj

	abstract void onBombDamage();
	// Usually you do nothing.

	abstract void collide(GameObject m);

	// You have collided with an object m, you are another object.
	abstract void collideWithHero(Hero m);

	abstract void collideWithMonster(Monster m);

	abstract void collideWithPowerUp(PowerUp m);

	public Point2D.Double getPreviousposition() {
		return this.previousposition;
	}

	public void setPreviousposition(Point2D.Double previousposition) {
		this.previousposition = previousposition;
	}

}
