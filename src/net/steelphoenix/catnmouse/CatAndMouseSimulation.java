package net.steelphoenix.catnmouse;

import java.util.HashSet;
import java.util.Set;

/**
 * A cat and mouse simulation.
 * 
 * @author SteelPhoenix
 */
public class CatAndMouseSimulation implements ICatAndMouseSimulation {
	
	private final Tile[] tiles;
	private final int catMoves;
	private final int mouseMoves;
	private int cat; // Cat starting pos
	private int mouse; // Mouse starting pos
	private int turns = 0; // Amount of turns processed
	
	private CatAndMouseSimulation(int tiles, int catMoves, int mouseMoves, int catStart, int mouseStart, int[] rocks) {
		// Preconditions
		if (tiles < 1) {
			throw new IllegalArgumentException("Invalid tile count");
		}
		if (catMoves < 0) {
			throw new IllegalArgumentException("Invalid cat moves count");
		}
		if (mouseMoves < 0) {
			throw new IllegalArgumentException("Invalid mouse moves count");
		}
		if (catStart < 0 || catStart >= tiles) {
			throw new IllegalArgumentException("Invalid cat starting position");
		}
		if (mouseStart < 0 || mouseStart >= tiles) {
			throw new IllegalArgumentException("Invalid mouse starting position");
		}
		
		this.tiles = new Tile[tiles];
		this.catMoves = catMoves;
		this.mouseMoves = mouseMoves;
		this.cat = catStart;
		this.mouse = mouseStart;
		
		// Tiles
		for (int i = 0; i < rocks.length; i++) {
			int index = rocks[i];
			if (index < 0 || index >= tiles) {
				throw new IllegalArgumentException("Invalid rock index");
			}
			this.tiles[index] = new Tile(index, true);
		}
		for (int i = 0; i < tiles; i++) {
			if (this.tiles[i] == null) {
				this.tiles[i] = new Tile(i, false);
			}
		}
	}
	
	@Override
	public ITile getTile(int index) {
		// Preconditions
		if (index < 0 || index >= tiles.length) {
			throw new IllegalArgumentException("Illegal index: " + index);
		}
		
		return tiles[index];
	}

	@Override
	public int tiles() {
		return tiles.length;
	}

	@Override
	public int catMoves() {
		return catMoves;
	}

	@Override
	public int mouseMoves() {
		return mouseMoves;
	}
	
	@Override
	public boolean move() {
		// Already finished
		// Note that we ignore the 0 turn move because they start at the same tile
		if (cat == mouse && turns != 0) {
			throw new IllegalStateException("Simulation finished");
		}
		
		turns++;
		
		// Cat moves
		Tile tile = (Tile) getTile(cat);
		for (int i = catMoves; i > 0; i -= tile.hasRock() ? 2 : 1) {
			cat = (tile = (Tile) tile.next()).index;
		}
		
		// Mouse moves
		tile = (Tile) getTile(mouse);
		for (int i = mouseMoves; i > 0; i -= tile.hasRock() ? 2 : 1) {
			mouse = (tile = (Tile) tile.next()).index;
		}
		
		return cat == mouse;
	}
	
	@Override
	public int moves() {
		return turns;
	}
	
	// This #toString() is unconventional but makes printing the program output easier
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < tiles(); i++) {
			builder.append(getTile(i));
		}
		return builder.toString();
	}
	
	/**
	 * A tile.
	 * 
	 * @author SteelPhoenix
	 */
	private class Tile implements ITile {
		
		private final int index;
		private final boolean rock;
		
		private Tile(int index, boolean rock) {
			this.index = index;
			this.rock = rock;
		}
		
		@Override
		public ITile next() {
			return getTile((index + 1) % tiles.length);
		}
		
		@Override
		public boolean hasRock() {
			return rock;
		}

		@Override
		public boolean isCatOnTile() {
			return cat == index;
		}

		@Override
		public boolean isMouseOnTile() {
			return mouse == index;
		}
		
		
		// This #toString() is unconventional but makes printing the program output easier
		@Override
		public String toString() {
			String s = " ";
			
			if (hasRock()) {
				s = "x";
			}
			
			if (isCatOnTile()) {
				s = "c";
			}
			
			if (isMouseOnTile()) {
				s = isCatOnTile() ? "*" : "m";
			}
			
			
			return s;
		}
	}
	
	/**
	 * A simulation builder class.
	 * Note that no inputs are validated until building.
	 * 
	 * @author SteelPhoenix
	 */
	public static class Builder {
		
		private int tiles = 1;
		private int catMoves = 0;
		private int mouseMoves = 0;
		private int catStart = 0;
		private int mouseStart = 0;
		private Set<Integer> rocks = new HashSet<>();
		
		/**
		 * Set the tile count.
		 * 
		 * @param tiles Tile count.
		 * @return this for chaining.
		 */
		public Builder withTiles(int tiles) {
			this.tiles = tiles;
			return this;
		}
		
		/**
		 * Set the cat move count.
		 * 
		 * @param moves Cat move count.
		 * @return this for chaining.
		 */
		public Builder withCatMoves(int moves) {
			this.catMoves = moves;
			return this;
		}
		
		/**
		 * Set the mouse move count.
		 * 
		 * @param moves Mouse move count.
		 * @return this for chaining.
		 */
		public Builder withMouseMoves(int moves) {
			this.mouseMoves = moves;
			return this;
		}
		
		/**
		 * Set the cat starting position.
		 * 
		 * @param pos Starting position.
		 * @return this for chaining.
		 */
		public Builder withCatStartPos(int pos) {
			this.catStart = pos;
			return this;
		}
		
		/**
		 * Set the mouse starting position.
		 * 
		 * @param pos Starting position.
		 * @return this for chaining.
		 */
		public Builder withMouseStartPos(int pos) {
			this.mouseStart = pos;
			return this;
		}
		
		/**
		 * Set the tiles that have a rock.
		 * 
		 * @param indices Tile indices.
		 * @return this for chaining.
		 */
		public Builder withRockAt(int... indices) {
			// No valid indices
			if (indices == null) {
				return this;
			}
			
			for (int i : indices) {
				rocks.add(i);
			}
			return this;
		}
		
		/**
		 * Create a simulation instance.
		 * This may throw an exception for invalid parameters.
		 * 
		 * @return the created instance.
		 */
		public ICatAndMouseSimulation build() {
			// This may throw an exception
			// The builder does not check anything
			return new CatAndMouseSimulation(tiles, catMoves, mouseMoves, catStart, mouseStart, rocks.stream().mapToInt(i -> i).toArray());
		}
	}
}
