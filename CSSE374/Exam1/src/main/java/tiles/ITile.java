package tiles;

import render.IRenderer;

public interface ITile {
	

	ITile createNewTile();

	public void renderTile();
	
	public void setRenderer(IRenderer renderer);
	
	public String toString();

}
