package ballworlds;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * 
 * DONE This creates a pulsar that swaps from black to white and then back to black.
 *
 * @author taylorz1.
 *         Created Oct 17, 2016.
 */
public class Pulsar extends Ball {
	
	private Color current;
	private boolean decrement;

	public Pulsar(BallEnvironment e) {
		super(e);
		Random rand = new Random(); // For creating random center
		int y = e.getSize().height;
		int x = e.getSize().width;
		setCenterPoint(new Point2D.Double(rand.nextInt(x+1),rand.nextInt(y+1)));
		this.current = Color.BLACK;
		this.decrement = false;
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub.
		return this.current;
	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub.
		return; //doesn't move
	}

	@Override
	public void updateSize() {
		// TODO Auto-generated method stub.
		return; //doesn't change size
	}

	@Override
	public void updateColor() {
		int color = this.current.getBlue();
		if (this.decrement) {
			color = color - 4;
			if (color < 0) {
				color = 0;
				this.decrement = false;
			}
		} else {
			color = color + 4;
			if (color > 255) {
				color = 255;
				this.decrement = true;
			}
		}
		this.current = new Color(color, color, color);
		

	}

	@Override
	public double getDiameter() {
		// TODO Auto-generated method stub.
		return 20;
	}

}
