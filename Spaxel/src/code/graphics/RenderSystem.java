package code.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import code.Game;
import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
import code.entity.TrailSegment;
import code.input.Keyboard;
import code.input.Mouse;
import code.ui.UISystem;
import javafx.scene.layout.Background;

public class RenderSystem extends GameSystem {
	private RenderBuffer mainBuffer;
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
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU){

			Player player = (Player)entities.getEntities(EntityType.PLAYER).get(0);
			int screenXOffset = mouse.getX() / 2 - Game.GAME_WIDTH / 4;
			int screenYOffset = mouse.getY() / 2 - Game.GAME_HEIGHT / 4;

			int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
			int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
			int xOffset = playerXPos - (int)player.getX();
			int yOffset = playerYPos - (int)player.getY();

			mainBuffer.dots(xOffset, yOffset);
			player.render(playerXPos,playerYPos, mainBuffer);

			List<Entity> toRender = entities.getEntities(EntityType.TRAILSEGMENT);
			for (Entity e: toRender){
				//rendering enemies
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = entities.getEntities(EntityType.ENEMY);
			for (Entity e: toRender){
				//rendering enemies
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = entities.getEntities(EntityType.PROJECTILE);
			for (Entity e: toRender){
				//rendering projectiles
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = entities.getEntities(EntityType.PARTICLE);
			for (Entity e: toRender){
				//rendering particles
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = entities.getEntities(EntityType.MOUSE1ITEM);
			int i = 0;
			for (Entity e: toRender){
				//rendering items
				e.render(40, 40 + i * 72, mainBuffer);
				i++;
			}
			toRender = entities.getEntities(EntityType.MOUSE3ITEM);
			i= 0;
			for (Entity e: toRender){
				//rendering items
				e.render(1240, 40 + i * 72, mainBuffer);
				i++;
			}
			toRender = entities.getEntities(EntityType.OTHERITEM);
			i= 0;
			for (Entity e: toRender){
				//rendering items
				e.render(386 + i * 72, 680, mainBuffer);
				i++;
			}



		}
		UISystem uis = (UISystem)engine.getSystem(SystemType.UI);
		uis.getCurrentUI().render(mainBuffer);
	}
	public void render(){
		for (int i = 0; i < Game.GAME_WIDTH * Game.GAME_HEIGHT; i++) {
			Game.game.pixels[i] = mainBuffer.getPixel(i);
		}		
	}
	
	public void drawText(Graphics g){
		UISystem uis = (UISystem)engine.getSystem(SystemType.UI);
		uis.getCurrentUI().drawText(g);
	}

}
