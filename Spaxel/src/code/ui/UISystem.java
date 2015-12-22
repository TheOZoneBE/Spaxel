package code.ui;

import code.engine.Engine;
import code.engine.GameSystem;
import code.engine.SystemType;

public class UISystem extends GameSystem{

	public UISystem(Engine engine) {
		super(engine);
		type = SystemType.UI;
	}
	
	public void update(){
		
	}

}
