package code.engine;

import code.Constants;
import code.Game;
import code.input.Keyboard;
import code.input.MouseWrapper;
import code.logger.Logger;
import code.math.VectorD;
import code.ui.elements.UIType;
import code.ui.elements.UI;

public final class Engine {
	private static final Engine engine = new Engine();

	private Keyboard keys;
	private MouseWrapper mouseWrapper;

	private NEntityStream nentities;

	private UI currentUI;
	private GameState gameState;

	private boolean loading = true;
	private double updateTime;
	private long window;
	private VectorD screenOffset;
	private VectorD cursorFollow;
	private GameProperties gameProperties;
	private Logger logger;

	private Engine() {
		gameProperties = new GameProperties();
		nentities = new NEntityStream();
		gameState = GameState.LOAD;
	}

	public static Engine get() {
		return engine;
	}

	public enum GameState {
		MENU, PLAY, PAUSE, LOAD
	}

	public void finishLoading() {
		this.keys = new Keyboard(window);
		this.cursorFollow = new VectorD(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2);

		nentities.cleanup();

		currentUI = Resources.get().getUIS().get(UIType.MAIN);
		loading = false;
		gameState = GameState.MENU;

		Game.startUpdating();
	}

	public void stopGame() {
		nentities.scheduleClear();
		gameProperties = new GameProperties();
		logger = null;
		// TODO rework musiclist so the data and the resetting is seperated
		Resources.get().getMusicList().reset();
		this.cursorFollow = new VectorD(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2);
	}

	public void setWindow(long window) {
		this.window = window;
	}

	public boolean isLoading() {
		return loading;
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

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gs) {
		gameState = gs;
	}

	public double getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(double updateTime) {
		this.updateTime = updateTime;
	}


	public VectorD getScreenOffset() {
		return screenOffset;
	}

	public void setScreenOffset(VectorD screenOffset) {
		this.screenOffset = screenOffset;
	}

	public GameProperties getGameProperties() {
		return gameProperties;
	}

	public void setGameProperties(GameProperties gameProperties) {
		this.gameProperties = gameProperties;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public VectorD getCursorFollow() {
		return cursorFollow;
	}

	public void setCursorFollow(VectorD cursorFollow) {
		this.cursorFollow = cursorFollow;
	}

	public UI getCurrentUI() {
		return currentUI;
	}

	public void setCurrentUI(UI currentUI) {
		currentUI.reset();
		this.currentUI = currentUI;
	}


}
