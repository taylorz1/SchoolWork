import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * 
 * Some authors believe that this monster isn't actually the easiest monster.
 * Others believe it's so easy it's cheesey. This author leaves it up to the player.
 * 
 * In short: this is a subset of the monster abstract class that runs in a random direction.
 *
 * @author taylorz1.
 *         Created Nov 9, 2016.
 */
public class EasyMonster extends Monster {
	private int counter = 0;

	public EasyMonster(GameWorld world, Double position) {
		super(world, position, java.awt.Color.PINK);
		this.setDyDx(0, 0);

	}

	@Override
	public void updatePosition() {
		this.counter++;
		if (this.counter > 90) {
			int ra = (int) Math.round(Math.random()*3);
			if(ra==0)
				this.setDyDx(3, 0);
			if(ra==1)
				this.setDyDx(-3, 0);
			if(ra==2)
				this.setDyDx(0, 3);
			if(ra==3)
				this.setDyDx(0, -3);
			this.counter = 0;
		}

		double x = this.getPosition().getX() + this.dx;
		double y = this.getPosition().getY() + this.dy;
		if (!super.getWorld().isInsideWorld(getPosition())) {
			this.setPosition(this.getPreviousposition());
			return;
		}
		setPreviousposition(getPosition());
		this.setPosition(new Point2D.Double(x, y));

	}

}
