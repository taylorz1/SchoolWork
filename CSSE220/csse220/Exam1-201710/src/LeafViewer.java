import java.awt.Dimension;

import javax.swing.JFrame;


public class LeafViewer {

	/**
	 * The width of the frame.
	 */
	public static final int WIDTH = 400;
	/**
	 * The height of the frame.
	 */
	public static final int HEIGHT = 400;

	/**
	 * Starts the viewer.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setTitle("Final Question");
		LeafComponent g = new LeafComponent();
		g.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.add(g);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
