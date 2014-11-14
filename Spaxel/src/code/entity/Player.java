package code.entity;

import code.graphics.Render;
import code.graphics.Sprite;
import code.input.Keyboard;
import code.input.Mouse;

public class Player extends Entity {
	private Sprite sprite;
	private Keyboard keyboard;
	private Mouse mouse;
	private double rot;

	public Player(int x, int y, Keyboard keyboard, Mouse mouse, Sprite sprite) {
		super(x, y);
		this.keyboard = keyboard;
		this.mouse = mouse;
		this.sprite = sprite;
	}

	public void update() {
		if(keyboard.down) y++;
		if(keyboard.up) y--;
		if(keyboard.left) x--;
		if(keyboard.right) x++;		
	}
	
	public void render(int xPos, int yPos, Render render) {
		rot = (double)(mouse.getY() - yPos) /(double)(mouse.getX() -xPos);
		sprite.render(xPos, yPos, rot, render);		
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
