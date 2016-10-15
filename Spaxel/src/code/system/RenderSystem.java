package code.system;

import java.util.Iterator;

import code.Game;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.graphics.MasterBuffer;
import code.graphics.MasterRenderer;
import code.graphics.RenderBuffer;
import code.input.MouseWrapper;

public class RenderSystem extends GameSystem {
	//private RenderBuffer actorBuffer; //actors
	//private RenderBuffer particleBuffer; //particles
	//private RenderBuffer effectBuffer; //projectile, effects, trails
	//private RenderBuffer UIBuffer; //mouse1item, mouse3item, shipitem, uielement
	private MasterBuffer bufferBuffer;

	private MasterRenderer master;
	private int xOffset;
	private int yOffset;

	public RenderSystem() {
		super(SystemType.RENDER);
		bufferBuffer = new MasterBuffer(Engine.getEngine().getSpritesheets());
		//actorBuffer = new RenderBuffer();
		//particleBuffer = new RenderBuffer();
		//effectBuffer = new RenderBuffer();
		//UIBuffer = new RenderBuffer();
		master = new MasterRenderer();
	}

	public void render(){
		MouseWrapper mouseWrapper = Engine.getEngine().getMouseWrapper();
		//actorBuffer.clear();
		//particleBuffer.clear();
		//effectBuffer.clear();
		//UIBuffer.clear();
		bufferBuffer.clear();
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU) {
			Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
			int screenXOffset = mouseWrapper.getX() / 2 - Game.GAME_WIDTH / 4;
			int screenYOffset = mouseWrapper.getY() / 2 - Game.GAME_HEIGHT / 4;
			int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
			int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
			xOffset = playerXPos - (int) player.getX();
			yOffset = playerYPos - (int) player.getY();
			renderActors();
			renderParticles();
			renderEffects();
		}
		renderUI();
		master.render(bufferBuffer);
	}

	public void renderActors(){
		Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
		int playerXPos = xOffset + (int)player.getX();
		int playerYPos = yOffset + (int)player.getY();
		player.render(playerXPos,playerYPos, bufferBuffer);

		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, bufferBuffer);
		}
	}

	public void renderParticles(){
		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PARTICLE);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, bufferBuffer);
		}

	}

	public void renderEffects(){
		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.TRAILSEGMENT);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, bufferBuffer);
		}

		toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PLAYER_PROJECTILE);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, bufferBuffer);
		}

		toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY_PROJECTILE);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, bufferBuffer);
		}


	}

	public void renderUI(){
		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.DROPPEDITEM);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, bufferBuffer);
		}

		UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
		//TODO text rendering
		uis.getCurrentUI().render(bufferBuffer);

	}
}
