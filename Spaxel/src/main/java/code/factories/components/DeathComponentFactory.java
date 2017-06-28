package code.factories.components;

import code.components.Component;
import code.components.ComponentType;
import code.components.death.BasicEnemyDeathComponent;
import code.components.death.DeathType;
import code.components.death.PlayerDeathComponent;
import code.components.death.effect.DisableMoveAffectDeathComponent;
import code.components.death.effect.DisableShootAffectDeathComponent;
import code.components.death.effect.SlowAffectDeathComponent;

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
            case SLOW_AFFECT:
                return new SlowAffectDeathComponent();
            case DISABLE_MOVE_AFFECT:
                return new DisableMoveAffectDeathComponent();
            case DISABLE_SHOOT_AFFECT:
                return new DisableShootAffectDeathComponent();
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
