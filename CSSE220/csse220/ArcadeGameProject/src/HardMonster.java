import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * 
 * See easy monster.
 *
 * @author taylorz1.
 *         Created Nov 9, 2016.
 */
public class HardMonster extends Monster {
	private GameWorld world;
	private int counter = 0;
	private boolean xOrY = false;

	public HardMonster(GameWorld world, Double position) {
		super(world, position, Color.RED);
		this.world = world;

	}

	@Override
	public void updatePosition() {
		setPreviousposition(getPosition());
		this.counter++;
		if (this.counter > 100) {
			this.counter = 0;
			this.xOrY = !this.xOrY;
		}

		double x = this.getPosition().getX();
		double y = this.getPosition().getY();
		double heroDist = Math.sqrt(Math.pow((this.world.getHero().getPosition().getX()) - this.getPosition().x, 2)
				+ Math.pow((this.world.getHero().getPosition().getY()) - this.getPosition().y, 2));
		if (heroDist < 300) {
			if (this.xOrY) {
				if (x > this.world.getHero().getPosition().getX())
					this.setPosition(new Point2D.Double(x - 2.5, y));
				else
					this.setPosition(new Point2D.Double(x + 2.5, y));
			} else {
				if (y > this.world.getHero().getPosition().getY())
					this.setPosition(new Point2D.Double(x, y - 2.5));
				else
					this.setPosition(new Point2D.Double(x, y + 2.5));
			}

			if (!super.getWorld().isInsideWorld(getPosition())) {
				this.setPosition(this.getPreviousposition());
				return;
			}
		} else {
			if (this.xOrY) {
				this.setPosition(new Point2D.Double(x + 1.5, y));
			} else {
				this.setPosition(new Point2D.Double(x - 1.5, y));
			}
		}

	}

}