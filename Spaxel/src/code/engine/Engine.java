package code.engine;

import java.util.EnumMap;
import java.util.Map;

import code.graphics.RenderSystem;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.input.Mouse;
import code.resource.SoundLoader;
import code.resource.SpriteLoader;

public class Engine {
	private Keyboard keys;
	private Mouse mouse;
	private EntityStream entities;
	private Map<String, Sprite> spriteAtlas;
	private EnumMap<SystemType, GameSystem> systems;
	
	public Engine(Keyboard keys, Mouse mouse){
		this.keys = keys;
		this.mouse = mouse;
		entities = new EntityStream();
		systems = new EnumMap<>(SystemType.class);
		initialize();
	}
	
	public void initialize(){
		SoundLoader sounds = new SoundLoader();
		entities.addEntities(EntityType.SOUND, sounds.loadAssets("/resources/sound.xml"));
		SpriteLoader sprites = new SpriteLoader();
		spriteAtlas = sprites.loadSprites("/resources/spritesheet.xml", "/resources/sprite.xml");
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
	
	public void addSystem(GameSystem system){
		systems.put(system.getType(), system);
	}
	
	public GameSystem getSystem(SystemType type){
		return systems.get(type);
	}
	
	public void update(){
		systems.get(SystemType.PLAYER).update();
		systems.get(SystemType.AI).update();
		systems.get(SystemType.SOUND).update();
		systems.get(SystemType.INVENTORY).update();
		systems.get(SystemType.UI).update();
		systems.get(SystemType.PROJECTILE).update();
		systems.get(SystemType.PARTICLE).update();
		systems.get(SystemType.RENDER).update();
	}
	
	public void render(){
		((RenderSystem)systems.get(SystemType.RENDER)).render();
	}

}
