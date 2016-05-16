package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.graphics.RenderBuffer;
import code.projectiles.Projectile;
import code.factories.ProjectileFactory;
import code.graphics.Sprite;
import code.ui.UIBar;

public class ToggleItem extends Item{
	private int cooldown;
	private int cd = 0;
	private ProjectileFactory projFac;
	private UIBar cooldownBar;

	public ToggleItem(EntityType type,  Sprite sprite, int cooldown, Sprite bar,  ProjectileFactory projFac) {
		super(type, sprite);
		this.cooldown = cooldown;
		this.projFac = projFac;
		cooldownBar = new UIBar(0, 0, 56, Math.PI/2, bar);
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

	public void render(int xPos, int yPos, RenderBuffer render){
		sprite.render(xPos, yPos, render);
		cooldownBar.render(xPos - 28, yPos,render);
	}

	public void update(){
		reduceCD();
		cooldownBar.setPercent((double)cd/(double)cooldown);
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
