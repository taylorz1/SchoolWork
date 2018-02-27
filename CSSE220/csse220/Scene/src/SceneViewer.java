import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * 
 * DONE this class is the viewer for a given scene.
 *
 * @author taylorz1. Created Sep 19, 2016.
 */
public class SceneViewer {

	public static final Dimension SCENE_VIEWER_SIZE = new Dimension(750, 600);

	public static void main(String[] args) {
		// DONE
		JFrame frame = new JFrame();

		frame.setSize(SCENE_VIEWER_SIZE);
		frame.setTitle("I see a scene!");

		frame.add(new SceneComponent());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
