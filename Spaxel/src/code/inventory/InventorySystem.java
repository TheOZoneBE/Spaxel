package code.inventory;

import java.util.ArrayList;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Projectile;
import code.factories.LaserFactory;
import code.graphics.Sprite;
import code.graphics.Spritesheet;

public class InventorySystem extends GameSystem{

	public InventorySystem(Engine engine){
		super(engine);
		type = SystemType.INVENTORY;
	}
	
	public void update(){
		List<Entity> updating = engine.getEntityStream().getEntities(EntityType.MOUSE1ITEM);
		for (Entity e: updating){
			e.update();
		}
		updating = engine.getEntityStream().getEntities(EntityType.MOUSE2ITEM);
		for (Entity e: updating){
			e.update();
		}
		updating = engine.getEntityStream().getEntities(EntityType.OTHERITEM);
		for (Entity e: updating){
			e.update();
		}
	}

}
