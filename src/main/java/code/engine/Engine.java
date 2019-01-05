package code.engine;

import code.Game;
import code.input.Keyboard;
import code.input.MouseWrapper;
import code.logger.Logger;
import code.ui.elements.UIType;
import code.ui.elements.UI;

public final class Engine {
	private static final Engine engine = new Engine();

	private Keyboard keys;
	private MouseWrapper mouseWrapper;

	private NEntityStream nentities;

	private UI currentUI;
	private EngineState engineState;

	private long window;

	private GameState gameState;

	private Logger logger;

	private Engine() {
		gameState = new GameState();
		nentities = new NEntityStream();
		engineState = EngineState.LOAD;
	}

	public static Engine get() {
		return engine;
	}

	public enum EngineState {
		MENU, PLAY, PAUSE, LOAD
	}

	public void finishLoading() {
		this.keys = new Keyboard(window, Resources.get().getKeyConfiguration());

		nentities.cleanup();

		currentUI = Resources.get().getUIS().get(UIType.MAIN);
		engineState = EngineState.MENU;

		Game.startUpdating();
	}

	public void stopGame() {
		nentities.scheduleClear();
		gameState = new GameState();
		logger = null;
		// TODO rework musiclist so the data and the resetting is seperated
		Resources.get().getMusicList().reset();
	}

	public void setWindow(long window) {
		this.window = window;
	}

	public Keyboard getKeyboard() {
		return keys;
	}

	public MouseWrapper getMouseWrapper() {
		return mouseWrapper;
	}

	public void setMouseWrapper(MouseWrapper wrapper) {
		this.mouseWrapper = wrapper;
	}

	public NEntityStream getNEntityStream() {
		return nentities;
	}

	public EngineState getEngineState() {
		return engineState;
	}

	public void setEngineState(EngineState gs) {
		engineState = gs;
	}

	public GameState getGameState() {
		return gameState;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public UI getCurrentUI() {
		return currentUI;
	}

	public void setCurrentUI(UI currentUI) {
		currentUI.reset();
		this.currentUI = currentUI;
	}


}
