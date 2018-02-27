package adder;


//Import classes needed for appearance
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
* A simple calculator that only does addition and subtraction of integers.
* 
*  TODO
* @author Delvin Defoe and (Your name here). Created Oct 19, 2015.
*/

/* Example of a sequence of button presses and what should be displayed after each:
 * Press 	Display
 *       	0  (initial display before any buttons are pressed)
 *  2    	2
 *  3    	23
 *  +    	23
 *  5    	5
 *  6    	56
 *  +    	79
 *  0    	0
 *  0    	0
 *  1    	1
 *  8    	18
 *  C    	0
 *  3    	3
 *  -    	-3
 *  2    	2
 *  -    	-5
 *  
 */
public class AdderMain {

	private static long sum=0;
	private static JTextField display;
	
	/**
	 * Set up the AdderGUI to respond to button clicks.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		final int WIDTH = 300, HEIGHT = 450;
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Adder");

		// Create the text field that displays numbers.
		display = new JTextField("0", 20);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setFont(new Font("Helvetica", Font.BOLD, 24));

		// Add a panel to hold the buttons
		ButtonPanel buttonPanel = new ButtonPanel(display);
		
		// Create a button to clear the display
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Helvetica", Font.BOLD, 32));
		clearButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				AdderMain.zeroSum();
				AdderMain.updateDisplay();
			}
		});
	
		// Add the components to the frame.
		frame.add(display, BorderLayout.NORTH);
		frame.add(buttonPanel);
		frame.add(clearButton, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void zeroSum(){
		sum = 0;
	}
	
	public static void addToSum(int num){
		sum = sum + num;
	}
	
	public static void updateDisplay(){
		display.setText("" + sum);
	}
	
}


