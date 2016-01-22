package code.entity;

import code.graphics.RenderBuffer;
import code.graphics.Sprite;

public class Enemy extends Entity{
	private Sprite sprite;
	private int health;
	private boolean alive;
	private double maxspeed;
	private double acc;
	private double xdir;
	private double ydir;

	public Enemy(double x, double y, double rot, int health, Sprite sprite) {
		super(x, y, rot);
		this.sprite = sprite;
		this.health = health;
		alive = true;
		maxspeed = 10;
		acc = 0.5;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void update(Player player){
		if (health < 0){
			alive = false;
		}
		rot = Math.PI + Math.atan2(((double) (player.getX() - x)), (double) (player.getY() - y));
		double dx = 0;
		double dy = 0;
		
		dx = -Math.sin(rot) * acc;
		dy = -Math.cos(rot) * acc;
		if (controlSpeed(xdir + dx, ydir + dy)) {
			xdir += dx;
			ydir += dy;
		} else {
			xdir = 0.975 * xdir + dx;
			ydir = 0.975 * ydir + dy;
		}

		x+=xdir;
		y+=ydir;
		super.update();		
	}
	
	public boolean controlSpeed(double dx, double dy) {
		double speed = Math.sqrt(dx * dx + dy * dy);
		return speed < maxspeed;
	}
	
	@Override
	public void render(int xPos, int yPos, RenderBuffer render) {
		sprite.render((int) (getX() + xPos), (int) (getY() + yPos), rot, render);
		updHitShape.render(xPos,yPos, render);
	}
	
	public void hit(Projectile p){
		health -= p.getDamage();
	}

}
