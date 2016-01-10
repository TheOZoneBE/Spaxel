package code.inventory;

import code.entity.Laser;
import code.entity.Projectile;
import code.graphics.Sprite;

public class Weapon extends ToggleItem {
	private Sprite proj;
	private int life;
	private double speed;

	public Weapon(double x, double y, Sprite sprite, int cooldown, Sprite proj, int life, double speed) {
		super(x, y, sprite, cooldown);
		this.proj = proj;
		this.life = life;
		this.speed = speed;
	}

	public boolean shoot() {
		if (getCD() == 0) {
			setCD(getCooldown());
			return true;
		}
		return false;
	}

	public Projectile getProjectile(double x, double y, double rot) {
		if (shoot())
			return new Laser(x, y, rot, proj, 5, life, speed);
		return null;
	}

}
