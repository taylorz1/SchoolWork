package tiles;

import render.IRenderer;

public class EmptyTile implements ITile {
	
	private IRenderer renderer;
	
	public EmptyTile() {
	}

	public ITile createNewTile() {
		ITile tile = new EmptyTile();
		tile.setRenderer(this.renderer);
		return tile;
	}

	@Override
	public void renderTile() {
		this.renderer.render();
	}

	@Override
	public void setRenderer(IRenderer renderer) {
		this.renderer = renderer;
	}
	
	@Override
	public String toString(){
		return "EmptyTile";
	}

}
