package code.system;

import code.engine.Engine;
import code.engine.SystemType;
import code.ui.UI;

public class UISystem extends GameSystem{

	public UISystem() {
		super(SystemType.UI);
	}
	
	public void update(){
		Engine.getEngine().getController().update();
	}
}
