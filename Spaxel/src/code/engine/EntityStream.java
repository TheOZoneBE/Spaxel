package code.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import code.entity.Entity;

final public class EntityStream {
	private EnumMap<EntityType, List<Entity>> entities;
	private EnumMap<EntityType, List<Entity>> toAdd;
	private EnumMap<EntityType, Boolean> locks;

	public EntityStream(){
		entities = new EnumMap<>(EntityType.class);
		toAdd = new EnumMap<>(EntityType.class);
		locks = new EnumMap<>(EntityType.class);
		entities.put(EntityType.PLAYER, new ArrayList<>());
		entities.put(EntityType.ENEMY, new ArrayList<>());
		entities.put(EntityType.UI_ELEMENT, new ArrayList<>());
		entities.put(EntityType.PLAYER_PROJECTILE, new ArrayList<>());
		entities.put(EntityType.ENEMY_PROJECTILE, new ArrayList<>());
		entities.put(EntityType.MOUSE1ITEM, new ArrayList<>());
		entities.put(EntityType.MOUSE3ITEM, new ArrayList<>());
		entities.put(EntityType.SHIPITEM, new ArrayList<>());
		entities.put(EntityType.DROPPEDITEM, new ArrayList<>());
		entities.put(EntityType.SOUND, new ArrayList<>());	
		entities.put(EntityType.PARTICLE, new ArrayList<>());	
		entities.put(EntityType.SPAWNER, new ArrayList<>());	
		entities.put(EntityType.LABEL, new ArrayList<>());
		entities.put(EntityType.TRAILSEGMENT, new ArrayList<>());
	}
	
	public List<Entity> getEntities(EntityType type){
		synchronized (entities.get(type)){
			try {
				while(locks.get(type)){
					wait();
				}
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
			finally {
				locks.put(type, true);
				return entities.get(type);
			}
		}
	}

	public void releaseLock(EntityType type){
		locks.put(type, false);
	}

	public void addEntity(EntityType type, Entity e){
		toAdd.putIfAbsent(type, new ArrayList<>());
		toAdd.get(type).add(e);
	}
	
	public void addEntities(EntityType type, List<Entity> es){
		toAdd.putIfAbsent(type, new ArrayList<>());
		toAdd.get(type).addAll(es);
	}

	public void cleanup(){
		for (EntityType type: entities.keySet()){
			List<Entity> adding = toAdd.get(type);
			if (adding != null){
				entities.get(type).addAll(adding);
			}
			List<Entity> toRemove = new ArrayList<>();
			for (Entity e: entities.get(type)){
				if (e!= null && !e.isAlive()){
					toRemove.add(e);
				}
			}
			entities.get(type).removeAll(toRemove);
		}
		toAdd.clear();
	}

	public void printLocks(){
		System.out.println("---------------------------------------");
		for (EntityType type: locks.keySet()){
			System.out.println(type + ": " + locks.get(type));
		}
	}

}
