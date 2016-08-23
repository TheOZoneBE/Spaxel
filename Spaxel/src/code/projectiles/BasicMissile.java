package code.projectiles;

import code.graphics.Sprite;

public class BasicMissile extends Projectile{

	public BasicMissile(float x, float y, float rot, Sprite sprite, Sprite trail, int damage, int life, float speed) {
		super(x, y, rot, sprite,trail, damage, life, speed);
	}

}
