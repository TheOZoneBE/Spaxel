package code.system;

import java.util.Set;
import code.Constants;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.Resources;
import code.engine.NEntity;
import code.engine.SystemType;
import code.graphics.MasterBuffer;
import code.graphics.MasterRenderer;
import code.input.MouseWrapper;
import code.logger.DebugRenderer;
import code.math.VectorD;

public class RenderSystem extends GameSystem {
	private static final double DIM_REDUCTION = 0.75;
	private static final double MOUSE_POS_REDUCTION = 0.5;

	private MasterBuffer bufferBuffer;

	private MasterRenderer master;

	public RenderSystem() {
		super(SystemType.RENDER);
		bufferBuffer = new MasterBuffer(Resources.get().getSpritesheets());
		master = new MasterRenderer();
	}

	/*
	 * public void renderloading(LoadingScreen loading) { bufferBuffer.clear();
	 * loading.render(bufferBuffer); master.render(bufferBuffer); }
	 */

	public void update() {
		MouseWrapper mouseWrapper = Engine.get().getMouseWrapper();
		bufferBuffer.clear();
		if (Engine.get().getGameState() == Engine.GameState.PLAY
				|| Engine.get().getGameState() == Engine.GameState.PAUSE) {
			VectorD mousePos = new VectorD(mouseWrapper.getX(), mouseWrapper.getY());
			VectorD difference = mousePos.diff(Engine.get().getCursorFollow());
			if (difference.length() > Constants.MOUSE_FOLLOW_CUTOFF) {
				difference = difference.multiplicate(Constants.MOUSE_FOLLOW_MULTIPLIER);
			}
			Engine.get().setCursorFollow(Engine.get().getCursorFollow().sum(difference));

			NEntity player = Engine.get().getNEntityStream().getPlayer();
			PositionComponent playerPos =
					(PositionComponent) player.getComponent(ComponentType.POSITION);

			Engine.get().setScreenOffset(calculateScreenOffset(playerPos));
		}
		renderEntities();

		if (Engine.get().getGameProperties().isDebug()) {
			DebugRenderer.renderDebug(bufferBuffer);
		}
		master.render(bufferBuffer);
	}

	private VectorD calculateScreenOffset(PositionComponent playerPos) {
		VectorD dim = new VectorD(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		return dim.multiplicate(DIM_REDUCTION)
				.diff(Engine.get().getCursorFollow().multiplicate(MOUSE_POS_REDUCTION))
				.diff(playerPos.getCoord());
	}

	public void renderEntities() {
		Set<NEntity> toRender =
				Engine.get().getNEntityStream().getEntitiesCopy(ComponentType.RENDER);
		for (NEntity ne : toRender) {
			((RenderComponent) ne.getComponent(ComponentType.RENDER)).render(ne, bufferBuffer);
		}
		Engine.get().getCurrentUI().render(bufferBuffer);
	}
}
