package code;

public final class Game {
	public static final DisplayRunner displayRunner = new DisplayRunner();
	public static final UpdateRunner updateRunner = new UpdateRunner();

	private Game() {
	}

	public static void main(String[] args) {
		Thread display = new Thread(displayRunner, "Display");

		display.start();
	}

	public static void startUpdating() {
		Thread update = new Thread(updateRunner, "Update");

		update.start();
	}

	public static void exit() {
		displayRunner.exit();
		updateRunner.exit();
	}
}
