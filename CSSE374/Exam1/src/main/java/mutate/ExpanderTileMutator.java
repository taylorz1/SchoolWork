package mutate;

import tiles.ITile;

public class ExpanderTileMutator implements IMutate {

	@Override
	public void mutate(ITile tile, ITile[][] map) {
		int i = 0;
		int j = 0;
		//For lack of a better implementation given time.
		for(int x = 0; x < map.length; x++) {
			for(int y = 0; y< map[i].length; y++) {
				if (map[i][j].equals(tile)) {
					i = x;
					j = y;
				}
			}
		}
		// We found its place.
		boolean i0 = i == 0;
		boolean j0 = j == 0;
		boolean il = i == map.length;
		boolean jl = j == map[0].length;
		
		if(i0) {
			if(j0) {
				map[i+1][j] = tile.createNewTile();
				map[i+1][j+1] = tile.createNewTile();
				map[i][j+1] = tile.createNewTile();
			} else if (jl) {
				map[i+1][j] = tile.createNewTile();
				map[i+1][j-1] = tile.createNewTile();
				map[i][j-1] = tile.createNewTile();
			} else {
				map[i][j-1] = tile.createNewTile();
				map[i][j+1] = tile.createNewTile();
				map[i+1][j] = tile.createNewTile();
				map[i+1][j-1] = tile.createNewTile();
				map[i+1][j+1] = tile.createNewTile();
			}
		} else if (il) {
			if(j0) {
				map[i-1][j] = tile.createNewTile();
				map[i-1][j+1] = tile.createNewTile();
				map[i][j+1] = tile.createNewTile();
			} else if (jl) {
				map[i-1][j] = tile.createNewTile();
				map[i-1][j-1] = tile.createNewTile();
				map[i][j-1] = tile.createNewTile();
			} else {
				map[i][j-1] = tile.createNewTile();
				map[i][j+1] = tile.createNewTile();
				map[i-1][j] = tile.createNewTile();
				map[i-1][j-1] = tile.createNewTile();
				map[i-1][j+1] = tile.createNewTile();
			}
		} else {
			map[i-1][j] = tile.createNewTile();
			map[i-1][j-1] = tile.createNewTile();
			map[i-1][j+1] = tile.createNewTile();
			map[i][j-1] = tile.createNewTile();
			map[i][j+1] = tile.createNewTile();
			map[i+1][j] = tile.createNewTile();
			map[i+1][j-1] = tile.createNewTile();
			map[i+1][j+1] = tile.createNewTile();
		}
	}
}
