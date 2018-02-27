import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Wall extends GameObject implements Drawable {

	private Shape shape;
	private Color color;
	boolean destructable;
	
	public Wall(GameWorld world, Point2D.Double position) {
		super(world, position);
		CreateWall(position);
	}

	
	public void CreateWall(Point2D.Double position) {
		this.color = new Color(190,190,190);
		int x = (int) position.getX();
		int y = (int) position.getY();
		this.shape = new Rectangle(x, y, 60, 60); //Place then width height.
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
	public void timePassed() {
		// TODO Auto-generated method stub.
		return;
		
	}


	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub.
		return;
	}
	
	//Always of the form hero colliding with you.
	@Override
	public void collide(GameObject m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (boundBox.intersects(selfbind)) {
			m.setPosition(m.getPreviousposition());
		}
	}


	@Override
	void onBombDamage() {
		// TODO Auto-generated method stub.
		return;
	}


	@Override
	void collideWithHero(Hero m) {
		Rectangle2D boundBox = m.getShape().getBounds2D();
		Rectangle2D selfbind = this.getShape().getBounds2D();
		if (boundBox.intersects(selfbind)) {
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
		// TODO Auto-generated method stub.
		return;
	}

}