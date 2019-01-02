package code.system;

import java.util.Set;
import code.Constants;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.components.render.RenderComponent;
import code.engine.Engine;
import code.engine.LoadingScreen;
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
		bufferBuffer = new MasterBuffer(Engine.getEngine().getSpritesheets());
		master = new MasterRenderer();
	}

	public void renderloading(LoadingScreen loading) {
		bufferBuffer.clear();
		loading.render(bufferBuffer);
		master.render(bufferBuffer);
	}

	public void update() {
		MouseWrapper mouseWrapper = Engine.getEngine().getMouseWrapper();
		bufferBuffer.clear();
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU) {
			VectorD mousePos = new VectorD(mouseWrapper.getX(), mouseWrapper.getY());
			VectorD difference = mousePos.diff(Engine.getEngine().getCursorFollow());
			if (difference.length() > Constants.MOUSE_FOLLOW_CUTOFF) {
				difference = difference.multiplicate(Constants.MOUSE_FOLLOW_MULTIPLIER);
			}
			Engine.getEngine()
					.setCursorFollow(Engine.getEngine().getCursorFollow().sum(difference));

			NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
			PositionComponent playerPos =
					(PositionComponent) player.getComponent(ComponentType.POSITION);

			Engine.getEngine().setScreenOffset(calculateScreenOffset(playerPos));
		}
		renderEntities();

		if (Engine.getEngine().getGameProperties().isDebug()) {
			DebugRenderer.renderDebug(bufferBuffer);
		}
		master.render(bufferBuffer);
	}

	private VectorD calculateScreenOffset(PositionComponent playerPos) {
		VectorD dim = new VectorD(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		return dim.multiplicate(DIM_REDUCTION)
				.diff(Engine.getEngine().getCursorFollow().multiplicate(MOUSE_POS_REDUCTION))
				.diff(playerPos.getCoord());
	}

	public void renderEntities() {
		Set<NEntity> toRender =
				Engine.getEngine().getNEntityStream().getEntitiesCopy(ComponentType.RENDER);
		for (NEntity ne : toRender) {
			((RenderComponent) ne.getComponent(ComponentType.RENDER)).render(ne, bufferBuffer);
		}
		Engine.getEngine().getCurrentUI().render(bufferBuffer);
	}
}
