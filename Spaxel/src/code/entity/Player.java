package code.entity;

import code.Level.Level;
import code.graphics.Render;
import code.graphics.Sprite;


public class Player extends Entity {
	private Sprite sprite;
	private double rot;
	private int mouseX;
	private int mouseY;
	private Level level;

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
	
	public void addLevel(Level level){
		this.level = level;
	}
	
	public void primaryWeapon(){
		level.addProjectile(new Laser((int)getX(), (int)getY(), sprite, rot, 200, 5.0));
	}
	
	public void secondaryWeapon(){
		
	}

	
}
