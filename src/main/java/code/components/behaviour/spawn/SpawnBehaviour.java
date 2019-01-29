package code.components.behaviour.spawn;

import code.components.Behaviour;
import code.components.ComponentType;
import code.engine.Engine;
import code.entity.Entity;

public class SpawnBehaviour extends Behaviour {
    private Spawner spawner;

    public SpawnBehaviour() {
        super(ComponentType.SPAWN);
    }

    public SpawnBehaviour(Spawner spawner) {
        super(ComponentType.SPAWN);
        this.spawner = spawner;
    }

    public void execute(Entity entity) {
        Engine.get().getNEntityStream().addEntities(spawner.spawn(entity));

    }

    public SpawnBehaviour copy() {
        return new SpawnBehaviour(spawner);
    }

    /**
     * @return the spawner
     */
    public Spawner getSpawner() {
        return spawner;
    }

    /**
     * @param spawner the spawner to set
     */
    public void setSpawner(Spawner spawner) {
        this.spawner = spawner;
    }

}
