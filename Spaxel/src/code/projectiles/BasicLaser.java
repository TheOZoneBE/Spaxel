package code.projectiles;

import code.graphics.Sprite;

public class BasicLaser extends Projectile {

	public BasicLaser(double x, double y, double rot, Sprite sprite, Sprite trail, int damage, int life, double speed) {
		super(x, y, rot, sprite, trail, damage, life, speed);
	}
}
