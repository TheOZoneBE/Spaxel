package code.Level;

import java.util.List;
import java.util.ArrayList;

import code.Game;
import code.entity.Player;
import code.entity.Projectile;
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
	private List<Projectile> projectiles;

	public Level() {
		xOffset = 0;
		yOffset = 0;
		projectiles = new ArrayList<Projectile>();
	}

	public void update(Keyboard keyboard, Mouse mouse) {
		if (keyboard.down)
			yOffset+=2;
		if (keyboard.up)
			yOffset-=2;
		if (keyboard.left)
			xOffset-=2;
		if (keyboard.right)
			xOffset+=2;

		mouseX = mouse.getX();
		mouseY = mouse.getY();
		screenXOffset = mouseX / 2 - Game.GAME_WIDTH / 4;
		screenYOffset = mouseY / 2 - Game.GAME_HEIGHT / 4;
		player.update(xOffset, yOffset, mouseX, mouseY);

		if (mouse.mouse1) {
			player.primaryWeapon();
		}
		if (mouse.mouse2) {
			player.secondaryWeapon();
		}
		
		for (Projectile p: projectiles){
			p.update();
		}
		cleanProjectiles(projectiles);
	}

	public void render(Render render) {
		int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
		int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
		player.render(playerXPos, playerYPos, render);
		
		for (Projectile p: projectiles){
			p.render(playerXPos - xOffset, playerYPos -yOffset, render);
		}
	}

	public void addPlayer(Player player) {
		this.player = player;
		player.addLevel(this);
	}
	
	public void addProjectile(Projectile p){
		projectiles.add(p);
	}
	
	public void cleanProjectiles(List<Projectile> projectiles){
		for (int i = 0; i < projectiles.size(); i++){
			Projectile p = projectiles.get(i);
			if (!p.isAlive()){
				projectiles.remove(p);
			}
		}
	}

}
