package code.system;

import java.util.ArrayList;
import java.util.Set;

import code.Game;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.*;
import code.graphics.*;
import code.input.MouseWrapper;
import code.logger.DebugRenderer;
import code.math.VectorF;

public class RenderSystem extends GameSystem {
	private MasterBuffer bufferBuffer;

	private MasterRenderer master;
	private int xOffset;
	private int yOffset;

	public RenderSystem() {
		super(SystemType.RENDER);
		bufferBuffer = new MasterBuffer(Engine.getEngine().getSpritesheets());
		master = new MasterRenderer();
	}

	public void renderloading(LoadingScreen loading){
		bufferBuffer.clear();
		loading.render(bufferBuffer);
		master.render(bufferBuffer);
	}

	public void render(){
		MouseWrapper mouseWrapper = Engine.getEngine().getMouseWrapper();
		bufferBuffer.clear();
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU) {
			//TODO revisit
			Set<NEntity> playerSet = Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER);
			NEntity player = new ArrayList<>(playerSet).get(0);
			PositionComponent playerPos = (PositionComponent)player.getComponent(ComponentType.POSITION);
			int screenXOffset = mouseWrapper.getX() / 2 - Game.GAME_WIDTH / 4;
			int screenYOffset = mouseWrapper.getY() / 2 - Game.GAME_HEIGHT / 4;
			int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
			int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
			xOffset = playerXPos - (int) playerPos.getCoord().getValue(0);
			yOffset = playerYPos - (int) playerPos.getCoord().getValue(1);
			Engine.getEngine().setScreenOffset(new VectorF(xOffset, yOffset));

			//dots();
		}
		renderEntities();

		if (Engine.getEngine().getGameProperties().isDebug()){
			DebugRenderer.renderDebug(bufferBuffer);
		}
		master.render(bufferBuffer);
	}

	public void dots(){
		SpriteData dot  = Engine.getEngine().getSpriteAtlas().get("dot");
		VectorF origin = new VectorF(Engine.getEngine().getScreenOffset().getValue(0)% 64, Engine.getEngine().getScreenOffset().getValue(1) % 64);
		for (int i =  0; i < Game.GAME_WIDTH; i += 64){
			for (int j = 0; j < Game.GAME_HEIGHT; j+= 64){
				RenderData data = new RenderData();
				data.setPos(origin.sum(new VectorF(i, j)));
				data.setXScale(dot.getWidth());
				data.setYScale(dot.getHeight());
				data.setRot(0);
				data.setColor(dot.getColor());
				bufferBuffer.addNewSprite(RenderLayer.GAME, data);
			}
		}
	}

	public void renderEntities(){
		Set<NEntity> NtoRender = Engine.getEngine().getNEntityStream().getEntities(ComponentType.RENDER);
		for (NEntity ne: NtoRender){
			((RenderComponent)ne.getComponent(ComponentType.RENDER)).render(ne, bufferBuffer);
		}
		Engine.getEngine().getController().render(bufferBuffer);
	}

	/*
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
		Iterator<Entity> toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.HITPARTICLE);
		while(toRender.hasNext()){
			Entity e = toRender.next();
			e.render(xOffset, yOffset, bufferBuffer);
		}
		//TODO temporary


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

		toRender = Engine.getEngine().getEntityStream().getIterator(EntityType.PROJECTILE);
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
		uis.getCurrentUI().render(bufferBuffer);

	}*/
}
