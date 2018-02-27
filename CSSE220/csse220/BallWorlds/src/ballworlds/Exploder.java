package ballworlds;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * 
 * DONE this creates an exploder ball that explodes when it reaches a random size that
 * is larger than its initial size and splits into 2 smaller exploder balls.
 *
 * @author taylorz1.
 *         Created Oct 17, 2016.
 */
public class Exploder extends AbstractBouncer {
	private Color color;
	private double sizestep = .3;
	private double size;
	private double terminate;
	private double start = 7;
	
	public Exploder(BallEnvironment world) {
		
		super(world);
		Random rand = new Random();
		this.terminate = this.start*(rand.nextInt(11)+2);
		this.color = Color.ORANGE;
		
	}
	
	public Exploder(BallEnvironment world, Point2D center) {
		super(world);
		this.setCenterPoint(center);
		Random rand = new Random();
		this.cx = center.getX();
		this.cy = center.getY();
		this.size = this.start;
		this.color = Color.ORANGE;
		this.terminate = this.start*(rand.nextInt(11)+2);
	}
	
	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void updateSize() {
		if (this.size > this.terminate) {
			// add 2 new things to the world and remove this ball
			getWorld().addBall(new Exploder(this.getWorld(), new Point2D.Double(this.cx, this.cy)));
			getWorld().addBall(new Exploder(this.getWorld(), new Point2D.Double(this.cx, this.cy)));
			getWorld().removeBall(this);
		}
		
		this.size = this.size + this.sizestep;
	}
	
	@Override
	public double getDiameter() {
		// TODO Auto-generated method stub.
		return this.size;
	}

}
