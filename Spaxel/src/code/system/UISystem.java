package code.system;

import code.engine.Engine;
import code.engine.SystemType;
import code.ui.UI;

public class UISystem extends GameSystem{
	private UI currentUI;

	public UISystem() {
		super(SystemType.UI);
	}
	
	public void update(){
		currentUI.update();
	}
	
	public UI getCurrentUI(){
		return currentUI;
	}
	
	public void changeUI(String name){
		currentUI = Engine.getEngine().getUIAtlas().get(name);
	}
	

}
