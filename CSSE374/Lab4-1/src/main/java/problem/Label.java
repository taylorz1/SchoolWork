package problem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * This class provides the support for the label widget.
 *  
 * @author Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
 */
public class Label extends AbstractComponent {
	private static final int H_SPACE = 3;

	String text;

	public Label(String text) {
		this(text, null);
	}

	public Label(String text, Rectangle bound) {
		this(null, text, bound);
	}

	public Label(IComponent parent, String text, Rectangle bound) {
		super(parent, bound);
		
		this.text = text;
		
		if(this.text == null)
			this.text = "";
	}
	
	/**
	 * Gets the text in the label.
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Sets the text in the label. 
	 * Calling this method results in the call to {@link #fireUpdate()}, 
	 * which informs the component hierarchy to re-draw itself.
	 */
	public void setText(String text) {
		this.text = text;
		this.fireUpdate();
	}
	
	protected IRendererFactory createRendererFactory() {
		return new LabelRendererFactory(this);
	}

}
