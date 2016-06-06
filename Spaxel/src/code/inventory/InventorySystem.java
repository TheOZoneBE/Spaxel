package code.inventory;

import java.util.List;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;

public class InventorySystem extends GameSystem{

	public InventorySystem(){
		super(SystemType.INVENTORY);
	}
	
	public void update(){
		List<Entity> updating = Engine.getEngine().getEntityStream().getEntities(EntityType.MOUSE1ITEM);
		for (Entity e: updating){
			e.update();
		}
		Engine.getEngine().getEntityStream().releaseLock(EntityType.MOUSE1ITEM);
		updating = Engine.getEngine().getEntityStream().getEntities(EntityType.MOUSE3ITEM);
		for (Entity e: updating){
			e.update();
		}
		Engine.getEngine().getEntityStream().releaseLock(EntityType.MOUSE3ITEM);
		updating = Engine.getEngine().getEntityStream().getEntities(EntityType.SHIPITEM);
		for (Entity e: updating){
			e.update();
		}
		Engine.getEngine().getEntityStream().releaseLock(EntityType.SHIPITEM);
		updating = Engine.getEngine().getEntityStream().getEntities(EntityType.DROPPEDITEM);
		for (Entity e: updating){
			e.update();
		}
		Engine.getEngine().getEntityStream().releaseLock(EntityType.DROPPEDITEM);
	}

}
