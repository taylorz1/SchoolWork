import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class MyComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Draw stuff:
		// Construct an object, then draw it.
		
		  Rectangle2D.Double box = new
		  Rectangle2D.Double(this.getWidth()/2-100, 40, 200, 60);
		  g2.setColor(Color.YELLOW); g2.fill(box); g2.setColor(Color.GREEN);
		  g2.draw(box);
		 
		/*for (int i = 0; i < (this.getWidth() / 10) - 2; i++) {
			Rectangle2D.Double box = new Rectangle2D.Double(10, 10, 10 + 10 * i, 10 + 10 * i);
			g2.draw(box);
		} 
		Ellipse2D.Double ellipse = new Ellipse2D.Double(60, this.getWidth() + 20, 30, 20);
		Point2D.Double point1 = new Point2D.Double(10, this.getWidth() + 5);
		Point2D.Double point2 = new Point2D.Double(40, this.getWidth()+ 25);
		Line2D.Double Line1 = new Line2D.Double(point1, point2);
		Line2D.Double Line2 = new Line2D.Double(7, this.getWidth() + 3, 12, this.getWidth() + 60);
		Ellipse2D.Double circle = new Ellipse2D.Double(20, this.getWidth()+29, 10, 10);
		g2.draw(ellipse);
		g2.draw(circle);
		g2.draw(Line1);
		g2.draw(Line2);
		
		Font font = new Font("Time New Roman", Font.PLAIN, 40);
		g2.setFont(font);
		g2.drawString("Your Momma", 30, this.getWidth()+45);
		Font font2 = new Font("Arial", Font.ITALIC, 30);
		g2.setFont(font2);
		g2.drawString("Let's not go there please", 35, this.getWidth()+70); */
	}

}
