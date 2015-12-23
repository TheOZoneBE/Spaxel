package code.graphics;

import java.util.List;

import code.Game;
import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
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
		Player player = (Player)entities.getEntities(EntityType.PLAYER).get(0);
		int screenXOffset = mouse.getX() / 2 - Game.GAME_WIDTH / 4;
		int screenYOffset = mouse.getY() / 2 - Game.GAME_HEIGHT / 4;
		
		int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
		int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
		int xOffset = playerXPos - (int)player.getX();
		int yOffset = playerYPos - (int)player.getY();
		mainBuffer.clear();
		mainBuffer.dots(xOffset, yOffset);
		player.render(playerXPos,playerYPos, mainBuffer);
		
		List<Entity> toRender = entities.getEntities(EntityType.ENEMY);
		for (Entity e: toRender){
			//rendering enemies
			e.render(xOffset, yOffset, mainBuffer);
		}
		toRender = entities.getEntities(EntityType.PROJECTILE);
		for (Entity e: toRender){
			//rendering projectiles
			e.render(xOffset, yOffset, mainBuffer);
		}
		toRender = entities.getEntities(EntityType.ITEM);
		for (Entity e: toRender){
			//rendering items
			e.render(xOffset, yOffset, mainBuffer);
		}
		toRender = entities.getEntities(EntityType.UI_ELEMENT);
		for (Entity e: toRender){
			//rendering ui
			e.render(xOffset, yOffset, mainBuffer);
		}
	}
	public void render(){
		for (int i = 0; i < Game.GAME_WIDTH * Game.GAME_HEIGHT; i++) {
			Game.game.pixels[i] = mainBuffer.getPixel(i);
		}
		
	}

}