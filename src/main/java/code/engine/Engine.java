package code.engine;

import java.awt.Font;
import java.util.EnumMap;
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
import code.system.AISystem;
import code.system.AgeSystem;
import code.system.CooldownSystem;
import code.system.DamageSystem;
import code.system.DifficultySystem;
import code.system.EquipSystem;
import code.system.ExperienceSystem;
import code.system.GameSystem;
import code.system.HealthSystem;
import code.system.HitSystem;
import code.system.InputSystem;
import code.system.MarkerSystem;
import code.system.RenderSystem;
import code.system.ShipSystem;
import code.system.SoundSystem;
import code.system.SpawnerSystem;
import code.system.UISystem;
import code.system.VelocitySystem;
import code.ui.Controller;
import code.ui.UI;

final public class Engine {
	private final static Engine engine = new Engine();
	private Keyboard keys;
	private MouseWrapper mouseWrapper;
	private NEntityStream nentities;
	private MusicList musicList;
	private Map<String, EntityIndustry> industryMap;
	private Map<String, HitShape> hitShapeAtlas;
	private EnumMap<UI, Controller> UIAtlas;
	private Controller controller;
	private EnumMap<SystemType, GameSystem> systems;
	private GameState gameState;
	private ItemCatalogue items;
	private boolean loading = true;
	private double updateTime;
	private Font font;
	private long window;
	private VectorD screenOffset;
	private VectorD cursorFollow;
	private GameProperties gameProperties;
	private Logger logger;
	private Map<String, Spritesheet> spritesheets;
	private Map<String, SpriteData> spriteAtlas;
	private LoadingScreen loadingScreen;
	private SystemUpdater updater;

	public static Engine getEngine() {
		return engine;
	}

	public enum GameState {
		MENU, PLAY, PAUSE
	}

	private Engine() {
		gameProperties = new GameProperties();
		nentities = new NEntityStream();
		systems = new EnumMap<>(SystemType.class);
		gameState = GameState.MENU;
		loadingScreen = new LoadingScreen();
		updater = new SystemUpdater();
	}

	public void initialize() {
		SpritesheetLoader spritesheetLoader = new SpritesheetLoader();
		spritesheets = spritesheetLoader.loadSpritesheets("/resources/spritesheet.json");
		SpriteDataLoader spriteDataLoader = new SpriteDataLoader();
		spriteAtlas = spriteDataLoader
				.loadSpriteDatas(new String[] { "/resources/sprite.json", "/resources/font.json" });

		spriteAtlas.put("hp_bar", new SpriteData(1, 4, 0xff00ff00));
		spriteAtlas.put("xp_bar", new SpriteData(1, 4, 0xff0000ff));
		spriteAtlas.put("dot", new SpriteData(2, 2, 0xffffffff));

		addSystem(new RenderSystem());

		updater.setSystems(systems);
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
		loadingScreen.getProgress().setPercent(0.9);

		nentities.cleanup();

		loadingScreen.getMessage().setText("Initializing systems");

		// systems
		addSystem(new SoundSystem());
		addSystem(new UISystem());
		addSystem(new AISystem());
		addSystem(new SpawnerSystem());
		addSystem(new AgeSystem());
		addSystem(new VelocitySystem());
		addSystem(new DamageSystem());
		addSystem(new HealthSystem());
		addSystem(new CooldownSystem());
		addSystem(new HitSystem());
		addSystem(new InputSystem());
		addSystem(new EquipSystem());
		addSystem(new ExperienceSystem());
		addSystem(new ShipSystem());
		addSystem(new DifficultySystem());
		addSystem(new MarkerSystem());
		((SoundSystem) getSystem(SystemType.SOUND)).nextSong();
		controller = UIAtlas.get(UI.MAIN);
		// starting threads
		updater.setSystems(systems);
		loadingScreen.getProgress().setPercent(.95);
		loading = false;
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

	public void addSystem(GameSystem system) {
		systems.put(system.getType(), system);
	}

	public GameSystem getSystem(SystemType type) {
		return systems.get(type);
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

	public void setFont(Font font) {
		this.font = font;
	}

	public Font getFont() {
		return font;
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

	public SystemUpdater getUpdater() {
		return updater;
	}

	public LoadingScreen getLoadingScreen() {
		return loadingScreen;
	}
}
