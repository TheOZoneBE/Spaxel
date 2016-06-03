package code.graphics;

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
import code.input.Keyboard;
import code.input.Mouse;
import code.ui.UISystem;

public class RenderSystem extends GameSystem {
	private RenderBuffer mainBuffer;
	private RenderBuffer backgroundBlur;
	//todo add more buffers to split rendering

	public RenderSystem() {
		super(SystemType.RENDER);
		mainBuffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		backgroundBlur = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
	}
	
	public void update(){
		EntityStream entities = Engine.getEngine().getEntityStream();
		Keyboard keys = Engine.getEngine().getKeyboard();
		Mouse mouse = Engine.getEngine().getMouse();
		mainBuffer.clear();
		backgroundBlur.clear();
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
			toRender = entities.getEntities(EntityType.PLAYER_PROJECTILE);
			for (Entity e: toRender){
				//rendering projectiles
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = entities.getEntities(EntityType.ENEMY_PROJECTILE);
			for (Entity e: toRender){
				//rendering projectiles
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = entities.getEntities(EntityType.PARTICLE);
			for (Entity e: toRender){
				//rendering particles
				e.render(xOffset, yOffset, mainBuffer);
			}
			toRender = entities.getEntities(EntityType.DROPPEDITEM);
			for (Entity e: toRender){
				//rendering dropped items
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
			toRender = entities.getEntities(EntityType.SHIPITEM);
			i= 0;
			for (Entity e: toRender){
				//rendering items
				e.render(386 + i * 72, 680, mainBuffer);
				i++;
			}
			//long startTime = System.nanoTime();
			//backgroundBlur.pixelBlur2(mainBuffer, 3, 2);
			//long stopTime = System.nanoTime();
			//System.out.println(stopTime - startTime);



		}
		UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
		uis.getCurrentUI().render(mainBuffer);
	}
	public void render(){
		for (int i = 0; i < Game.GAME_WIDTH * Game.GAME_HEIGHT; i++) {
			if (mainBuffer.getPixel(i) == 0){
				Game.game.pixels[i] = backgroundBlur.getPixel(i);
			}
			else{
				Game.game.pixels[i] = mainBuffer.getPixel(i);
			}

		}		
	}
	
	public void drawText(Graphics g){
		UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
		uis.getCurrentUI().drawText(g);
	}

}
