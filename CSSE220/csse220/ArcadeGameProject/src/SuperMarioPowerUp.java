import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

public class SuperMarioPowerUp extends PowerUp {

	public SuperMarioPowerUp(GameWorld world, Double position) {
		super(world, position, Color.YELLOW);
		// TODO Auto-generated constructor stub.
	}

	@Override
	void collideWithHero(Hero m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (boundBox.intersects(selfbind)) {
			this.die();
			m.setInvincible(true);
			
		}
	}
	
	@Override
	public Color getColor() {
		Color color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
		return color; 
	}

}
