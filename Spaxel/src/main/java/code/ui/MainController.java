package code.ui;

import code.engine.Engine;
import code.engine.Engine.GameState;
import code.engine.SystemType;
import code.system.UISystem;

public class MainController extends Controller{
	public MainController() {
		super(UI.MAIN);
	}

	/*
         * starts a new game
         */
	public void startGame(){
		Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.CLASS_SELECTION));
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
		Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.OPTIONS));
	}
	
	/*
	 * opens up credits
	 */
	public void credits(){
		Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.CREDITS));
	}

	public void quit(){
		//TODO
	}
}
