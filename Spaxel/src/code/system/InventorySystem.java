package code.system;

import java.util.Iterator;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;

public class InventorySystem extends GameSystem{

	public InventorySystem(){
		super(SystemType.INVENTORY);
	}
	
	public void update(){
		Iterator<Entity> updating = Engine.getEngine().getEntityStream().getIterator(EntityType.MOUSE1ITEM);
		while (updating.hasNext()){
			Entity e = updating.next();
			e.update();
		}
		updating = Engine.getEngine().getEntityStream().getIterator(EntityType.MOUSE3ITEM);
		while (updating.hasNext()){
			Entity e = updating.next();
			e.update();
		}
		updating = Engine.getEngine().getEntityStream().getIterator(EntityType.SHIPITEM);
		while (updating.hasNext()){
			Entity e = updating.next();
			e.update();
		}
		updating = Engine.getEngine().getEntityStream().getIterator(EntityType.DROPPEDITEM);
		while (updating.hasNext()){
			Entity e = updating.next();
			e.update();
		}
	}

}
