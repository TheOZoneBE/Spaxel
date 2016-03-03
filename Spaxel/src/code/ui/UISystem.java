package code.ui;

import java.util.Map;

import code.engine.Engine;
import code.engine.GameSystem;
import code.engine.SystemType;

public class UISystem extends GameSystem{
	private UI currentUI;
	private Map<String, UI> UIs;

	public UISystem(Engine engine) {
		super(engine);
		type = SystemType.UI;
	}
	
	public void update(){
		currentUI.update(engine.getMouse());
	}
	
	public UI getCurrentUI(){
		return currentUI;
	}
	
	public void changeUI(String name){
		currentUI = UIs.get(name);
	}

}
