package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import mutate.IMutate;
import tiles.ITile;
import tiles.DiamondTile;

public class MapGenerator {
	private List<ITile> tileSet;
	private int columns;
	private int rows;
	private int nMutations;
	private Random randomNumbers;
	private ITile[][] mapRep;
	private Map<String, IMutate> mutators;

	public MapGenerator(List<ITile> tileSet, int numRows, int numCols, int nMutations, Map<String, IMutate> mutators) {
		this.tileSet = tileSet;
		this.columns = numCols;
		this.rows = numRows;
		this.nMutations = nMutations;
		this.randomNumbers = new Random();
		this.mutators = mutators;
		this.mapRep = generateMapRepresentation();
	}

	private ITile[][] generateMapRepresentation() {
		ITile[][] mapRep = new ITile[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				mapRep[i][j] = generateRandomTile();
			}
		}

		mutateMap(mapRep);

		return mapRep;
	}

	// DONE: implement Feature 1
	public void renderMap() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				mapRep[i][j].renderTile();
			}
			System.out.println();
		}

	}

	// DONE: implement Feature 2
	private void mutateMap(ITile[][] mapRep) {
		HashSet<ITile> tilesMarkedForMutation = selectTilesForMutation(mapRep);
		for (ITile tile : tilesMarkedForMutation) {
			/*
			 * Can make distribution anything you want with tinkering,
			 * the reasoning for the random value is due to the collison in definition
			 * of the mutators. You could make the hashmap String,List but then you run
			 * into the same problem by just selecting a random element from the list.
			 * This is more direct, albeit looks much dumber.
			 */
			// Doubler works fine. I'm not sure if expander works, it should do something.
			// I need to somehow get to coords for it to work, currently it always copies 0,0
			// Probably because there is no well defined equals method.
			if (Math.random() > 0.5) {
				if (this.mutators.containsKey(tile.toString())) {
					IMutate mutate = this.mutators.get(tile.toString());
					mutate.mutate(tile, mapRep);
				}
			} else {
				IMutate mutate = this.mutators.get("default");
				mutate.mutate(tile, mapRep);
				
			}
		}
	}

	private ITile generateRandomTile() {
		int poolSize = tileSet.size();
		int randomNum = randomNumbers.nextInt(poolSize);
		ITile tile = tileSet.get(randomNum);
		// System.out.println("Made new tile: " + tile.getDescription());
		return tile.createNewTile();
	}

	public void addMutator(String key, IMutate val) {

	}

	private HashSet<ITile> selectTilesForMutation(ITile[][] mapRep) {
		HashSet<ITile> tilesMarkedForMutation = new HashSet<>();
		while (tilesMarkedForMutation.size() < nMutations) {
			int randomCol = randomNumbers.nextInt(this.columns);
			int randomRow = randomNumbers.nextInt(this.rows);
			ITile toMutate = mapRep[randomRow][randomCol];
			tilesMarkedForMutation.add(toMutate);
		}

		return tilesMarkedForMutation;
	}

}
