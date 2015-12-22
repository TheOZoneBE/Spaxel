package code.system;

import java.util.ArrayList;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Projectile;

public class ProjectileSystem extends GameSystem{

	public ProjectileSystem(Engine engine) {
		super(engine);
		type = SystemType.PROJECTILE;
	}
	
	public void update(){
		EntityStream entities = engine.getEntityStream();
		List<Entity> projs = entities.getEntities(EntityType.PROJECTILE);
		List<Entity> dead = new ArrayList<>();
		for (Entity proj : projs){
			proj.update();
			if (((Projectile)proj).isAlive()){
				dead.add(proj);
			}
		}
		projs.removeAll(dead);
	}

}
