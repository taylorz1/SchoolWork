package problem.sprites;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import problem.sprites.CompositeSpriteIterator;

public class TowerSprite extends CompositeSprite {
	
	private Iterator<ISprite> iter = null;
	private ArrayList<ISprite> sprite = new ArrayList<>();
	
	public TowerSprite(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.sprite.add(new RectangleSprite(x, y, width, height));
		this.sprite.add(new RectangleSprite(x-width, y-height, width+5,height+5));
	}



	@Override
	public void add(AbstractSprite s) {
		this.add(s);
	}

	@Override
	public AbstractSprite getSprite(int index) {
		// TODO Auto-generated method stub
		return this.getSprite(index);
	}

	@Override
	public void move(Dimension space) {
		// TODO Auto-generated method stub
		for(ISprite s : sprite) {
			s.move(space);
		}
	}

	@Override
	public Iterator<ISprite> iterator() {
			iter = new CompositeSpriteIterator(sprite.iterator());
		return iter;
	}

}
