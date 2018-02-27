package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class LinuxWindowRenderer implements ILinuxRenderer {
	public static final int V_SPACE = 3;
	public static final int H_SPACE = 3;
	public static final int TITLE_HEIGHT = 25;


	Window window;

	public LinuxWindowRenderer(Window window) {
		this.window = window;
	}
	
	@Override
	public void render(Graphics2D g) {
		// Draw the border
		g.setColor(new Color(48,0,0));
		g.draw3DRect(1, 1, window.getBounds().width - H_SPACE, window.getBounds().height - V_SPACE, true);

		// Draw the title bar
		g.setColor(new Color(96,0,0));
		g.fill3DRect(1, 1, (int)window.getBounds().width - H_SPACE , TITLE_HEIGHT, true);
		
		// Draw the title
		g.setFont(new Font("Arial", Font.PLAIN, 17)); 		
		g.setColor(Color.white);
		g.drawString(window.title, 5, 20);
	}

}
