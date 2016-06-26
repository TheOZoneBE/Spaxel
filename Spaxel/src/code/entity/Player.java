package code.entity;

import code.engine.Engine;
import code.engine.SystemType;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.system.UISystem;
import code.ui.Label;
import code.ui.UIBar;

public class Player extends Actor {
	private int mouseX;
	private int mouseY;
	private double maxHealth;
	private int xpToLevel;
	private int xp;
	private int level;

	public Player(double x, double y, double rot, int health, Sprite sprite, double maxspeed, double acc) {
		super(x, y, rot, health, sprite, maxspeed, acc);
		maxHealth = health;
		xpToLevel = 100;
		level = 1;
	}

	public void update(Keyboard keys, int mouseX, int mouseY) {		
		double dx = -xdir/(maxspeed*2);
		double dy = -ydir/(maxspeed*2);
		if (keys.down) {
			dx = Math.sin(rot) * acc;
			dy = Math.cos(rot) * acc;
		}
		if (keys.up) {
			dx = -Math.sin(rot) * acc;
			dy = -Math.cos(rot) * acc;
		}
		if (keys.left) {
			dx = Math.sin(rot - Math.PI / 2) * acc;
			dy = Math.cos(rot - Math.PI / 2) * acc;
		}

		if (keys.right) {
			dx = Math.sin(rot + Math.PI / 2) * acc;
			dy = Math.cos(rot + Math.PI / 2) * acc;
		}
		if (controlSpeed(xdir + dx, ydir + dy)) {
			xdir += dx;
			ydir += dy;
		} else {
			xdir = xdir - xdir/(maxspeed*2);
			ydir = ydir - ydir/(maxspeed*2);
		}

		x+= xdir*Engine.getEngine().getUpdateTime();
		y+=ydir*Engine.getEngine().getUpdateTime();
		
		this.mouseX = mouseX;
		this.mouseY = mouseY;

		if (health <= 0){
			Engine.getEngine().stopGame();
			UISystem uis = (UISystem) Engine.getEngine().getSystem(SystemType.UI);
			uis.changeUI("game_over");
			Engine.getEngine().setGameState(Engine.GameState.MENU);
		}

		if (xp >= xpToLevel){
			xp-= xpToLevel;
			xpToLevel*=1.5;
			maxHealth*=1.5;
			health = (int)maxHealth;
		}

		UIBar hp_bar = (UIBar) Engine.getEngine().getUIAtlas().get("play").getElement("hp_bar");
		hp_bar.setPercent(health/maxHealth);
		UIBar xp_bar = (UIBar) Engine.getEngine().getUIAtlas().get("play").getElement("xp_bar");
		xp_bar.setPercent(xp/(double)xpToLevel);

		Label hp_label = (Label)Engine.getEngine().getUIAtlas().get("play").getElement("hp_label");
		Label xp_label = (Label)Engine.getEngine().getUIAtlas().get("play").getElement("xp_label");
		hp_label.setText(health + " / " + (int)maxHealth);
		xp_label.setText(xp + " / " + xpToLevel);


		super.update();
	}

	public int getXp(){
		return xp;
	}

	public void setXp(int xp){
		this.xp = xp;
	}

	public int getXpToLevel(){
		return xpToLevel;
	}

	public void setXpToLevel(int xpToLevel){
		this.xpToLevel = xpToLevel;
	}

	public void render(int xPos, int yPos, RenderBuffer render) {
		rot = Math.PI + Math.atan2(((double) (mouseX - xPos)), (double) (mouseY - yPos));
		sprite.render(xPos, yPos, rot, render);
	}

}
