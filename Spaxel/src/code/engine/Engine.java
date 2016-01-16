package code.engine;

import java.util.EnumMap;
import java.util.Map;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.entity.Enemy;
import code.entity.Player;
import code.graphics.RenderSystem;
import code.graphics.Sprite;
import code.graphics.Spritesheet;
import code.input.Keyboard;
import code.input.Mouse;
import code.math.VectorD;
import code.resource.HitShapeLoader;
import code.resource.SoundLoader;
import code.resource.SpriteLoader;

public class Engine {
	private Keyboard keys;
	private Mouse mouse;
	private EntityStream entities;
	private Map<String, Sprite> spriteAtlas;
	private Map<String, HitShape> hitShapeAtlas;
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
		spriteAtlas = new SpriteLoader().loadSprites("/resources/spritesheet.xml", "/resources/sprite.xml");
		hitShapeAtlas = new HitShapeLoader().loadHitShapes("/resources/hitshape.xml");
		Player player = new Player(0, 0, 0, spriteAtlas.get("white"));
		/*
		HitShape hitShape = new HitShape();
		HitPoint hitPoint = new HitPoint(new VectorD(new double[] { 64, 64, 1 }));
		hitShape.addHitPoint(hitPoint);
		hitPoint = new HitPoint(new VectorD(new double[] {64, -64, 1}));
		hitShape.addHitPoint(hitPoint);
		hitPoint = new HitPoint(new VectorD(new double[] {-64, -64, 1}));
		hitShape.addHitPoint(hitPoint);
		hitPoint = new HitPoint(new VectorD(new double[] {-64, 64, 1}));
		hitShape.addHitPoint(hitPoint);
		*/
		player.setHitShape(hitShapeAtlas.get("hitshape_white"));
				
		entities.addEntity(EntityType.PLAYER, player);
		Enemy temp = new Enemy(128,128,.45,50,spriteAtlas.get("green"));
		temp.setHitShape(hitShapeAtlas.get("hitshape_white"));
		entities.addEntity(EntityType.ENEMY, temp);
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
