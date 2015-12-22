package code.level;

import code.Game;
import code.engine.Engine;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Projectile;
import code.input.Keyboard;
import code.input.Mouse;

public class PlayerSystem extends GameSystem{

	public PlayerSystem(Engine engine) {
		super(engine);
		type = SystemType.PLAYER;
	}
	
	public void update(){
		//get mouse and keys and update the right values in player
		Mouse mouse = engine.getMouse();
		Keyboard keys = engine.getKeyboard();
		mouseX = mouse.getX();
		mouseY = mouse.getY();
		screenXOffset = mouseX / 2 - Game.GAME_WIDTH / 4;
		screenYOffset = mouseY / 2 - Game.GAME_HEIGHT / 4;
		player.update(keyboard, mouseX, mouseY);
		xOffset = (int) player.getX();
		yOffset = (int) player.getY();

		for (Projectile p : projectiles) {
			p.update();
		}
		cleanProjectiles(projectiles);
		collisionTest.update();
	}

}
