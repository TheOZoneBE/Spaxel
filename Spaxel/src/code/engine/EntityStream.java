package code.engine;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import code.entity.Entity;
import code.entity.EntityType;

public class EntityStream {
	private EnumMap<EntityType, List<Entity>> entities;
	
	public EntityStream(){
		entities.put(EntityType.PLAYER, new ArrayList<>());
		entities.put(EntityType.ENEMY, new ArrayList<>());
		entities.put(EntityType.UI_ELEMENT, new ArrayList<>());
		entities.put(EntityType.PROJECTILE, new ArrayList<>());
		entities.put(EntityType.ITEM, new ArrayList<>());		
	}
	
	public List<Entity> getEntities(EntityType type){
		return entities.get(type);
	}
	
	public void addEntity(EntityType type, Entity e){
		entities.get(type).add(e);
	}	

}
