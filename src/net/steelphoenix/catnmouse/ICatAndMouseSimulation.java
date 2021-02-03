package net.steelphoenix.catnmouse;

/**
 * A cat and mouse simulation.
 * 
 * @author SteelPhoenix
 */
public interface ICatAndMouseSimulation {
	
	/**
	 * Get the tile at a given index.
	 * 
	 * @param index Target index.
	 * @return the tile at the index.
	 */
	public ITile getTile(int index);
	
	/**
	 * Get the number of tiles in this simulation.
	 * 
	 * @return the tile count.
	 */
	public int tiles();
	
	/**
	 * Get the number of moves a cat can make in one turn.
	 * 
	 * @return the cat move count.
	 */
	public int catMoves();
	
	/**
	 * Get the number of moves a mouse can make in one turn.
	 * 
	 * @return the mouse move count.
	 */
	public int mouseMoves();
	
	/**
	 * Process a turn.
	 * Note that once the simulation has ended, no more turns are processed.
	 * 
	 * @return if at the end of the turn the cat and mouse are on the same tile.
	 */
	public boolean move();
	
	/**
	 * Get the number of turns processed.
	 * 
	 * @return the turn count.
	 */
	public int moves();
}
