package code.ui.controllers;

import code.Game;
import code.engine.Engine;
import code.ui.elements.UIType;

public final class MainController {


	/*
	 * starts a new game
	 */
	public static void startGame() {
		Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.CLASS_SELECTION));
	}

	/*
	 * opens up achievements page
	 */
	public static void achievements() {
		// TODO
	}

	/*
	 * opens up options
	 */
	public static void options() {
		Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.OPTIONS));
	}

	/*
	 * opens up credits
	 */
	public static void credits() {
		Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.CREDITS));
	}

	public static void quit() {
		Game.exit();
	}
}
