package tiles;

import render.IRenderer;

public class DiamondTile implements ITile {

	private IRenderer renderer;

	private int yield;

	public DiamondTile(int diamondYield) {
		this.yield = diamondYield;
	}
	
	public int getYield() {
		return this.yield;
	}
	
	public void setYield(int yield) {
		this.yield = yield;
	}

	public ITile createNewTile() {
		ITile tile = new DiamondTile(this.yield);
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
		return "DiamondTile";
	}

}
