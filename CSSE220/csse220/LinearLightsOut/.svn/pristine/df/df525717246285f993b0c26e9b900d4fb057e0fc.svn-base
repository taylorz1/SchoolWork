package linearLightsOut;

import java.util.Random;

import javax.swing.JButton;

/**
 * 
 * This is the light button object. It is a jbutton that is to represent the
 * object.
 *
 * @author taylorz1. Created Oct 12, 2016.
 */
public class LightButton {
	private JButton button;
	private int place;
	private String textb;

	public LightButton() {
		this.button = createButton();
		this.place = 0;
	}

	public JButton createButton() {
		Random rand = new Random();
		int xo = rand.nextInt(2);
		this.textb = (xo == 1) ? "X" : "O";
		JButton created = new JButton(this.textb);
		return created;
	}

	public JButton getButton() {
		return this.button;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getPlace() {
		return this.place;
	}

	public void changeText() {
		this.textb = (this.textb.equals("X")) ? "O" : "X";
		this.button.setText(textb);
	}

}
