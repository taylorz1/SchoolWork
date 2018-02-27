package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mutate.DiamondDoubleMutate;
import mutate.ExpanderTileMutator;
import mutate.IMutate;
import render.ASCIIRenderer;
import render.IRenderer;
import tiles.ITile;
import tiles.DiamondTile;
import tiles.DirtTile;
import tiles.EmptyTile;

/**
 * 
 * @author wickersl
 *
 */
public class MapGeneratorApp {

	public static void main(String[] args) {

		List<ITile> tileSet = buildTileSet();
		
		Map<String, IMutate> map = new HashMap<>();
		map.put("DiamondTile", new DiamondDoubleMutate());
		map.put("default", new ExpanderTileMutator());
		MapGenerator mapGen = new MapGenerator(tileSet, 4, 10, 5, map);

		mapGen.renderMap();
	}

	/**
	 * main() uses dependency injection to pass in this list of possible tiles
	 * to MapGenerator. This approach is good design.
	 * 
	 * @return
	 */
	private static List<ITile> buildTileSet() {
		int diamondYield = 1;
		IRenderer diamondRender = new ASCIIRenderer(String.valueOf(diamondYield));
		IRenderer emptyTileRender = new ASCIIRenderer(" ");
		IRenderer dirtTileRender = new ASCIIRenderer("#");
		ITile diamondTile = new DiamondTile(diamondYield);
		diamondTile.setRenderer(diamondRender);

		ITile dirtTile = new DirtTile();
		ITile emptyTile = new EmptyTile();
		dirtTile.setRenderer(dirtTileRender);
		emptyTile.setRenderer(emptyTileRender);

		List<ITile> tileSet = new ArrayList<ITile>();
		tileSet.add(diamondTile);
		tileSet.add(dirtTile);
		tileSet.add(emptyTile);

		return tileSet;
	}

}
