package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MacOSLabelRenderer implements IMacOSRenderer {

	private static final int H_SPACE = 3;
	
	public Label labelToRender;
	public MacOSLabelRenderer(Label labelToRender) {
		this.labelToRender = labelToRender;
	}

	@Override
	public void render(Graphics2D g) {
		Rectangle bound = labelToRender.getBounds();
		
		// Draw the title
		g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 		
		g.setColor(Color.cyan);
		g.drawString(labelToRender.text, bound.x + H_SPACE, bound.y + 16);
	}

}
