package code.system;

import code.engine.Engine;
import code.engine.SystemType;

public class GameSystem implements Runnable {
	protected SystemType type;

	public GameSystem(SystemType type){
		this.type = type;
	}

	public void update(){
		
	}
	public SystemType getType(){
		return type;
	}

	@Override
	public void run() {
		update();
		Engine.getEngine().systemDone();
	}
}
