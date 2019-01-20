package code.components.behaviour.spawn;

import code.components.Behaviour;
import code.components.ComponentType;
import code.engine.Engine;
import code.entity.Entity;
import code.entity.EntityType;

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
        Engine.get().getNEntityStream().addEntities(EntityType.RANDOM_PARTICLE,
                spawner.spawn(entity));

    }

    public SpawnBehaviour copy() {
        return new SpawnBehaviour(spawner);
    }

}
