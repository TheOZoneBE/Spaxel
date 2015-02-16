package code.entity;

import code.graphics.Render;
import code.graphics.Sprite;


public class Player extends Entity {
	private Sprite sprite;
	private double rot;
	private int mouseX;
	private int mouseY;

	public Player(int x, int y, Sprite sprite) {
		super(x, y);
		this.sprite = sprite;
	}

	public void update(int x, int y, int mouseX, int mouseY) {
		super.x = x;
		super.y = y;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	public void render(int xPos, int yPos, Render render) {
		rot = Math.PI + Math.atan2(((double) (mouseX - xPos)), (double) (mouseY - yPos));
		sprite.render(xPos, yPos, rot, render);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
