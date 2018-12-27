package code.system;

import code.engine.SystemType;

public abstract class GameSystem {
	protected SystemType type;

	public GameSystem(SystemType type) {
		this.type = type;
	}

	public abstract void update();

	public SystemType getType() {
		return type;
	}
}
