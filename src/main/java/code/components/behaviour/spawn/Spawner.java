package code.components.behaviour.spawn;

import java.util.List;
import code.entity.Entity;

public abstract class Spawner {
    private SpawnerType type;

    public Spawner(SpawnerType type) {
        this.type = type;
    }

    public abstract List<Entity> spawn(Entity entity);

    /**
     * @return the type
     */
    public SpawnerType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(SpawnerType type) {
        this.type = type;
    }


}
