package net.steelphoenix.catnmouse;

/**
 * Cat and mouse simulation.
 * 
 * @author SteelPhoenix
 */
public class Main {
	
	/**
	 * Application entry point.
	 * 
	 * @param args Command line parameters.
	 */
	public static void main(String[] args) {
		
		int n; // Number of tiles on the track
		int c; // Moves the cat can make in one turn
		int m; // Moves the mouse can make in one turn
		int[] rockIndices; // Indices of tiles with a rock (these start at 1 instead of 0)
		
		// TODO: Input reading
		n = 1;
		c = 0;
		m = 0;
		rockIndices = new int[0];
		
		// Sanitize input to make it 0-indexed
		for (int i = 0; i < rockIndices.length; i++) {
			rockIndices[i]--;
		}
		
		// Simulation
		ICatAndMouseSimulation sim = new CatAndMouseSimulation.Builder()
				.withTiles(n)
				.withCatMoves(c)
				.withMouseMoves(m)
				.withRockAt(rockIndices)
				.withCatStartPos(0)
				.withMouseStartPos(0)
				.build();
		
		// Run the simulation
		// Note that we do not test for infinite loops
		// We assume the setup is solvable
		
		// Print tiles
		do {
			System.out.println(sim);
		} while (!sim.move());
		
		// Print final state
		System.out.println(sim);
		
		// Print move count
		System.out.println(sim.moves());
	}
}
