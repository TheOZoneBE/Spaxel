package code.factories;

import code.projectiles.Projectile;
import code.graphics.Sprite;

public class ProjectileFactory {

	protected Sprite sprite;
	protected int damage;
	protected int life;
	protected double speed;
	
	public ProjectileFactory(Sprite sprite, int damage, int life, double speed){
		this.sprite = sprite;
		this.damage = damage;
		this.life = life;
		this.speed = speed;
	}
	
	public Projectile make(double x, double y, double rot){
		return null;
	}
	
}
