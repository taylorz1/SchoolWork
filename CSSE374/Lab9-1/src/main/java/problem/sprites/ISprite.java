package problem.sprites;

import java.awt.Dimension;
import java.awt.Shape;
import java.util.Iterator;

// TODO: Add support for complex (composite) sprites
public interface ISprite{
	public void move(Dimension space);
	public Shape getShape();
	public Iterator<ISprite> iterator();
}
