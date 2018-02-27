package adder;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	
	private Font buttonFont; 
	private ArrayList<JButton> buttonList;
	private final JTextField display;
	private boolean isNumberAdded;
	
	private class NumberButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			String number = display.getText() + b.getText();
			if(getIsNumberAdded()){
				number = b.getText();
				updateIsNumberAdded(false);
			}
			if(number.startsWith("0")){
				number  = number.substring(1);
			}
			System.out.println(number);
			display.setText(number);
		}
	}
	
	private class AddSubButtonListener implements ActionListener{
		String sign;
		AddSubButtonListener(String sign){
			this.sign = sign;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			int number = Integer.parseInt(display.getText());
			if(this.sign.equals("-")){
				number = 0 - number;
			}
			AdderMain.addToSum(number);
			AdderMain.updateDisplay();
			updateIsNumberAdded(true);	
		}
	}
	
	public boolean getIsNumberAdded(){
		return this.isNumberAdded;
	}
	
	public ButtonPanel(JTextField display){
		this.display = display;
		this.configure();
		this.buttonList = new ArrayList<JButton>();
		this.addButtons();
	}
	
	private void configure(){
		this.buttonFont = new Font("Helvetica", Font.BOLD, 48);
		this.setLayout(new GridLayout(4, 4, 5, 5));
	}
	
	private void  updateIsNumberAdded(boolean isNumberAdded){
		this.isNumberAdded = isNumberAdded;
	}
	
	private void addButtons(){
		// Create the top nine buttons (1-9) and add them to the panel.
		// label each button with a string representing the button's value.
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				JButton b = new JButton(7 - 3*row + col + "");
				b.setFont(this.buttonFont);
				this.buttonList.add(b);
				b.addActionListener(new NumberButtonListener());
				this.add(b);
			}
		}
		
		// Create and add the bottom three buttons.
		String[] remainingButtonLabels = {"-", "0", "+"};
		for (String s : remainingButtonLabels) {
			JButton b = new JButton(s);
			b.setFont(this.buttonFont);
			if(s.equals("0")){
				b.addActionListener(new NumberButtonListener());
			}else {
				b.addActionListener(new AddSubButtonListener(s));
			}
			this.buttonList.add(b);
			this.add(b);
		}
	}
}
