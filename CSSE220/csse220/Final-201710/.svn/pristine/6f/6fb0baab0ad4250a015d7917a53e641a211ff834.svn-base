package gravity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * Component where drawing is done. Also handles the threads.
 * 
 * You'll mostly make modifications to this class, as well as develop some
 * classes of your own.
 * 
 * @author hewner
 *
 */
@SuppressWarnings("serial")
public class GravityRectComponent extends JComponent {

	public static final int FLOOR_HEIGHT = 450;
	public ArrayList<GravityRect> rects;

	public GravityRectComponent() {
		this.rects = new ArrayList<GravityRect>();
		this.rects.add(new GravityRect(200, 200));
		this.rects.add(new GravityRect(400, 350));

		GravityMouseHandler mousehandler = new GravityMouseHandler();
		addMouseListener(mousehandler);
		addMouseMotionListener(mousehandler);

		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(50);
						repaint();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		};
		new Thread(repainter).start();
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		// HINT: you might want to make rects a
		// field and move initialization to a
		// constructor

		// the ground
		g2.setColor(new Color(0, 100, 0));
		g2.fillRect(0, FLOOR_HEIGHT, getWidth(), getHeight() - FLOOR_HEIGHT);

		for (GravityRect r : this.rects) {
			r.drawOn(g2);
		}

	}

	public class GravityMouseHandler implements MouseMotionListener, MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			if (e.isControlDown()) {
				GravityRect rect= new FastRect(x, y);
				GravityRectComponent.this.rects.add(rect);
			} else if (e.isShiftDown()) {
				GravityRect rect= new PinkRect(x, y);
				GravityRectComponent.this.rects.add(rect);
			} else {
			GravityRect rect= new GravityRect(x, y);
			GravityRectComponent.this.rects.add(rect);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//nothing
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			//nothing
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//nothing
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// nothing to do
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// nothing to do
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// nothing to do
		}
	}

}
