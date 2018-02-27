import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

/**
 * 
 * This is the bomb number power up class. It sets hero things and edits bomb number.
 *
 * @author taylorz1.
 *         Created Nov 9, 2016.
 */
public class BombNumberPowerUp extends PowerUp {

	public BombNumberPowerUp(GameWorld world, Double position) {
		super(world, position, Color.ORANGE);
	}

	@Override
	void collideWithHero(Hero m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (boundBox.intersects(selfbind)) {
			this.die();
			m.increaseBombs();
		}
	}

}
