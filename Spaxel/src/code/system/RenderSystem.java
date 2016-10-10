package code.system;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import code.Game;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
import code.graphics.MasterRenderer;
import code.graphics.RenderBuffer;
import code.input.Mouse;
import code.inventory.Item;

public class RenderSystem extends GameSystem {
	private RenderBuffer actorBuffer; //actors
	private RenderBuffer particleBuffer; //particles
	private RenderBuffer effectBuffer; //projectile, effects, trails
	private RenderBuffer UIBuffer; //mouse1item, mouse3item, shipitem, uielement

	private MasterRenderer master;
	private int xOffset;
	private int yOffset;

	public RenderSystem() {
		super(SystemType.RENDER);
		actorBuffer = new RenderBuffer();
		particleBuffer = new RenderBuffer();
		effectBuffer = new RenderBuffer();
		UIBuffer = new RenderBuffer();
		master = new MasterRenderer();
	}

	public void render(){
		Mouse mouse = Engine.getEngine().getMouse();
		actorBuffer.clear();
		particleBuffer.clear();
		effectBuffer.clear();
		UIBuffer.clear();
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU) {
			Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
			int screenXOffset = mouse.getX() / 2 - Game.GAME_WIDTH / 4;
			int screenYOffset = mouse.getY() / 2 - Game.GAME_HEIGHT / 4;
			int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
			int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
			xOffset = playerXPos - (int) player.getX();
			yOffset = playerYPos - (int) player.getY();
			renderActors();
			renderParticles();
			renderEffects();
		}
		renderUI();
	}

	public void renderActors(){
		Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
		int playerXPos = xOffset + (int)player.getX();
		int playerYPos = yOffset + (int)player.getY();
		player.render(playerXPos,playerYPos, actorBuffer);

		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, actorBuffer);
		}

		master.render(actorBuffer, Engine.getEngine().getSpritesheets().get("ships").getId());
	}

	public void renderParticles(){
		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PARTICLE);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, particleBuffer);
		}

		master.render(particleBuffer, Engine.getEngine().getSpritesheets().get("ships").getId());
	}

	public void renderEffects(){
		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.TRAILSEGMENT);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, effectBuffer);
		}

		toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PLAYER_PROJECTILE);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, effectBuffer);
		}

		toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY_PROJECTILE);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, effectBuffer);
		}

		master.render(effectBuffer, Engine.getEngine().getSpritesheets().get("effect_sheet").getId());

	}

	public void renderUI(){
		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.DROPPEDITEM);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, UIBuffer);
		}

		UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
		//TODO text rendering
		uis.getCurrentUI().render(UIBuffer);
		master.render(UIBuffer, Engine.getEngine().getSpritesheets().get("uisheet").getId());
	}
}
