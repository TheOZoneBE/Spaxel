package code.inventory;

import code.entity.Projectile;
import code.factories.ProjectileFactory;
import code.graphics.Sprite;

public class Weapon extends ToggleItem {
	private ProjectileFactory projFac;

	public Weapon(double x, double y, Sprite sprite, int cooldown, ProjectileFactory projFac) {
		super(x, y, sprite, cooldown);
		this.projFac = projFac;
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
			return projFac.make(x, y, rot);
		return null;
	}

}
