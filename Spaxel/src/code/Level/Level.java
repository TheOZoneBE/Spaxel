package code.Level;

import code.Game;
import code.entity.Player;
import code.graphics.Render;
import code.input.Keyboard;
import code.input.Mouse;

public class Level {

	private Player player;
	private int xOffset;
	private int yOffset;
	private int screenXOffset;
	private int screenYOffset;
	private int mouseX;
	private int mouseY;

	public Level() {
		xOffset = 0;
		yOffset = 0;
	}

	public void update(Keyboard keyboard, Mouse mouse) {
		if (keyboard.down)
			yOffset++;
		if (keyboard.up)
			yOffset--;
		if (keyboard.left)
			xOffset--;
		if (keyboard.right)
			xOffset++;

		mouseX = mouse.getX();
		mouseY = mouse.getY();
		screenXOffset = mouseX / 2 - Game.GAME_WIDTH / 4;
		screenYOffset = mouseY / 2 - Game.GAME_HEIGHT / 4;
		System.out.println(screenXOffset + "," + screenYOffset);

		player.update(xOffset, yOffset, mouseX, mouseY);
	}

	public void render(Render render) {
		int playerXPos = Game.GAME_WIDTH/2 - 8 * 4 - screenXOffset;
		int playerYPos = Game.GAME_HEIGHT/2 - 8 * 4 - screenYOffset;
		player.render(playerXPos, playerYPos, render);
	}

	public void addPlayer(Player player) {
		this.player = player;
	}

}
