package code.entity;

import code.engine.Engine;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.ui.UIBar;

public class Player extends Actor {
	private int mouseX;
	private int mouseY;
	private double maxHealth;

	public Player(double x, double y, double rot, int health, Sprite sprite, double maxspeed, double acc) {
		super(x, y, rot, health, sprite, maxspeed, acc);
		maxHealth = health;
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

		UIBar hp = (UIBar) Engine.getEngine().getUIAtlas().get("play").getElement("hp_bar");
		hp.setPercent(health/maxHealth);


		super.update();
	}

	public void render(int xPos, int yPos, RenderBuffer render) {
		rot = Math.PI + Math.atan2(((double) (mouseX - xPos)), (double) (mouseY - yPos));
		sprite.render(xPos, yPos, rot, render);
	}

}
