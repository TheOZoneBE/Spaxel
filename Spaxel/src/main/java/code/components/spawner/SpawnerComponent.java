package code.components.spawner;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

import java.util.List;
import java.util.Random;

/**
 * Created by theo on 5/06/17.
 */
public class SpawnerComponent extends Component {
    protected int rate;
    private SpawnerType spawnerType;
    protected Random rand;

    public SpawnerComponent(SpawnerType spawnerType, int rate) {
        super(ComponentType.SPAWNER);
        this.spawnerType = spawnerType;
        this.rate = rate;
        this.rand = new Random();
    }

    public List<NEntity> spawn(NEntity entity){
        return null;
    }

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
