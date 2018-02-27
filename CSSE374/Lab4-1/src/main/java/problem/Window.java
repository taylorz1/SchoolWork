package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class provides the support for the main window widget and also serves as
 * the top-level component in the component hierarchy.
 *  
 * @author Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
 */
public class Window extends AbstractComponent {

	
	String title;
	private JFrame frame;
	private JPanel panel;

	public Window() {
		this(null);
	}

	public Window(String title) {
		this(title, null);
	}

	@SuppressWarnings("serial")
	public Window(String title, Rectangle bound) {
		super(null, bound);

		if(title == null)
			this.title = "";
		else
			this.title = title;

		this.frame = new JFrame(this.title);
		this.frame.setUndecorated(true);	
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				draw((Graphics2D) g);
			}
		};
		this.frame.setContentPane(this.panel);
	}

	@Override
	public final void fireUpdate() {
		this.panel.repaint();
	}
	
	public void show() {
		this.frame.setBounds(this.getBounds());
		this.frame.setVisible(true);
	}

	@Override
	protected IRendererFactory createRendererFactory() {
		return new WindowRendererFactory(this);
	}
}
