package code.engine;

import java.awt.Graphics;
import java.util.EnumMap;
import java.util.Map;

import code.collision.HitShape;
import code.entity.Player;
import code.factories.BasicLaserFactory;
import code.factories.BasicMissileFactory;
import code.graphics.RenderSystem;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.input.Mouse;
import code.inventory.ToggleItem;
import code.resource.HitShapeLoader;
import code.resource.SoundLoader;
import code.resource.SpriteLoader;
import code.resource.UIElementLoader;
import code.ui.UI;

public class Engine {
	private Keyboard keys;
	private Mouse mouse;
	private EntityStream entities;
	private Map<String, Sprite> spriteAtlas;
	private Map<String, HitShape> hitShapeAtlas;
	private Map<String, UI> UIAtlas;
	private EnumMap<SystemType, GameSystem> systems;
	private GameState gameState;
	
	public enum GameState {
		MENU, PLAY
	}
	
	public Engine(Keyboard keys, Mouse mouse){
		this.keys = keys;
		this.mouse = mouse;
		entities = new EntityStream();
		systems = new EnumMap<>(SystemType.class);
		gameState = GameState.MENU;
		initialize();
	}
	
	public void initialize(){
		SoundLoader sounds = new SoundLoader();
		entities.addEntities(EntityType.SOUND, sounds.loadAssets("/resources/sound.xml"));
		spriteAtlas = new SpriteLoader().loadSprites("/resources/spritesheet.xml", "/resources/sprite.xml");
		spriteAtlas.put("hp_bar", new Sprite(4,1,2, 0xff00ff00));
		spriteAtlas.put("xp_bar", new Sprite(4,1,2, 0xff0000ff));
		hitShapeAtlas = new HitShapeLoader().loadHitShapes("/resources/hitshape.xml");
		Player player = new Player(0, 0, 0, spriteAtlas.get("red"));
		player.setHitShape(hitShapeAtlas.get("hitshape_red"));		
		entities.addEntity(EntityType.PLAYER, player);
		entities.addEntity(EntityType.MOUSE1ITEM, new ToggleItem(0,0,spriteAtlas.get("basic_laser"), 5, new BasicLaserFactory(spriteAtlas.get("basic_laser"), 5, 100, 15.0)));
		entities.addEntity(EntityType.MOUSE3ITEM, new ToggleItem(0,0,spriteAtlas.get("basic_missile"), 5, new BasicMissileFactory(spriteAtlas.get("basic_missile"), 5, 100, 15.0)));
		UIAtlas = new UIElementLoader().loadUIElements("/resources/uielement.xml", this);
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
			systems.get(SystemType.RENDER).update();
		}	
	}
	
	public void render(){
		((RenderSystem)systems.get(SystemType.RENDER)).render();
	}
	
	public void drawText(Graphics g){
		((RenderSystem)systems.get(SystemType.RENDER)).drawText(g);
	}

}
