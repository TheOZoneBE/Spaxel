package code.level;

import java.util.List;
import java.util.ArrayList;

import code.Game;
import code.collision.HitPoint;
import code.collision.HitShape;
import code.entity.Enemy;
import code.entity.Entity;
import code.entity.Player;
import code.entity.Projectile;
import code.graphics.Render;
import code.input.Keyboard;
import code.input.Mouse;
import code.math.VectorD;

public class Level {

	private Player player;//entitystream
	private int xOffset;//redundant
	private int yOffset;//redundant
	private int screenXOffset;//keep
	private int screenYOffset;//keep
	private int mouseX;//not needed
	private int mouseY;//not needed
	private List<Projectile> projectiles;//entitystream
	private List<Enemy> enemies;//entitystream	

	public Level() {
		xOffset = 0;
		yOffset = 0;
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
	}

	public void update(Keyboard keyboard, Mouse mouse) {
		mouseX = mouse.getX();
		mouseY = mouse.getY();
		//move to rendersystem
		screenXOffset = mouseX / 2 - Game.GAME_WIDTH / 4;
		screenYOffset = mouseY / 2 - Game.GAME_HEIGHT / 4;
		player.update(keyboard, mouseX, mouseY);
		xOffset = (int) player.getX();
		yOffset = (int) player.getY();

		for (Projectile p : projectiles) {
			p.update();
		}
		cleanProjectiles(projectiles);
	}

	public void render(Render render) {
		//move to rendersystem
		int playerXPos = Game.GAME_WIDTH / 2 - 8 * 4 - screenXOffset;
		int playerYPos = Game.GAME_HEIGHT / 2 - 8 * 4 - screenYOffset;
		render.dots(playerXPos - xOffset, playerYPos - yOffset);
		player.render(playerXPos, playerYPos, render);

		for (Projectile p : projectiles) {
			p.render(playerXPos - xOffset, playerYPos - yOffset, render);
		}

		for (Enemy e : enemies) {
			e.render(playerXPos - xOffset, playerYPos - yOffset, render);
		}
	}

	//in game, add it to entitystream
	public void addPlayer(Player player) {
		this.player = player;
		player.addLevel(this);
	}

	//move to playersystem
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	//move to playersystem
	public void addProjectiles(List<Projectile> projs) {
		if (projs != null)
			projectiles.addAll(projs);
	}

	//done
	public void cleanProjectiles(List<Projectile> projectiles) {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (!p.isAlive()) {
				projectiles.remove(p);
			}
		}
	}

}
