package code.factories;

import code.entity.Projectile;
import code.graphics.Sprite;

public class ProjectileFactory {

	protected Sprite sprite;
	protected int damage;
	
	public ProjectileFactory(Sprite sprite, int damage){
		this.sprite = sprite;
		this.damage = damage;
	}
	
	public Projectile make(double x, double y, double rot){
		return null;
	}
	
}
