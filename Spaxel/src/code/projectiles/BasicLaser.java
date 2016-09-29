package code.projectiles;

import code.graphics.SpriteData;

public class BasicLaser extends Projectile {

	public BasicLaser(float x, float y, float rot, SpriteData sprite, SpriteData trail, int damage, int life, float speed) {
		super(x, y, rot, sprite, trail, damage, life, speed);
	}
}
