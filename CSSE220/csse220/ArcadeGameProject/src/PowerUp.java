 import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public abstract class PowerUp extends GameObject implements Drawable {

	private Shape shape;
	private Color color;
	private boolean bombDamaged = false;
	private Point2D.Double position;

	public PowerUp(GameWorld world, Point2D.Double position,Color color) {
		super(world, position);
		this.position = position;
		this.shape = new Ellipse2D.Double(position.x, position.y, 5, 5);
		this.color = color;
		this.setSize(20);
	}
	
	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public Shape getShape() {
		double x = this.position.getX();
		double y = this.position.getY();
		double size = getSize();
		return new Ellipse2D.Double(x-size /2, y-size/2, size, size);
	}

	public void updatePosition() {
		return;
	}

	@Override
	void onBombDamage() {
		if (!( (GameWorld) this.getWorld()).getRemove().contains(this)) {
			//this.die();
		}
	}

	@Override
	void collide(GameObject m) {
		m.collideWithPowerUp(this);
	}
	
	@Override
	abstract void collideWithHero(Hero m);

	@Override
	void collideWithMonster(Monster m) {
		// TODO Auto-generated method stub.

	}

	@Override
	void collideWithPowerUp(PowerUp m) {
		// TODO Auto-generated method stub.
		
	}

	

	

}
