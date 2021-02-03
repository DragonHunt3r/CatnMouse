package net.steelphoenix.catnmouse;

/**
 * A tile on the board.
 * 
 * @author SteelPhoenix
 */
public interface ITile {
	
	/**
	 * Get the next tile.
	 * 
	 * @return the adjecent tile.
	 */
	public ITile next();
	
	/**
	 * Get if this tile has a rock.
	 * 
	 * @return if the tile has a rock.
	 */
	public boolean hasRock();
	
	/**
	 * Get if the cat is on this tile.
	 * 
	 * @return if the cat is on this tile. 
	 */
	public boolean isCatOnTile();
	
	/**
	 * Get if the mouse is on this tile.
	 * 
	 * @return if the mouse is on this tile.
	 */
	public boolean isMouseOnTile();
}
