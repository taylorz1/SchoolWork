package linearLightsOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * 
 * This handles virtually everything to do with light buttons.
 *
 * @author taylorz1. Created Oct 12, 2016.
 */
public class LightsOutFrame {
	private int numberButton;
	private ArrayList<LightButton> buttons = new ArrayList<>();
	private JPanel panel;

	public LightsOutFrame(int numberButtons, JPanel panel) {
		this.numberButton = numberButtons;
		this.panel = panel;
		generate();
	}

	private void generate() {
		class clickerListen implements ActionListener {
			private LightButton b;
			private JPanel panel;

			public clickerListen(LightButton b, JPanel panel2) {
				this.b = b;
				this.panel = panel2;
			}

			public void actionPerformed(ActionEvent e) {
				b.changeText();
				ChangeNeighbors(b.getPlace());
			}
		}

		for (int i = 0; i < this.numberButton; i++) {
			LightButton button = new LightButton();
			button.setPlace(i);
			this.buttons.add(button);
			button.getButton().addActionListener(new clickerListen(button, this.panel));

			this.panel.add(button.getButton());
		}
	}

	public void ChangeNeighbors(int i) {
		if (i == 0) {
			// Do the next button
			this.buttons.get(i + 1).changeText();
			getWin();
			return;
		}
		if (i == this.buttons.size() - 1) {
			// Do previous button
			this.buttons.get(i - 1).changeText();
			getWin();
			return;
		}
		// Do Both buttons!
		this.buttons.get(i + 1).changeText();
		this.buttons.get(i - 1).changeText();
		getWin();
	}

	public ArrayList<LightButton> getButtons() {
		return this.buttons;
	}

	public JPanel getPanel() {
		return this.panel;
	}

	// Get Wins!
	public void getWin() {
		String text = this.buttons.get(0).getButton().getText();
		int matches = 0;
		for (int i = 0; i < this.buttons.size(); i++) {
			if (this.buttons.get(i).getButton().getText().equals(text)) {
				matches++;
			}
		}

		if (matches == this.numberButton) {
			System.out.println("You have won! Try playing a new game.");
		}
	}
}
