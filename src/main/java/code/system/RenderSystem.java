package code.system;

import java.util.Set;

import code.Constants;
import code.Game;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.LoadingScreen;
import code.engine.NEntity;
import code.engine.SystemType;
import code.graphics.MasterBuffer;
import code.graphics.MasterRenderer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.graphics.SpriteData;
import code.input.MouseWrapper;
import code.logger.DebugRenderer;
import code.math.VectorD;

public class RenderSystem extends GameSystem {
	private MasterBuffer bufferBuffer;

	private MasterRenderer master;

	public RenderSystem() {
		super(SystemType.RENDER);
		bufferBuffer = new MasterBuffer(Engine.getEngine().getSpritesheets());
		master = new MasterRenderer();
	}

	public void renderloading(LoadingScreen loading) {
		bufferBuffer.clear();
		loading.render(bufferBuffer);
		master.render(bufferBuffer);
	}

	public void render() {
		MouseWrapper mouseWrapper = Engine.getEngine().getMouseWrapper();
		bufferBuffer.clear();
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU) {
			VectorD mousePos = new VectorD(mouseWrapper.getX(), mouseWrapper.getY());
			VectorD difference = mousePos.diff(Engine.getEngine().getCursorFollow());
			if (difference.length() > Game.MOUSE_FOLLOW_CUTOFF) {
				difference = difference.multiplicate(0.15);
			}
			Engine.getEngine().setCursorFollow(Engine.getEngine().getCursorFollow().sum(difference));

			NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
			PositionComponent playerPos = (PositionComponent) player.getComponent(ComponentType.POSITION);

			VectorD dim = new VectorD(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
			VectorD offset = dim.multiplicate(0.75).diff(new VectorD(8 * 4, 8 * 4))
					.diff(Engine.getEngine().getCursorFollow().multiplicate(0.5)).diff(playerPos.getCoord());
			Engine.getEngine().setScreenOffset(offset);
		}
		renderEntities();

		if (Engine.getEngine().getGameProperties().isDebug()) {
			DebugRenderer.renderDebug(bufferBuffer);
		}
		master.render(bufferBuffer);
	}

	public void dots() {
		SpriteData dot = Engine.getEngine().getSpriteAtlas().get("dot");
		VectorD origin = new VectorD(Engine.getEngine().getScreenOffset().getValue(0) % 64,
				Engine.getEngine().getScreenOffset().getValue(1) % 64);
		for (int i = 0; i < Constants.GAME_WIDTH; i += 64) {
			for (int j = 0; j < Constants.GAME_HEIGHT; j += 64) {
				RenderData data = new RenderData();
				data.setPos(origin.sum(new VectorD(i, j)));
				data.setXScale(dot.getWidth());
				data.setYScale(dot.getHeight());
				data.setRot(0);
				data.setColor(dot.getColor());
				bufferBuffer.addNewSprite(RenderLayer.GAME, data);
			}
		}
	}

	public void renderEntities() {
		Set<NEntity> NtoRender = Engine.getEngine().getNEntityStream().getEntities(ComponentType.RENDER);
		for (NEntity ne : NtoRender) {
			((RenderComponent) ne.getComponent(ComponentType.RENDER)).render(ne, bufferBuffer);
		}
		Engine.getEngine().getController().render(bufferBuffer);
	}
}
