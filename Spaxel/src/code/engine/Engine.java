package code.engine;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import code.Game;
import code.collision.HitShape;
import code.entity.Player;
import code.entity.SpaceCarrier;
import code.graphics.AnimSprite;
import code.graphics.Spritesheet;
import code.sound.Sound;
import code.system.RenderSystem;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.input.Mouse;
import code.inventory.*;
import code.system.*;
import code.resource.*;
import code.ui.UI;
import code.ui.UIButton;
import code.ui.UICounter;

final public class Engine {
	private final static Engine engine = new Engine();
	private Keyboard keys;
	private Mouse mouse;
	private EntityStream entities;
	private List<Sound> soundList;
	private Map<String, Sprite> spriteAtlas;
	private Map<String, HitShape> hitShapeAtlas;
	private Map<String, UI> UIAtlas;
	private EnumMap<SystemType, GameSystem> systems;
	private GameState gameState;
	private ItemCatalogue items;
	private boolean loading = true;
	private double updateTime;
	public SpaceCarrier temp;

	public static Engine getEngine(){
		return engine;
	}

	public enum GameState {
		MENU, PLAY
	}
	
	private Engine(){
		this.keys = new Keyboard();
		this.mouse = new Mouse();
		Game.game.addKeyListener(keys);
		Game.game.addMouseListener(mouse);
		Game.game.addMouseMotionListener(mouse);
		entities = new EntityStream();
		systems = new EnumMap<>(SystemType.class);
		gameState = GameState.MENU;
	}
	
	public void initialize(){
		//asset loading
		Game.game.loadingScreen.getMessage().setText("Loading sounds");
		Game.game.loadingScreen.getProgress().setPercent(0.05);
		SoundLoader sounds = new SoundLoader();
		soundList = sounds.loadSounds("/resources/sound.xml");

		Game.game.loadingScreen.getMessage().setText("Loading sprites");
		Game.game.loadingScreen.getProgress().setPercent(0.25);
		spriteAtlas = new SpriteLoader().loadSprites("/resources/spritesheet.xml", "/resources/sprite.xml");
		spriteAtlas.put("hp_bar", new Sprite(1,4,2, 0xff00ff00));
		spriteAtlas.put("xp_bar", new Sprite(1,4,2, 0xff0000ff));

		Game.game.loadingScreen.getMessage().setText("Loading hitshapes");
		Game.game.loadingScreen.getProgress().setPercent(0.4);
		hitShapeAtlas = new HitShapeLoader().loadHitShapes("/resources/hitshape.xml");


		Game.game.loadingScreen.getMessage().setText("Loading items");
		Game.game.loadingScreen.getProgress().setPercent(0.65);
		items = new ItemLoader().loadItems("/resources/item.xml", spriteAtlas);

		Game.game.loadingScreen.getMessage().setText("Loading UI");
		Game.game.loadingScreen.getProgress().setPercent(0.8);
		UIAtlas = new UIElementLoader().loadUIElements("/resources/uielement.xml", this);

		((UIButton)UIAtlas.get("main").getElement("ach_button")).setDisabled(true);
		((UIButton)UIAtlas.get("main").getElement("opt_button")).setDisabled(true);

		entities.cleanup();

		Game.game.loadingScreen.getMessage().setText("Initializing systems");
		Game.game.loadingScreen.getProgress().setPercent(0.9);
		//systems
		addSystem(new SoundSystem());
		addSystem(new InventorySystem());
		addSystem(new UISystem());
		addSystem(new ProjectileSystem());
		addSystem(new ActorSystem());
		addSystem(new RenderSystem());
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

	public boolean isLoading(){
		return loading;
	}
	
	public Keyboard getKeyboard(){
		return keys;
	}
	
	public Mouse getMouse(){
		return mouse;
	}
	
	public EntityStream getEntityStream(){
		return entities;
	}
	
	public Map<String, Sprite> getSpriteAtlas(){
		return spriteAtlas;
	}
	
	public Map<String, HitShape> getHitShapeAtlas(){
		return hitShapeAtlas;
	}
	
	public Map<String, UI> getUIAtlas(){
		return UIAtlas;
	}

	public List<Sound> getSoundList(){
		return soundList;
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

	public double getUpdateTime(){
		return updateTime;
	}

	public void setUpdateTime(double updateTime){
		this.updateTime = updateTime;
	}

}
