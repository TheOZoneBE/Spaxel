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

/**
 * The RenderSystem is responsible for rendering each frame
 */
public class RenderSystem extends GameSystem {
	private static final double DIM_REDUCTION = 0.75;
	private static final double MOUSE_POS_REDUCTION = 0.5;

	private MasterBuffer bufferBuffer;

	private MasterRenderer master;

	/**
	 * Create a new RenderSystem
	 */
	public RenderSystem() {
		super(SystemType.RENDER);
		bufferBuffer = new MasterBuffer(Resources.get().getSpritesheets());
		master = new MasterRenderer();
	}

	/**
	 * Update the mouse position and render a new frame
	 */
	public void update() {
		MouseWrapper mouseWrapper = Engine.get().getMouseWrapper();
		bufferBuffer.clear();
		if (Engine.get().getEngineState() == Engine.EngineState.PLAY
				|| Engine.get().getEngineState() == Engine.EngineState.PAUSE) {
			VectorD mousePos = new VectorD(mouseWrapper.getX(), mouseWrapper.getY());
			VectorD difference = mousePos.diff(Engine.get().getGameState().getCursorFollow());
			if (difference.length() > Constants.MOUSE_FOLLOW_CUTOFF) {
				difference = difference.multiplicate(Constants.MOUSE_FOLLOW_MULTIPLIER);
			}
			Engine.get().getGameState()
					.setCursorFollow(Engine.get().getGameState().getCursorFollow().sum(difference));

			NEntity player = Engine.get().getNEntityStream().getPlayer();
			PositionComponent playerPos =
					(PositionComponent) player.getComponent(ComponentType.POSITION);

			Engine.get().setScreenOffset(calculateScreenOffset(playerPos));
		}
		renderEntities();

		Engine.get().getCurrentUI().render(bufferBuffer);

		if (Engine.get().getGameState().isDebug()) {
			DebugRenderer.renderDebug(bufferBuffer);
		}
		master.render(bufferBuffer);
	}

	/**
	 * Calculate the screenOffset based on the player position
	 */
	private static VectorD calculateScreenOffset(PositionComponent playerPos) {
		VectorD dim = new VectorD(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		return dim.multiplicate(DIM_REDUCTION).diff(
				Engine.get().getGameState().getCursorFollow().multiplicate(MOUSE_POS_REDUCTION))
				.diff(playerPos.getCoord());
	}

	/**
	 * Render all entities with a Rendercomponent
	 */
	public void renderEntities() {
		Set<NEntity> toRender =
				Engine.get().getNEntityStream().getEntitiesCopy(ComponentType.RENDER);
		for (NEntity ne : toRender) {
			((RenderComponent) ne.getComponent(ComponentType.RENDER)).render(ne, bufferBuffer);
		}
	}
}
