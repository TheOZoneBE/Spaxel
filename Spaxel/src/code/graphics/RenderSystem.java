package code.graphics;

import java.util.List;

import code.Game;
import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.input.Keyboard;
import code.input.Mouse;

public class RenderSystem extends GameSystem {
	RenderBuffer mainBuffer;
	//todo add more buffers to split rendering

	public RenderSystem(Engine engine) {
		super(engine);
		type = SystemType.RENDER;
		mainBuffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
	}
	
	public void update(){
		EntityStream entities = engine.getEntityStream();
		Keyboard keys = engine.getKeyboard();
		Mouse mouse = engine.getMouse();
		mainBuffer.clear();
		
		List<Entity> toRender = entities.getEntities(EntityType.PLAYER);
		for (Entity e: toRender){
			//rendering player
			
		}
		toRender = entities.getEntities(EntityType.ENEMY);
		for (Entity e: toRender){
			//rendering enemies
		}
		toRender = entities.getEntities(EntityType.PROJECTILE);
		for (Entity e: toRender){
			//rendering projectiles
		}
		toRender = entities.getEntities(EntityType.ITEM);
		for (Entity e: toRender){
			//rendering items
		}
		toRender = entities.getEntities(EntityType.UI_ELEMENT);
		for (Entity e: toRender){
			//rendering ui
		}
	}
	public void render(){
		for (int i = 0; i < Game.GAME_WIDTH * Game.GAME_HEIGHT; i++) {
			Game.game.pixels[i] = mainBuffer.getPixel(i);
		}
		
	}

}
