package code.factories.components;

import code.components.Component;
import code.components.death.actor.BasicEnemyDeathComponent;
import code.components.death.DeathType;
import code.components.death.actor.PlayerDeathComponent;
import code.components.death.effect.DisableMoveAffectDeathComponent;
import code.components.death.effect.DisableShootAffectDeathComponent;
import code.components.death.effect.SlowAffectDeathComponent;
import code.components.death.projectile.ClusterMissileDeathComponent;

/**
 * Created by theo on 24/06/17.
 */
public class DeathComponentFactory extends ComponentFactory {
    private DeathType deathType;

    public DeathComponentFactory() {
        super();
    }

    @Override
    public Component make() {
        Component result = null;
        switch (deathType) {
        case PLAYER:
            result = new PlayerDeathComponent();
            break;
        case BASIC_ENEMY:
            result = new BasicEnemyDeathComponent();
            break;
        case SLOW_AFFECT:
            result = new SlowAffectDeathComponent();
            break;
        case DISABLE_MOVE_AFFECT:
            result = new DisableMoveAffectDeathComponent();
            break;
        case DISABLE_SHOOT_AFFECT:
            result = new DisableShootAffectDeathComponent();
            break;
        case CLUSTER_MISSILE:
            result = new ClusterMissileDeathComponent();
            break;
        default:
            break;
        }
        return result;
    }

    public DeathType getDeathType() {
        return deathType;
    }

    public void setDeathType(DeathType deathType) {
        this.deathType = deathType;
    }
}
