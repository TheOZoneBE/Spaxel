package code.ui;

import java.util.Map;

import code.engine.Engine;
import code.engine.GameSystem;
import code.engine.SystemType;

public class UISystem extends GameSystem{
	private UI currentUI;

	public UISystem(Engine engine) {
		super(engine);
		type = SystemType.UI;
		currentUI = engine.getUIAtlas().get("main");
	}
	
	public void update(){
		currentUI.update(engine.getMouse());
	}
	
	public UI getCurrentUI(){
		return currentUI;
	}
	
	public void changeUI(String name){
		currentUI = engine.getUIAtlas().get(name);
	}
	

}
