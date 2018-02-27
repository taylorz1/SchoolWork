import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * 
 * This counts time intervals and asks things politely to redraw.
 *
 * @author taylorz1. Created Oct 27, 2016.
 */
public class GameWindowComponent extends JComponent {
	private static final int FRAMES_PER_SECOND = 20;
	private static final long REPAINT_INTERVAL_MS = 1000 / FRAMES_PER_SECOND;

	private GameWorld world;
	private boolean hasShownNullErrorMessage = false;

	public GameWindowComponent(GameWorld world) {
		this.world = world;

		setPreferredSize(world.getSize());
		setMaximumSize(world.getSize());

		HeroMovHandler movHandler = new HeroMovHandler(this);
		setFocusable(true);

		addKeyListener(movHandler);

		Runnable repainter = new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(REPAINT_INTERVAL_MS);
						repaint();
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
					exception.getStackTrace();
				}
			}
		};
		new Thread(repainter).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		drawDrawable(g2, this.world);

		ArrayList<Drawable> drawableParts = this.world.getDrawableParts();
		for (Drawable d : drawableParts) {
			drawDrawable(g2, d);
		}
	}

	private void drawDrawable(Graphics2D g2, Drawable d) {
		Color color = d.getColor();
		if (color == null) {
			showNullPointerMessage("color", d);
			return;
		}
		Shape shape = d.getShape();
		if (shape == null) {
			showNullPointerMessage("shape", d);
			return;
		}
		g2.setColor(color);
		g2.fill(shape);
	}

	private void showNullPointerMessage(String nullAttribute, Drawable d) {
		if (!this.hasShownNullErrorMessage) {
			this.hasShownNullErrorMessage = true;
			String message = "I could not draw this Drawable object of type " + d.getClass().getName() + " because its "
					+ nullAttribute + " is null.\n";
			JOptionPane.showMessageDialog(null, message, "Null pointer exception", JOptionPane.ERROR_MESSAGE);
		}
	}

	public GameWorld getWorld() {
		return this.world;
	}

	public static long repaintint() {
		return REPAINT_INTERVAL_MS;
	}
}
