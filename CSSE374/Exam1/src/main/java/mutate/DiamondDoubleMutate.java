package mutate;

import tiles.ITile;
import render.ASCIIRenderer;
import tiles.DiamondTile;

public class DiamondDoubleMutate implements IMutate {

	@Override
	public void mutate(ITile tile, ITile[][] map) {
		int t = ((DiamondTile) tile).getYield();
		((DiamondTile) tile).setYield(t*2);
		tile.setRenderer(new ASCIIRenderer(String.valueOf(t*2)));
	}

}
