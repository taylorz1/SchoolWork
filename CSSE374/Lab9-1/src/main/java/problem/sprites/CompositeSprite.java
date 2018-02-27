package problem.sprites;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

abstract public class CompositeSprite extends AbstractSprite {
	public CompositeSprite(double x, double y, double width, double height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	abstract public void add(AbstractSprite s);

	abstract public AbstractSprite getSprite(int index);

	@Override
	abstract public void move(Dimension space);

	@Override
	abstract public Iterator<ISprite> iterator();

}
