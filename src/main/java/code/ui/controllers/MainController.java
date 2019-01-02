package code.ui.controllers;

import code.Game;
import code.engine.Engine;
import code.ui.elements.UIType;

public class MainController extends Controller {
	public MainController() {
		super(UIType.MAIN);
	}

	/*
	 * starts a new game
	 */
	public void startGame() {
		Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.CLASS_SELECTION));
	}

	/*
	 * opens up achievements page
	 */
	public void achievements() {
		// TODO
	}

	/*
	 * opens up options
	 */
	public void options() {
		Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.OPTIONS));
	}

	/*
	 * opens up credits
	 */
	public void credits() {
		Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UIType.CREDITS));
	}

	public void quit() {
		Game.exit();
	}
}
