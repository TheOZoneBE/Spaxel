package code.engine;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import code.entity.Entity;

public class EntityStream {
	private EnumMap<EntityType, List<Entity>> entities;
	
	public EntityStream(){
		entities = new EnumMap<>(EntityType.class);
		entities.put(EntityType.PLAYER, new ArrayList<>());
		entities.put(EntityType.ENEMY, new ArrayList<>());
		entities.put(EntityType.UI_ELEMENT, new ArrayList<>());
		entities.put(EntityType.PROJECTILE, new ArrayList<>());
		entities.put(EntityType.MOUSE1ITEM, new ArrayList<>());
		entities.put(EntityType.MOUSE3ITEM, new ArrayList<>());
		entities.put(EntityType.OTHERITEM, new ArrayList<>());
		entities.put(EntityType.SOUND, new ArrayList<>());	
		entities.put(EntityType.PARTICLE, new ArrayList<>());	
		entities.put(EntityType.SPAWNER, new ArrayList<>());	
		entities.put(EntityType.LABEL, new ArrayList<>());	
	}
	
	public List<Entity> getEntities(EntityType type){
		return entities.get(type);
	}

	
	public void addEntity(EntityType type, Entity e){
		entities.get(type).add(e);
	}
	
	public void addEntities(EntityType type, List<Entity> es){
		entities.get(type).addAll(es);
	}

}
