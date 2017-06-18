package code.engine;

import java.util.*;

import code.entity.Entity;

final public class EntityStream {
	private EnumMap<EntityType, List<Entity>> entities;
	private EnumMap<EntityType, List<Entity>> toAdd;

	public EntityStream(){
		entities = new EnumMap<>(EntityType.class);
		toAdd = new EnumMap<>(EntityType.class);
		entities.put(EntityType.PLAYER, new ArrayList<>());
		entities.put(EntityType.ENEMY, new ArrayList<>());
		entities.put(EntityType.UI_ELEMENT, new ArrayList<>());
		entities.put(EntityType.PLAYER_PROJECTILE, new ArrayList<>());
		entities.put(EntityType.PROJECTILE, new ArrayList<>());
		entities.put(EntityType.MOUSE1ITEM, new ArrayList<>());
		entities.put(EntityType.MOUSE3ITEM, new ArrayList<>());
		entities.put(EntityType.SHIPITEM, new ArrayList<>());
		entities.put(EntityType.DROPPEDITEM, new ArrayList<>());
		entities.put(EntityType.SOUND, new ArrayList<>());	
		entities.put(EntityType.HITPARTICLE, new ArrayList<>());
		entities.put(EntityType.SPAWNER, new ArrayList<>());	
		entities.put(EntityType.LABEL, new ArrayList<>());
		entities.put(EntityType.TRAILSEGMENT, new ArrayList<>());
	}

	public Iterator<Entity> getIterator(EntityType type){
		return new Iterator<Entity>() {
			List<Entity> iterating = entities.get(type);
			int i = -1;
			@Override
			public boolean hasNext() {
				return i < iterating.size() - 1;
			}

			@Override
			public Entity next() {
				i++;
				return iterating.get(i);
			}
		};
	}

	public List<Entity> getEntities(EntityType type){
		return entities.get(type);
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
				if (e != null){
					e.reduceLife();
				}
				if (e== null || !e.isAlive()){
					toRemove.add(e);
				}
			}
			entities.get(type).removeAll(toRemove);
		}
		toAdd.clear();
	}

	public void clear(){
		for (List<Entity> ent: entities.values()){
			ent.clear();
		}
	}

}
