package problem;

import java.awt.Rectangle;

public class Button extends AbstractComponent {

	/**
	 * This class provides the support for the label widget.
	 * 
	 * @author Chandan R. Rupakheti (chandan.rupakheti@rose-hulman.edu)
	 */
	private static final int H_SPACE = 3;

	String text;

	public Button(String text) {
		this(text, null);
	}

	public Button(String text, Rectangle bound) {
		this(null, text, bound);
	}

	public Button(IComponent parent, String text, Rectangle bound) {
		super(parent, bound);

		this.text = text;

		if (this.text == null)
			this.text = "";
	}

	/**
	 * Gets the text in the label.
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Sets the text in the label. Calling this method results in the call to
	 * {@link #fireUpdate()}, which informs the component hierarchy to re-draw
	 * itself.
	 */
	public void setText(String text) {
		this.text = text;
		this.fireUpdate();
	}

	protected IRendererFactory createRendererFactory() {
		return new ButtonRendererFactory(this);
	}

}
