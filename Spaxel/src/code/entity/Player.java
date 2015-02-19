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

	public void update(double x, double y, int mouseX, int mouseY) {
		setX(x);
		setY(y);
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	public void render(int xPos, int yPos, Render render) {
		rot = Math.PI + Math.atan2(((double) (mouseX - xPos)), (double) (mouseY - yPos));
		sprite.render(xPos, yPos, rot, render);
	}

	
}
