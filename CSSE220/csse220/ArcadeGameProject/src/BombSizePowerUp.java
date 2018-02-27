import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

public class BombSizePowerUp extends PowerUp {

	public BombSizePowerUp(GameWorld world, Double position) {
		super(world, position, Color.CYAN);
	}

	@Override
	void collideWithHero(Hero m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (boundBox.intersects(selfbind)) {
			this.die();
			m.increaseBombSize();
		}
	}

}
