import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * It's the window.
 *
 * @author taylorz1.
 *         Created Nov 9, 2016.
 */
public class GameWorldFrame extends JFrame {
		/**
		 * Constructs a frame for displaying the given simulations.
		 * 
		 * @param panels
		 *            a non-empty list of simulation panels to display
		 */
		public GameWorldFrame(List<GameWindowPanel> panels) {
			setTitle("BomberMan");

			JPanel worldpanel = new JPanel();
			for (GameWindowPanel gwp : panels) {
				worldpanel.add(gwp);
			}
			add(worldpanel);

			add(quitButtonComponent(), BorderLayout.PAGE_END);
			
			// Set resizable to true for now
			setResizable(false);
			
			pack();
		}

		/**
		 * Adds a quit button to the bottom-right corner of the window.
		 */
		private JComponent quitButtonComponent() {
			Box quitPanel = Box.createHorizontalBox();
			quitPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
			quitPanel.add(Box.createHorizontalGlue());
			JButton quitButton = new JButton("Quit");
			quitPanel.add(quitButton);

			ActionListener quitter = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					dispose();
					System.exit(0);
				}
			};
			
			quitButton.addActionListener(quitter);
			return quitPanel;
		}

	}
