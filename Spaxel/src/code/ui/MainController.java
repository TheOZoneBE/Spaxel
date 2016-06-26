package code.ui;

import code.engine.Engine;
import code.engine.Engine.GameState;
import code.engine.SystemType;
import code.system.UISystem;

public class MainController extends Controller{
	/*
	 * starts a new game
	 */
	public void startGame(){
		UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
		uis.changeUI("class_selection");
	}
	
	/*
	 * opens up achievements page
	 */
	public void achievements(){
		
	}
	
	/*
	 * opens up options
	 */
	public void options(){
		
	}
	
	/*
	 * opens up credits
	 */
	public void credits(){
		UISystem uis = (UISystem)Engine.getEngine().getSystem(SystemType.UI);
		uis.changeUI("credits");
	}

}
