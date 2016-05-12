package code.inventory;

import code.engine.EntityType;
import code.projectiles.Projectile;
import code.factories.ProjectileFactory;
import code.graphics.Sprite;

public class ToggleItem extends Item{
	private int cooldown;
	private int cd = 0;
	private ProjectileFactory projFac;

	public ToggleItem(EntityType type,  Sprite sprite, int cooldown, ProjectileFactory projFac) {
		super(type, sprite);
		this.cooldown = cooldown;
		this.projFac = projFac;
	}

	public Projectile activate(double x, double y, double rot){
		if (canActivate())
			return projFac.make(x, y, rot);
		return null;
	}

	public boolean canActivate(){
		if (cd == 0) {
			cd = cooldown;
			return true;
		}
		return false;
	}

	public void update(){
		reduceCD();
	}
	public void reduceCD(){
		if (cd != 0) cd--;
	}
	
	public int getCooldown(){
		return cooldown;
	}
	
	public int getCD(){
		return cd;
	}
	
	public void setCD(int cd){
		this.cd = cd;
	}

}
