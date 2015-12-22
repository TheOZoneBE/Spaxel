package code.system;

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
		for (Entity proj : projs){
			proj.update();
		}
	}
	public void cleanProjectiles(List<Projectile> projectiles) {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (!p.isAlive()) {
				projectiles.remove(p);
			}
		}
	}


}
