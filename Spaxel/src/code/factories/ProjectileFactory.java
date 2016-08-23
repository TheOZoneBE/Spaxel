package code.factories;

import code.projectiles.Projectile;
import code.graphics.Sprite;

public class ProjectileFactory {

	protected Sprite sprite;
	protected Sprite trail;
	protected int damage;
	protected int life;
	protected float speed;
	
	public ProjectileFactory(Sprite sprite, Sprite trail, int damage, int life, float speed){
		this.sprite = sprite;
		this.trail = trail;
		this.damage = damage;
		this.life = life;
		this.speed = speed;
	}
	
	public Projectile make(float x, float y, float rot){
		return null;
	}
	
}
