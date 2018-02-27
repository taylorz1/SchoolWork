package starBarGraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ButtonPanel extends JPanel{
	private ArrayList<JButton> buttonList;
	private JTextField display;
	private JFrame frame;
	private JTextArea label;
	
	private class ButtonListener implements ActionListener  {
		@Override
		public void actionPerformed(ActionEvent e) {
			// to be populated with *
			String first =""; 
			String second = "";
			String third= "";
			String fourth= "";
			
			JButton b = (JButton) e.getSource();
			String textonLabel = ButtonPanel.this.label.getText();
			String type = b.getText();
			
			// Split the lines
			int length = textonLabel.length();
			int firstbreak = 0;
			int secondbreak = 0;
			int thirdbreak = 0;
			
			int j = 0;
			while (!textonLabel.substring(j,j+1).equals("2")) {
				j++;
			}
			firstbreak = j+1;
			//System.out.println(firstbreak);
			while (!textonLabel.substring(j,j+1).equals("3")) {
				j++;
			}
			secondbreak = j+2;
			//System.out.println(secondbreak);
			while (!textonLabel.substring(j,j+1).equals("4")) {
				j++;
			}
			thirdbreak = j+3;
			//System.out.println(thidbreak);
			
			
			for (int i = 0; i < firstbreak-4; i++) {
				first= first + "*";
			}
			for (int i = firstbreak; i < secondbreak-4; i++) {
				second= second + "*";
			}
			for (int i = secondbreak; i < thirdbreak-4; i++) {
				third= third + "*";
			}
			for (int i = thirdbreak; i < textonLabel.length(); i++) {
				fourth= fourth + "*";
			}
			
			ButtonPanel.this.label.setText("1|" + first +"\n" +"2|" + second + "\n" + "3|" + third + "\n" + "4|" + fourth);
			
		}
	}
	
	
	public ButtonPanel(JFrame frame, JTextArea label){
		this.buttonList = new ArrayList<JButton>();
		this.addButtons();
		this.label = label;
	}
	
	
	private void addButtons(){
		for (int i = 1; i < 5; i++) {
			JButton b = new JButton("[" + i + "]");
			this.buttonList.add(b);
			b.addActionListener(new ButtonListener());
			this.add(b);
		}
	}
}
