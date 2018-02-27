import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * 
 * Extends movable game object. Contains general methods for monsters.
 *
 * @author taylorz1. Created Nov 9, 2016.
 */
public abstract class Monster extends GameObjMov implements Drawable {

	private Color color;

	public Monster(GameWorld world, Point2D.Double position, Color color) {
		super(world, position);
		this.color = color;
		this.setSize(20);
	}
	public Monster(GameWorld world, Point2D.Double position, Color color, int size) {
		super(world, position);
		this.color = color;
		this.setSize(size);
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Shape getShape() {
		double x = getPosition().getX();
		double y = getPosition().getY();
		double size = getSize();
		return new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);
	}

	@Override
	public abstract void updatePosition();

	@Override
	void onBombDamage() {
		if (!(this.getWorld()).getRemove().contains(this)) {
			this.die();
		}
	}

	@Override
	public void die() {
		this.getWorld().removeObj(this);
		(this.getWorld()).removeMonster(this);
	}

	@Override
	void collide(GameObject m) {
		m.collideWithMonster(this);
	}

	@Override
	void collideWithHero(Hero m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (boundBox.intersects(selfbind)) {
			if(!m.isInvincible()){
			m.die();
			}else{
				this.die();
			}
		}

	}

	@Override
	void collideWithMonster(Monster m) {
		return;
	}

	@Override
	void collideWithPowerUp(PowerUp m) {
		return;
	}
}
