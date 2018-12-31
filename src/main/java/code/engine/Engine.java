package code.engine;

import java.util.Map;

import code.Constants;
import code.Game;
import code.collision.HitShape;
import code.factories.entities.EntityIndustry;
import code.graphics.SpriteData;
import code.graphics.Spritesheet;
import code.input.Keyboard;
import code.input.MouseWrapper;
import code.loaders.HitShapeLoader;
import code.loaders.IndustryLoader;
import code.loaders.ItemPropertiesLoader;
import code.loaders.SoundLoader;
import code.loaders.SpriteDataLoader;
import code.loaders.SpritesheetLoader;
import code.loaders.UIElementLoader;
import code.logger.Logger;
import code.math.VectorD;
import code.ui.controllers.Controller;
import code.ui.elements.UI;

public final class Engine {
	private static final Engine engine = new Engine();

	private Keyboard keys;
	private MouseWrapper mouseWrapper;

	private NEntityStream nentities;

	private MusicList musicList;
	private Map<String, EntityIndustry> industryMap;
	private Map<String, HitShape> hitShapeAtlas;
	private Map<UI, Controller> UIAtlas;
	private Map<String, Spritesheet> spritesheets;
	private Map<String, SpriteData> spriteAtlas;
	private ItemCatalogue items;

	private Controller controller;
	private GameState gameState;

	private boolean loading = true;
	private double updateTime;
	private long window;
	private VectorD screenOffset;
	private VectorD cursorFollow;
	private GameProperties gameProperties;
	private Logger logger;

	private LoadingScreen loadingScreen;

	private Engine() {
		gameProperties = new GameProperties();
		nentities = new NEntityStream();
		gameState = GameState.LOAD;
	}

	public static Engine getEngine() {
		return engine;
	}

	public enum GameState {
		MENU, PLAY, PAUSE, LOAD
	}

	public void initialize() {
		SpritesheetLoader spritesheetLoader = new SpritesheetLoader();
		spritesheets = spritesheetLoader.loadSpritesheets("/resources/spritesheet.json");
		SpriteDataLoader spriteDataLoader = new SpriteDataLoader();
		spriteAtlas = spriteDataLoader
				.loadSpriteDatas(new String[] { "/resources/sprite.json", "/resources/font.json" });
		loadingScreen = new LoadingScreen();
	}

	public void startLoading() {
		this.keys = new Keyboard(window);
		this.cursorFollow = new VectorD(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2);

		// asset loading
		loadingScreen.getMessage().setText("Loading sounds");
		loadingScreen.getProgress().setPercent(0.0);
		SoundLoader sounds = new SoundLoader();
		musicList = new MusicList(sounds.loadSounds("/resources/sound.json"));

		loadingScreen.getMessage().setText("Loading hitshapes");
		hitShapeAtlas = new HitShapeLoader().loadHitShapes("/resources/hitshape.json");
		loadingScreen.getProgress().setPercent(0.75);

		loadingScreen.getMessage().setText("Loading items");
		items = new ItemPropertiesLoader().loadItems("/resources/itemProperties.json");
		loadingScreen.getProgress().setPercent(0.8);

		loadingScreen.getMessage().setText("Loading UI");
		UIAtlas = new UIElementLoader().loadUIElements(new String[] { "/ui/main.xml", "/ui/credits.xml",
				"/ui/class_selection.xml", "/ui/play.xml", "/ui/pause.xml", "/ui/game_over.xml", "/ui/options.xml" });
		loadingScreen.getProgress().setPercent(0.85);

		loadingScreen.getMessage().setText("Loading entities");
		industryMap = new IndustryLoader().loadEntityIndustries(new String[] { "/resources/entity.json",
				"/resources/actor.json", "/resources/projectile.json", "/resources/item.json", "/resources/player.json",
				"/resources/effect.json", "/resources/marker.json" });
		loadingScreen.getProgress().setPercent(0.95);

		nentities.cleanup();

		controller = UIAtlas.get(UI.MAIN);

		loading = false;
		gameState = GameState.MENU;

		Game.startUpdating();
	}

	public void stopGame() {
		nentities.scheduleClear();
		gameProperties = new GameProperties();
		logger = null;
		musicList.reset();
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

	public Map<String, SpriteData> getSpriteAtlas() {
		return spriteAtlas;
	}

	public Map<String, HitShape> getHitShapeAtlas() {
		return hitShapeAtlas;
	}

	public Map<UI, Controller> getUIAtlas() {
		return UIAtlas;
	}

	public MusicList getMusicList() {
		return musicList;
	}

	public ItemCatalogue getItems() {
		return items;
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

	public Map<String, Spritesheet> getSpritesheets() {
		return spritesheets;
	}

	public Map<String, EntityIndustry> getIndustryMap() {
		return industryMap;
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

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
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

	public LoadingScreen getLoadingScreen() {
		return loadingScreen;
	}
}
