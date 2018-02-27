import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;

public class Leaf {
	// Use this constant list of colors for your leaves. Do not change this
	// constant.
	public static final Color[] COLORS = new Color[5];
	static {
		COLORS[0] = Color.GREEN;
		COLORS[1] = Color.YELLOW;
		COLORS[2] = new Color(255, 200, 0); // orange
		COLORS[3] = Color.RED;
		COLORS[4] = new Color(145, 112, 33); // brown
	}

	// TODO: add instance variables here
	double x;
	double y;
	double width;
	Color color;
	int colorIndex;
	Graphics2D g2;

	public Leaf() {
		// You can ignore this once you have written the next constructor.
	}

	public Leaf(double x, double y, double width) {
		// DONE
		this.x = x;
		this.y = y;
		this.width = width;
		this.color = Leaf.COLORS[0];
	}

	public Leaf(double x, double y, double width, int colorIndex) {
		// DONE: write this constructor
		this.x = x;
		this.y = y;
		this.width = width;
		if (colorIndex > 4) {
			this.color = Leaf.COLORS[4];
		} else {
			this.color = Leaf.COLORS[colorIndex];
		};
		this.colorIndex = colorIndex;
	}

	public void drawOn(Graphics2D graphics2) {
		// DONE: write this method
		// We are giving you code to draw an 80x40 green leaf without veins in
		// the
		// upper-left corner as an example only. In stage 1, you'll add veins to
		// the leaf and handle different center locations and sizes.
		// You'll probably want to modify and add to this code.
		this.g2 = graphics2;
		graphics2.setColor(this.color);
		Ellipse2D.Double leaf;
		graphics2.translate(this.x+this.width/2, this.y+this.width/4);
		leaf = new Ellipse2D.Double(-this.width, -this.width / 2, this.width, this.width / 2);
		graphics2.fill(leaf);
		graphics2.setColor(Color.BLACK);
		Line2D.Double line = new Line2D.Double(-this.width, -this.width/4, 0, -this.width/4);
		Line2D.Double line2 = new Line2D.Double(-this.width* 2/3, -this.width/4, -this.width/4, -this.width * 9/20);
		Line2D.Double line3 = new Line2D.Double(-this.width* 2/3, -this.width/4, -this.width/4, this.width*1/480 - this.width*1/300); // Don't pay attention to this magic numbering.
		graphics2.draw(line3);
		graphics2.draw(line2);
		graphics2.draw(line);
		graphics2.draw(leaf);
		graphics2.translate(-this.x-this.width/2, -this.y-this.width/4);

	}

	public void fall(int i) {
		Random r = new Random();
		int x = r.nextInt(11) - 5;
		this.x = this.x + x;
		this.y = this.y + i;
	}

	public void turnColor() {
		this.colorIndex += 1;
		Leaf colorChange = new Leaf(this.x, this.y, this.width, this.colorIndex);
		colorChange.drawOn(this.g2);
		
	}
}
