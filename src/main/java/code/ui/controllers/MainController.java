package code.ui.controllers;

import code.Game;
import code.engine.Engine;
import code.ui.elements.UIType;
import code.engine.Resources;

public final class MainController {
	private MainController() {

	}

	/*
	 * starts a new game
	 */
	public static void startGame() {
		Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.CLASS_SELECTION));
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
		Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.OPTIONS));
	}

	/*
	 * opens up credits
	 */
	public static void credits() {
		Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.CREDITS));
	}

	public static void quit() {
		Game.exit();
	}
}
