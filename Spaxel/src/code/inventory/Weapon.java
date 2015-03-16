package code.inventory;

import code.entity.Laser;
import code.entity.Projectile;
import code.graphics.Sprite;

public class Weapon extends ToggleItem {
	private Sprite proj;
	private int life;
	private double speed;

	public Weapon(Sprite sprite, int cooldown, Sprite proj, int life, double speed) {
		super(sprite, cooldown);
		this.proj = proj;
		this.life = life;
		this.speed = speed;
		// TODO Auto-generated constructor stub
	}

	public boolean shoot() {
		if (getCD() == 0) {
			setCD(getCooldown());
			return true;
		}
		return false;
	}

	public Projectile getProjectile(int x, int y, double rot) {
		if (shoot())
			return new Laser(x, y, proj, rot, life, speed);
		return null;
	}

}
