package code.ui;

import code.engine.Engine.GameState;
import code.engine.SystemType;

public class MainController extends Controller{
	/*
	 * starts a new game
	 */
	public void startGame(){
		System.out.println("started");
		engine.setGameState(GameState.PLAY);
		UISystem uis = (UISystem)engine.getSystem(SystemType.UI);
		uis.changeUI("play");
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
		
	}

}
