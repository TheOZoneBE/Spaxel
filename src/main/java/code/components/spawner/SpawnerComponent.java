package code.components.spawner;

import code.components.Component;
import code.components.ComponentType;
import code.entity.Entity;
import code.util.SpaxelRandom;

import java.util.List;

/**
 * Created by theo on 5/06/17.
 */
public abstract class SpawnerComponent extends Component {
    protected int rate;
    private SpawnerType spawnerType;
    protected SpaxelRandom rand;

    public SpawnerComponent(SpawnerType spawnerType, int rate) {
        super(ComponentType.SPAWNER);
        this.spawnerType = spawnerType;
        this.rate = rate;
        this.rand = new SpaxelRandom();
    }

    public abstract List<Entity> spawn(Entity entity);

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public SpawnerType getSpawnerType() {
        return spawnerType;
    }

    public void setSpawnerType(SpawnerType spawnerType) {
        this.spawnerType = spawnerType;
    }
}
