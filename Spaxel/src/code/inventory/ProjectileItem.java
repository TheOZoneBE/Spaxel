package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.factories.ProjectileFactory;
import code.graphics.SpriteData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectileItem extends Item{
	private ProjectileFactory projFac;

	public ProjectileItem(EntityType type, String name, SpriteData sprite, SpriteData bar, int cooldown, ProjectileFactory projFac) {
		super(type,name,  sprite, bar, cooldown);
		this.projFac = projFac;

	}

	public void activate(float x, float y, float rot){
		if (canActivate()){
			float offset = stacks*-0.05f;
			List<Entity> projectiles = new ArrayList<>();
			for (int i = 0; i<=stacks; i++){
				projectiles.add(projFac.make(x, y, rot + offset));
				offset += 0.1;
			}
			Engine.getEngine().getEntityStream().addEntities(EntityType.PLAYER_PROJECTILE, projectiles);
		}
	}

	public boolean canActivate(){
		if (cd == 0) {
			cd = cooldown;
			return true;
		}
		return false;
	}

	public void render(int xPos, int yPos, RenderBuffer render){
		super.render(xPos, yPos, render);
		cooldownBar.render(xPos - 24, yPos,render);
	}

	public void update(){
		reduceCD();
		cooldownBar.setPercent((float)cd/(float)cooldown);
	}

	public Item copy(){
		return new ProjectileItem(type,name, sprite, bar, cooldown, projFac);
	}


}
