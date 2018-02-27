package linearLightsOut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * This handles everything to do the quit and new game button.
 *
 * @author taylorz1. Created Oct 12, 2016.
 */
public class ButtonPanel {
	private JPanel panel;
	private QuitButton q;
	private NewGameButton ng;
	private JFrame frame;

	public ButtonPanel(JPanel panel, JFrame frame) {
		this.panel = panel;
		this.q = new QuitButton();
		this.ng = new NewGameButton();
		this.frame = frame;
		generate();
	}

	public void generate() {
		class clickerListen implements ActionListener {
			private QuitButton q;
			private JPanel panel2;

			public clickerListen(QuitButton q, JPanel panel2) {
				this.q = q;
				this.panel2 = panel2;
			}

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}

		class clickerListen2 implements ActionListener {
			private NewGameButton ng;
			private JFrame frame2;

			public clickerListen2(NewGameButton ng, JFrame frame) {
				this.ng = ng;
				this.frame2 = frame;
			}

			public void actionPerformed(ActionEvent e) {
				this.frame2.setVisible(false);
				this.frame2.dispose();
				new LinearMain();
			}
		}

		this.q.getButton().addActionListener(new clickerListen(this.q, this.panel));
		this.panel.add(this.q.getButton());
		this.ng.getButton().addActionListener(new clickerListen2(this.ng, this.frame));
		this.panel.add(this.ng.getButton());

	}

	public JPanel getPanel() {
		return this.panel;
	}

}
