package code.factories.components;

import code.components.Component;
import code.components.ComponentType;
import code.components.death.BasicEnemyDeathComponent;
import code.components.death.DeathType;
import code.components.death.PlayerDeathComponent;

/**
 * Created by theo on 24/06/17.
 */
public class DeathComponentFactory extends ComponentFactory {
    private DeathType deathType;

    public Component make(){
        switch (deathType){
            case PLAYER:
                return new PlayerDeathComponent();
            case BASIC_ENEMY:
                return new BasicEnemyDeathComponent();
        }
        return null;
    }

    public DeathType getDeathType() {
        return deathType;
    }

    public void setDeathType(DeathType deathType) {
        this.deathType = deathType;
    }
}
