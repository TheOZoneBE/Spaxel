package code.factories;

import code.projectiles.Projectile;
import code.graphics.SpriteData;

public class ProjectileFactory {

	protected SpriteData sprite;
	protected SpriteData trail;
	protected int damage;
	protected int life;
	protected float speed;
	
	public ProjectileFactory(SpriteData sprite, SpriteData trail, int damage, int life, float speed){
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
