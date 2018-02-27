import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class LeafComponent extends JComponent {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Arial", Font.BOLD, 18));

		// FINAL QUESTION STAGE 0 (given as a hint; you don't need to do
		// anything with it, and can comment it out or delete it when you finish stage 1)
//		g2.drawString("Stage 0", 90, 20);
//		 // Draws big green leaf in top left corner
//		Leaf example0 = new Leaf(); 
//		example0.drawOn(g2);
		
		
		// FINAL QUESTION STAGE 1
		// uncomment the code below for stage 1
		// For full credit: DO NOT modify the code below beyond removing
		// comments
		// You will need to modify the Leaf class

		g2.drawString("Stage 1", 320, 330);
		// Draws big green leaf in bottom right corner
		Leaf example1a = new Leaf(this.getWidth() - 50, this.getHeight() - 25, 100); 
		example1a.drawOn(g2);

		// FINAL QUESTION STAGE 2
		// uncomment the code below for stage 2
		// For full credit: DO NOT modify the code below beyond removing
		// comments
		// You will need to modify the Leaf class
		g2.drawString("Stage 2", 200, 250);
		// Draws small green leaf
		Leaf example2a = new Leaf(200, 300, 40); 
		example2a.drawOn(g2);
		// Yellow leaf smaller than the middle green one and above it.
		Leaf example2b = new Leaf(215, 260, 30, 1); 
		example2b.drawOn(g2);
		// Orange leaf larger than the middle green one and below it.
		Leaf example2c = new Leaf(220, 350, 70, 2); 
		example2c.drawOn(g2);

		// FINAL QUESTION STAGE 3
		// uncomment the code below for stage 3
		// For full credit: DO NOT modify the code below beyond removing
		// comments
		// You will need to modify the Leaf class
		g2.drawString("Stage 3", 25, 75);
		Leaf example3 = new Leaf(50, 90, 50, 3); // Red
		for (int i = 0; i < 20; i++) {
			example3.drawOn(g2);
			example3.fall(8);
		}

		// FINAL QUESTION STAGE 4
		// uncomment the code below for stage 3
		// For full credit: DO NOT modify the code below beyond removing
		// comments
		// You will need to modify the Leaf class
		g2.drawString("Stage 4", 190, 50);
		Leaf example4a = new Leaf(150, 20, 50, 1); 
		for (int i = 0; i < 10; i++) {
			example4a.drawOn(g2);
			example4a.turnColor();
			example4a.fall(15);
		}

		Leaf example4b = new Leaf(300, 40, 80, 0); 
		for (int i = 0; i < 6; i++) {
			example4b.drawOn(g2);
			example4b.turnColor();
			example4b.fall(20);
		}
	}
}
