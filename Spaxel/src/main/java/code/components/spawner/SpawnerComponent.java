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
    private SpawnerType subType;
    protected Random rand;

    public SpawnerComponent(SpawnerType subType, int rate) {
        super(ComponentType.SPAWNER);
        this.subType = subType;
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

    public SpawnerType getSubType() {
        return subType;
    }

    public void setSubType(SpawnerType subType) {
        this.subType = subType;
    }
}
