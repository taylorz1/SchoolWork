import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D.Double;

public class HeartObject extends GameObject implements Drawable {

	private Shape shape;
	private Color color;
	
	public HeartObject(GameWorld world, Double position) {
		super(world, position);
		double x = getPosition().getX();
		double y = getPosition().getY();
		Path2D shaper = new java.awt.geom.Path2D.Double();
		shaper.moveTo(x,y);
		shaper.lineTo(x-15, y-30);
		shaper.lineTo(x-10, y-30);
		shaper.lineTo(x, y-20);
		shaper.lineTo(x+15, y-30);
		shaper.lineTo(x+20, y-30);
		shaper.lineTo(x, y);
		this.shape = shaper;
		this.setColor(color.RED);
	}
	public HeartObject(GameWorld world, Double position, Color blue) {
		super(world, position);
		double x = getPosition().getX();
		double y = getPosition().getY();
		Path2D shaper = new java.awt.geom.Path2D.Double();
		shaper.moveTo(x,y);
		shaper.lineTo(x-15, y-30);
		shaper.lineTo(x-10, y-30);
		shaper.lineTo(x, y-20);
		shaper.lineTo(x+15, y-30);
		shaper.lineTo(x+20, y-30);
		shaper.lineTo(x, y);
		this.shape = shaper;
		this.setColor(color.MAGENTA);
	}
	@Override
	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color=color;
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
	void onBombDamage() {
		return;
	}

	@Override
	void collide(GameObject m) {
		return;
	}

	@Override
	void collideWithHero(Hero m) {
		return;
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
