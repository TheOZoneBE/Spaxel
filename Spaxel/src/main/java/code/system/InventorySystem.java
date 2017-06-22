package code.system;

import java.util.Iterator;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
import code.inventory.Item;

public class InventorySystem extends GameSystem{

	public InventorySystem(){
		super(SystemType.INVENTORY);
	}
	
	public void update(){
		//TODO rework to only ship item update
		Player player = (Player)Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
		Iterator<Item> updating = player.getItemIterator(EntityType.MOUSE1ITEM);
		while (updating.hasNext()){
			Entity e = updating.next();
			e.update();
		}
		updating = player.getItemIterator(EntityType.MOUSE3ITEM);
		while (updating.hasNext()){
			Entity e = updating.next();
			e.update();
		}
		updating =player.getItemIterator(EntityType.SHIPITEM);
		while (updating.hasNext()){
			Entity e = updating.next();
			e.update();
		}
		Iterator<Entity> dropped = Engine.getEngine().getEntityStream().getIterator(EntityType.DROPPEDITEM);
		while (dropped.hasNext()){
			Entity e = dropped.next();
			e.update();
		}
	}

}
