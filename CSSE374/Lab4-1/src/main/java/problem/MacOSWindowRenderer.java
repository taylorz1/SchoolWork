package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MacOSWindowRenderer implements IMacOSRenderer {
	public static final int V_SPACE = 3;
	public static final int H_SPACE = 3;
	public static final int TITLE_HEIGHT = 25;
	Window window;

	public MacOSWindowRenderer(Window window) {
		this.window = window;
	}
	
	@Override
	public void render(Graphics2D g) {
		// Draw the border
		g.setColor(Color.gray);
		g.draw3DRect(1, 1, window.getBounds().width - H_SPACE, (int)window.getBounds().height - V_SPACE, true);

		// Draw the title bar
		g.setColor(Color.orange);
		g.fill3DRect(1, 1, window.getBounds().width - H_SPACE , TITLE_HEIGHT - 5, true);
		
		// Draw the title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 		
		g.setColor(Color.white);
		g.drawString(window.title, 5, 17);
	}
}
