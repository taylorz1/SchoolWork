package linearLightsOut;

import javax.swing.JButton;

/**
 * 
 * This is the quit button class.
 *
 * @author taylorz1. Created Oct 12, 2016.
 */
public class QuitButton {
	public JButton button;

	public QuitButton() {
		this.button = createButton();
	}

	public JButton createButton() {
		JButton created = new JButton("Quit");
		return created;
	}

	public JButton getButton() {
		return this.button;
	}
}
