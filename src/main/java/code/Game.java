package code;

import code.engine.Resources;
import code.engine.Engine;

/**
 * Main class of the game, manages the different threads
 */
public final class Game {
	public static final DisplayRunner displayRunner = new DisplayRunner();
	public static final UpdateRunner updateRunner = new UpdateRunner();

	private Game() {
	}

	/**
	 * Entry point of the program, starts up the display thread.
	 * 
	 * @param args program arguments
	 */
	public static void main(String[] args) {
		Thread display = new Thread(displayRunner, "Display");

		display.start();
	}

	/**
	 * Starts up the update thread
	 */
	public static void startUpdating() {
		Thread update = new Thread(updateRunner, "Update");

		update.start();
	}

	/**
	 * Exits the game by exiting all running threads.
	 */
	public static void exit() {
		displayRunner.exit();
		updateRunner.exit();
	}
}
