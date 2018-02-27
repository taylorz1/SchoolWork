import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

/**
 * 
 * This class handles all things related to a bomb including exploding and
 * drawing.
 *
 * @author taylorz1. Created Nov 9, 2016.
 */
public class Bomb extends GameObject implements Drawable, Temporal {

	private Color color;
	private Shape shape;
	private Hero hero;
	private Rectangle bombExplode;
	private boolean collidable = false;

	private long timeFlag = (long) 0.0;
	private long repainter;
	private boolean exploded = false;
	private Rectangle bombExplode2;
	private boolean remoteDetonation;

	public Bomb(GameWorld world, Double position, Hero hero, boolean remoteDetonation, int size) {
		super(world, position);
		this.color = Color.BLACK;
		this.hero = hero;
		this.remoteDetonation = remoteDetonation;
		setSize(size);
		this.shape = new Ellipse2D.Double(position.x - getSize() / 2, position.y - getSize() / 2, getSize(), getSize());

		// Essentially here you need to generate things to check collision with.
		// Due to the way we check collision we need to actually draw shapes
		// where we
		// want things to die due to the effect of the bomb. Thus the undrawn
		// rectangles.
		this.bombExplode = new Rectangle((int) (position.x - getSize() / 2),
				(int) (position.y - (getSize() / 2 + getSize())), (int) (getSize()), (int) (3 * getSize()));
		this.bombExplode2 = new Rectangle((int) (position.x - (getSize() / 2 + getSize())),
				(int) (position.y - getSize() / 2), (int) (3 * getSize()), (int) (getSize()));
		this.repainter = GameWindowComponent.repaintint();

	}

	// These two functions return the rectangles that serve as an explosion
	// radius
	// to call the intersects() method on.
	public Rectangle getbombExplode() {
		return this.bombExplode;
	}

	public Rectangle getbombExplode2() {
		return this.bombExplode2;
	}

	// Time updating
	@Override
	public void timePassed() {
		if (!this.remoteDetonation) {
			// Making the bomb explode in 3 seconds.
			if (this.timeFlag >= 3000F) {
				this.exploded = true;
			}
			this.timeFlag = this.timeFlag + this.repainter;
		}
	}
	public void detonate() {
		if (this.remoteDetonation)
			this.exploded = true;
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
	public void updatePosition() {
		return;
	}

	@Override
	public void die() {
		this.hero.bombExploded();
		(this.hero.getWorld()).removeObj(this);
	}

	public boolean getExploded() {
		return this.exploded;
	}

	@Override
	void onBombDamage() {
		return;
	}

	@Override
	void collide(GameObject m) {
		return;
	}

	@Override
	void collideWithHero(Hero m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (!boundBox.intersects(selfbind)) {
			this.collidable = true;
		}
		if (boundBox.intersects(selfbind) && this.collidable) {
			m.setPosition(m.getPreviousposition());
		}
	}

	@Override
	void collideWithMonster(Monster m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (boundBox.intersects(selfbind)) {
			m.setPosition(m.getPreviousposition());
		}
	}

	@Override
	void collideWithPowerUp(PowerUp m) {
		return;
	}
}
