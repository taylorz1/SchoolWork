package tiles;

import render.IRenderer;

public class DirtTile implements ITile {
	
	private IRenderer renderer;

	public DirtTile() {
	}

	public ITile createNewTile() {
		ITile tile = new DirtTile();
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
	public String toString() {
		return "DirtTile";
	}
	
}
