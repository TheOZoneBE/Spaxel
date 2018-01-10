package code.system;

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
	private float xOffset;
	private float yOffset;

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
			VectorF mousePos = new VectorF(mouseWrapper.getX(), mouseWrapper.getY());
			VectorF difference = mousePos.diff(Engine.getEngine().getCursorFollow());
			if (difference.length() > Game.MOUSE_FOLLOW_CUTOFF){
				difference = difference.multiplicate(0.15f);
			}
			Engine.getEngine().setCursorFollow(Engine.getEngine().getCursorFollow().sum(difference));

			NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
			PositionComponent playerPos = (PositionComponent)player.getComponent(ComponentType.POSITION);

			VectorF dim = new VectorF(Game.GAME_WIDTH, Game.GAME_HEIGHT);
			VectorF offset = dim
					.multiplicate(0.75f)
					.diff(new VectorF(8*4,8*4))
					.diff(Engine.getEngine().getCursorFollow().multiplicate(0.5f))
					.diff(playerPos.getCoord());
			Engine.getEngine().setScreenOffset(offset);

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
}
