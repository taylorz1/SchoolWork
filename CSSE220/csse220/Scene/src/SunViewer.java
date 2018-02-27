import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * 
 * DONE this class views a sample sun component.
 *
 * @author taylorz1. Created Sep 19, 2016.
 */
public class SunViewer {
	public static final Dimension SUN_VIEWER_SIZE = new Dimension(750, 600);

	public static void main(String[] args) {
		// DONE
		JFrame frame = new JFrame();

		frame.setSize(SUN_VIEWER_SIZE);
		frame.setTitle("I see suns!");

		frame.add(new SunComponent());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
