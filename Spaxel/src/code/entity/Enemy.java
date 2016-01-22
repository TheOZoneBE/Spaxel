package code.entity;

import code.graphics.RenderBuffer;
import code.graphics.Sprite;

public class Enemy extends Entity{
	private Sprite sprite;
	private int health;
	private boolean alive;

	public Enemy(double x, double y, double rot, int health, Sprite sprite) {
		super(x, y, rot);
		this.sprite = sprite;
		this.health = health;
		alive = true;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void update(Player player){
		if (health < 0){
			alive = false;
		}
		rot = Math.PI + Math.atan2(((double) (player.getX() - x)), (double) (player.getY() - y));
		super.update();		
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
