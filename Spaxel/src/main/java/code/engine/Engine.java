package code.engine;

import java.awt.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import code.Game;
import code.collision.HitShape;
import code.factories.entities.EntityIndustry;
import code.graphics.Spritesheet;
import code.logger.Logger;
import code.math.VectorF;
import code.sound.Music;
import code.system.RenderSystem;
import code.graphics.SpriteData;
import code.input.Keyboard;
import code.input.MouseWrapper;
import code.system.*;
import code.loaders.*;
import code.ui.Controller;
import code.ui.UI;


final public class Engine {
	private final static Engine engine = new Engine();
	private Keyboard keys;
	private MouseWrapper mouseWrapper;
	private NEntityStream nentities;
	private List<Music> musicList;
	private Map<String, EntityIndustry> industryMap;
	public Map<String, Spritesheet> spritesheets;
	public Map<String, SpriteData> spriteAtlas;
	private Map<String, HitShape> hitShapeAtlas;
	private EnumMap<UI, Controller> UIAtlas;
	private Controller controller;
	private EnumMap<SystemType, GameSystem> systems;
	private GameState gameState;
	private ItemCatalogue items;
	private boolean loading = true;
	private float updateTime;
	private Font font;
	private long window;
	private VectorF screenOffset;
	private GameProperties gameProperties;
	private Logger logger;

	public static Engine getEngine(){
		return engine;
	}

	public enum GameState {
		MENU, PLAY, PAUSE
	}
	
	private Engine(){
		gameProperties = new GameProperties();
		nentities = new NEntityStream();
		systems = new EnumMap<>(SystemType.class);
		gameState = GameState.MENU;
	}
	
	public void initialize(){
		SpritesheetLoader spritesheetLoader = new SpritesheetLoader();
		spritesheets = spritesheetLoader.loadSpritesheets("/resources/spritesheet.json");
		SpriteDataLoader spriteDataLoader = new SpriteDataLoader();
        spriteAtlas = spriteDataLoader.loadSpriteDatas( "/resources/sprite.json");


        spriteAtlas.put("hp_bar", new SpriteData(1,4, 0xff00ff00));
        spriteAtlas.put("xp_bar", new SpriteData(1,4, 0xff0000ff));
		spriteAtlas.put("dot", new SpriteData(2,2, 0xffffffff));

        addSystem(new RenderSystem());

        Game.game.updater.setSystems(systems);
	}

	public void startLoading(){
        this.keys = new Keyboard(window);

        //asset loading
		/*
		Game.game.loadingScreen.getMessage().setText("Loading sounds");
		Game.game.loadingScreen.getProgress().setPercent(0.05f);
		SoundLoader sounds = new SoundLoader();
		musicList = sounds.loadSounds("/resources/sound.json");*/
		//TODO fix loading screen
        //Game.game.loadingScreen.getMessage().setText("Loading hitshapes");
        //Game.game.loadingScreen.getProgress().setPercent(0.4f);
        hitShapeAtlas = new HitShapeLoader().loadHitShapes("/resources/hitshape.json");

        //Game.game.loadingScreen.getMessage().setText("Loading items");
        //Game.game.loadingScreen.getProgress().setPercent(0.65f);
        items = new ItemPropertiesLoader().loadItems("/resources/itemProperties.json");

        //Game.game.loadingScreen.getMessage().setText("Loading UI");
        //Game.game.loadingScreen.getProgress().setPercent(0.8f);
        UIAtlas = new UIElementLoader().loadUIElements(new String[]{
        		"/ui/main.xml",
				"/ui/credits.xml",
				"/ui/class_selection.xml",
				"/ui/play.xml",
				"/ui/pause.xml",
				"/ui/game_over.xml"
		});

        industryMap = new IndustryLoader().loadEntityIndustries(new String[]{
        		"/resources/entity.json",
				"/resources/actor.json",
				"/resources/projectile.json",
				"/resources/item.json",
				"/resources/player.json",
				"/resources/effect.json"
        });


        nentities.cleanup();

        //Game.game.loadingScreen.getMessage().setText("Initializing systems");
        //Game.game.loadingScreen.getProgress().setPercent(0.9f);
        //systems
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
        //((SoundSystem)getSystem(SystemType.SOUND)).nextSong();
		controller = UIAtlas.get(UI.MAIN);
        //starting threads
        Game.game.updater.setSystems(systems);

		/*
		Spritesheet spaceCar = new Spritesheet(64,64, "/spritesheets/space_carrier.png");
		Sprite hull = new Sprite(39,57, 0,0,4,spaceCar);
		Sprite turret =new Sprite(4, 8, 11, 0, 4, spaceCar);
		Sprite door1 = new Sprite(16,16, 3,0,4, spaceCar);
		Sprite door2 =new Sprite(16,16, 3,1,4, spaceCar);
		Sprite door3 = new Sprite(16,16, 3,2,4, spaceCar);
		Sprite door4 = new Sprite(16,16, 3,3,4, spaceCar);
		Sprite[] anim = {door1, door2, door3, door4, door4, door3, door2, door1};
		AnimSprite as = new AnimSprite(8, 10, anim);
		temp = new SpaceCarrier(0,0,0,1000,hull, turret, as, 1, 0.5);
		*/

        loading = false;
    }

	public void stopGame(){
		nentities.clear();
		gameProperties = new GameProperties();
		logger = null;
	}

	public void setWindow(long window){
		this.window = window;
	}

	public boolean isLoading(){
		return loading;
	}
	
	public Keyboard getKeyboard(){
		return keys;
	}
	
	public MouseWrapper getMouseWrapper(){
		return mouseWrapper;
	}

	public void setMouseWrapper(MouseWrapper wrapper){
		this.mouseWrapper = wrapper;
	}

	public NEntityStream getNEntityStream() {
		return nentities;
	}
	
	public Map<String, SpriteData> getSpriteAtlas(){
		return spriteAtlas;
	}
	
	public Map<String, HitShape> getHitShapeAtlas(){
		return hitShapeAtlas;
	}
	
	public EnumMap<UI, Controller> getUIAtlas(){
		return UIAtlas;
	}

	public List<Music> getMusicList(){
		return musicList;
	}

	public ItemCatalogue getItems(){
		return items;
	}
	
	public void addSystem(GameSystem system){
		systems.put(system.getType(), system);
	}
	
	public GameSystem getSystem(SystemType type){
		return systems.get(type);
	}
	
	public GameState getGameState(){
		return gameState;
	}
	
	public void setGameState(GameState gs){
		gameState = gs;
	}

	public float getUpdateTime(){
		return updateTime;
	}

	public void setUpdateTime(float updateTime){
		this.updateTime = updateTime;
	}

	public void setFont(Font font){
		this.font = font;
	}

	public Font getFont(){
		return font;
	}

	public Map<String, Spritesheet> getSpritesheets(){
		return spritesheets;
	}

	public Map<String, EntityIndustry> getIndustryMap() {
		return industryMap;
	}

	public VectorF getScreenOffset() {
		return screenOffset;
	}

	public void setScreenOffset(VectorF screenOffset) {
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
}
