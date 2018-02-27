package linearLightsOut;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Runs the Linear Lights Out application.
 * 
 * @author taylorz1. Created Jan 18, 2010.
 */
public class LinearMain {
	public LinearMain() {
		String nButtonsString = JOptionPane.showInputDialog("How many buttons would you like?");
		int nButtons = Integer.parseInt(nButtonsString);
		JFrame myFrame = new JFrame("Linear Lights out!");
		JPanel buttonPanel = new JPanel();
		LightsOutFrame framework = new LightsOutFrame(nButtons, buttonPanel);
		myFrame.add(framework.getPanel(), BorderLayout.PAGE_START);

		JPanel buttonPanel2 = new JPanel();
		ButtonPanel otherbuttons = new ButtonPanel(buttonPanel2, myFrame);
		myFrame.add(otherbuttons.getPanel(), BorderLayout.PAGE_END);

		// you'll want to think about how you want to manage the state of the
		// game
		// also you want to add some buttons and stuff
		myFrame.pack();
		myFrame.setVisible(true);
	}

	/**
	 * Starts here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new LinearMain();
	}
}
