import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * 
 * DONE This is the viewer for a given Pine Tree test.
 *
 * @author taylorz1. Created Sep 19, 2016.
 */
public class PineTreesViewer {
	public static final Dimension PINETREES_VIEWER_SIZE = new Dimension(500, 400);

	public static void main(String[] args) {
		// DONE Auto-generated method stub.
		JFrame frame = new JFrame();

		frame.setSize(PINETREES_VIEWER_SIZE);
		frame.setTitle("I see trees!");

		frame.add(new PineTreeComponent());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
