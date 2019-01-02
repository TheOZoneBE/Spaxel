package code.system;

import code.engine.Engine;
import code.engine.SystemType;

/**
 * The UISystem is responsible for updating the current UI.
 */
public class UISystem extends GameSystem {

	/**
	 * Create a new UISystem
	 */
	public UISystem() {
		super(SystemType.UI);
	}

	public void update() {
		Engine.getEngine().getCurrentUI().update();
	}
}
