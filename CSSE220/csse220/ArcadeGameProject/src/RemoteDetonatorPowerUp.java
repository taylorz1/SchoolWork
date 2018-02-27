import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

public class RemoteDetonatorPowerUp extends PowerUp {
	
	

	public RemoteDetonatorPowerUp(GameWorld world, Double position) {
		super(world, position, Color.GREEN);
		// TODO Auto-generated constructor stub.
	}

	@Override
	void collideWithHero(Hero m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (boundBox.intersects(selfbind)) {
			this.die();
			m.enableRemoteDetonation();
		}
	}

}
