package code.engine;

import java.awt.Graphics;
import java.util.EnumMap;
import java.util.Map;

import code.Game;
import code.collision.HitShape;
import code.entity.Player;
import code.system.RenderSystem;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.input.Mouse;
import code.inventory.*;
import code.system.*;
import code.resource.*;
import code.ui.UI;
import code.ui.UIButton;

final public class Engine {
	private final static Engine engine = new Engine();
	private Keyboard keys;
	private Mouse mouse;
	private EntityStream entities;
	private Map<String, Sprite> spriteAtlas;
	private Map<String, HitShape> hitShapeAtlas;
	private Map<String, UI> UIAtlas;
	private EnumMap<SystemType, GameSystem> systems;
	private GameState gameState;
	private ItemCatalogue items;
	private boolean loading = true;
	private volatile int systemsToUpdate;

	public static Engine getEngine(){
		return engine;
	}

	public enum GameState {
		MENU, PLAY
	}
	
	private Engine(){
		systemsToUpdate = 9;
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
		entities.addEntities(EntityType.SOUND, sounds.loadAssets("/resources/sound.xml"));

		Game.game.loadingScreen.getMessage().setText("Loading sprites");
		Game.game.loadingScreen.getProgress().setPercent(0.25);
		spriteAtlas = new SpriteLoader().loadSprites("/resources/spritesheet.xml", "/resources/sprite.xml");
		spriteAtlas.put("hp_bar", new Sprite(1,4,2, 0xff00ff00));
		spriteAtlas.put("xp_bar", new Sprite(1,4,2, 0xff0000ff));

		Game.game.loadingScreen.getMessage().setText("Loading hitshapes");
		Game.game.loadingScreen.getProgress().setPercent(0.4);
		hitShapeAtlas = new HitShapeLoader().loadHitShapes("/resources/hitshape.xml");
		Player player = new Player(0, 0, 0, 100, spriteAtlas.get("red"),20,0.5);
		player.setHitShape(hitShapeAtlas.get("hitshape_red"));
		entities.addEntity(EntityType.PLAYER, player);

		Game.game.loadingScreen.getMessage().setText("Loading items");
		Game.game.loadingScreen.getProgress().setPercent(0.65);
		items = new ItemLoader().loadItems("/resources/item.xml", spriteAtlas);
		Item i = items.getItem("basic_laser");
		entities.addEntity(i.getType(), i);
		i = items.getItem("homing_missile");
		entities.addEntity(i.getType(),i );
		entities.addEntity(EntityType.SHIPITEM,new BasicShield(EntityType.SHIPITEM, spriteAtlas.get("basic_shield_item"), spriteAtlas.get("cooldown_bar"),250, spriteAtlas.get("basic_shield_effect"),50));

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
		addSystem(new PlayerSystem());
		addSystem(new RenderSystem());
		addSystem(new AISystem());
		addSystem(new ParticleSystem());
		addSystem(new TrailSystem());
		((SoundSystem)getSystem(SystemType.SOUND)).nextSong();
		((UISystem)getSystem(SystemType.UI)).changeUI("main");
		//starting threads
		for (GameSystem gs: systems.values()){
			Game.game.updater.addSystem(gs);
		}

		loading = false;
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
	
	public void update(){
		keys.update();
		if (gameState == GameState.MENU){
			systems.get(SystemType.SOUND).update();
			systems.get(SystemType.UI).update();
			systems.get(SystemType.RENDER).update();
		}
		else {
			systems.get(SystemType.PLAYER).update();
			systems.get(SystemType.AI).update();
			systems.get(SystemType.SOUND).update();
			systems.get(SystemType.INVENTORY).update();
			systems.get(SystemType.UI).update();
			systems.get(SystemType.PROJECTILE).update();
			systems.get(SystemType.PARTICLE).update();
			systems.get(SystemType.TRAIL).update();
			systems.get(SystemType.RENDER).update();
		}
		entities.cleanup();
	}
	
	public void render(){
		((RenderSystem)systems.get(SystemType.RENDER)).render();
	}
	
	public void drawText(Graphics g){
		((RenderSystem)systems.get(SystemType.RENDER)).drawText(g);
	}

	public void systemDone(){
		synchronized (Game.game.updater){
			systemsToUpdate--;
			if (systemsToUpdate == 0){
				Game.game.updater.done();
				Game.game.updater.notifyAll();
			}
		}
	}

	public void setSystemsToUpdate(int value){
		systemsToUpdate = value;
	}

}
