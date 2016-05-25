package code.entity;

import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.inventory.InventorySystem;

public class Player extends Entity {
	private Sprite sprite;
	private int mouseX;
	private int mouseY;
	private double maxspeed;
	private double acc;
	private double xdir;
	private double ydir;

	public Player(double x, double y, double rot, Sprite sprite) {
		super(x, y, rot);
		this.sprite = sprite;
		maxspeed = 20;
		acc = 0.5;
		xdir = 0;
		ydir = 0;
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

		x+= xdir;
		y+=ydir;
		setY(getY() + ydir);
		
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		super.update();
	}

	public boolean controlSpeed(double dx, double dy) {
		double speed = Math.sqrt(dx * dx + dy * dy);
		return speed < maxspeed;
	}

	public void render(int xPos, int yPos, RenderBuffer render) {
		rot = Math.PI + Math.atan2(((double) (mouseX - xPos)), (double) (mouseY - yPos));
		sprite.render(xPos, yPos, rot, render);
	}
	
	public Sprite getSprite() {
		return sprite;
	}

}
