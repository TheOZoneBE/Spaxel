package code.engine;

import java.awt.*;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import code.Game;
import code.collision.HitShape;
import code.entity.SpaceCarrier;
import code.graphics.Spritesheet;
import code.sound.Music;
import code.sound.Sound;
import code.system.RenderSystem;
import code.graphics.SpriteData;
import code.input.Keyboard;
import code.input.MouseWrapper;
import code.inventory.*;
import code.system.*;
import code.loaders.*;
import code.ui.UI;
import code.ui.UIButton;
import code.ui.UICounter;


final public class Engine {
	private final static Engine engine = new Engine();
	private Keyboard keys;
	private MouseWrapper mouseWrapper;
	private EntityStream entities;
	private List<Music> musicList;
	public Map<String, Spritesheet> spritesheets;
	public Map<String, SpriteData> spriteAtlas;
	private Map<String, HitShape> hitShapeAtlas;
	private Map<String, UI> UIAtlas;
	private EnumMap<SystemType, GameSystem> systems;
	private GameState gameState;
	private ItemCatalogue items;
	private boolean loading = true;
	private float updateTime;
	public SpaceCarrier temp;
	private Font font;
	private long window;

	public static Engine getEngine(){
		return engine;
	}

	public enum GameState {
		MENU, PLAY, PAUSE
	}
	
	private Engine(){

		entities = new EntityStream();
		systems = new EnumMap<>(SystemType.class);
		gameState = GameState.MENU;
	}
	
	public void initialize(){
        SpriteLoader spriteLoader = new SpriteLoader();
        spriteAtlas = spriteLoader.loadSprites("/resources/spritesheet.xml", "/resources/sprite.xml");
        spritesheets = spriteLoader.spritesheetMap;

        spriteAtlas.put("hp_bar", new SpriteData(1,4, 0xff00ff00));
        spriteAtlas.put("xp_bar", new SpriteData(1,4, 0xff0000ff));

        addSystem(new RenderSystem());

        Game.game.updater.setSystems(systems);
	}

	public void startLoading(){
        this.keys = new Keyboard(window);

        //asset loading
		/*
		Game.game.loadingScreen.getMessage().setText("Loading sounds");
		Game.game.loadingScreen.getProgress().setPercent(0.05f);*/
		SoundLoader sounds = new SoundLoader();
		musicList = sounds.loadSounds("/resources/sound.json");

        Game.game.loadingScreen.getMessage().setText("Loading hitshapes");
        Game.game.loadingScreen.getProgress().setPercent(0.4f);
        hitShapeAtlas = new HitShapeLoader().loadHitShapes("/resources/hitshape.json");

        Game.game.loadingScreen.getMessage().setText("Loading items");
        Game.game.loadingScreen.getProgress().setPercent(0.65f);
        items = new ItemLoader().loadItems("/resources/item.xml", spriteAtlas);

        Game.game.loadingScreen.getMessage().setText("Loading UI");
        Game.game.loadingScreen.getProgress().setPercent(0.8f);
        UIAtlas = new UIElementLoader().loadUIElements("/resources/uielement.xml", this);

        ((UIButton)UIAtlas.get("main").getElement("ach_button")).setDisabled(true);
        ((UIButton)UIAtlas.get("main").getElement("opt_button")).setDisabled(true);

        entities.cleanup();

        Game.game.loadingScreen.getMessage().setText("Initializing systems");
        Game.game.loadingScreen.getProgress().setPercent(0.9f);
        //systems
        addSystem(new SoundSystem());
        addSystem(new InventorySystem());
        addSystem(new UISystem());
        addSystem(new ProjectileSystem());
        addSystem(new ActorSystem());
        addSystem(new AISystem());
        addSystem(new ParticleSystem());
        addSystem(new TrailSystem());
        addSystem(new SpawnerSystem());
        ((SoundSystem)getSystem(SystemType.SOUND)).nextSong();
        ((UISystem)getSystem(SystemType.UI)).changeUI("main");
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
		((UICounter)UIAtlas.get("play").getElement("score_counter")).setCounter(0);
		entities.clear();
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
	
	public EntityStream getEntityStream(){
		return entities;
	}
	
	public Map<String, SpriteData> getSpriteAtlas(){
		return spriteAtlas;
	}
	
	public Map<String, HitShape> getHitShapeAtlas(){
		return hitShapeAtlas;
	}
	
	public Map<String, UI> getUIAtlas(){
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

}
