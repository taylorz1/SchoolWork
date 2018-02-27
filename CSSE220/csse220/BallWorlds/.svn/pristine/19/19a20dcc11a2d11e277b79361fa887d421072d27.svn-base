package ballworlds;

import java.awt.geom.Point2D;
import java.util.Random;

/**
 * A ball that bounces off the walls.
 * 
 * @author Curt Clifton. Created Jan 22, 2011.
 */
public abstract class AbstractBouncer extends Ball {
	protected double vx;
	protected double vy;
	private int speedmulti = 1;
	private BallEnvironment world;
	Double cx;
	Double cy;
	
	/**
	 * Constructs a abstract bouncer in the given world.
	 * 
	 * @param world
	 */
	public AbstractBouncer(BallEnvironment e) {
		super(e);
		this.world = e;
		Random rand = new Random();
		this.cx = e.getCenterPoint().getX();
		this.cy = e.getCenterPoint().getY();
		setCenterPoint(new Point2D.Double(cx,cy));
		this.vx = rand.nextDouble()*(this.speedmulti)*Math.pow(-1, rand.nextInt(2));
		this.vy = rand.nextDouble()*(this.speedmulti)*Math.pow(-1, rand.nextInt(2));
				
	}
	
	
	@Override
	public void updatePosition() {
		Point2D center = this.getCenterPoint();
		
		double bRx = this.world.getSize().getWidth();
		double bDy = this.world.getSize().getHeight();
		
		this.cx = center.getX();
		this.cy = center.getY();
		
		if (this.cx >= bRx || this.cx <= 0) {
			this.vx = -this.vx;
		}
		
		if (this.cy >= bDy || this.cy <= 0) {
			this.vy = -this.vy;
		}
	
		
		center.setLocation(this.cx + this.vx, this.cy + this.vy);
		center = this.getCenterPoint();
		this.moveTo(center);
	}

	@Override
	public void updateSize() {
		// TODO Auto-generated method stub.

	}

	@Override
	public void updateColor() {
		// TODO Auto-generated method stub.

	}
	
	
}
