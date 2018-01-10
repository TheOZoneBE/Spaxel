package code.system;

import code.components.ComponentType;
import code.components.death.DeathComponent;
import code.components.health.HealthComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;
import code.factories.entities.SpawnerIndustry;

import java.util.Random;
import java.util.Set;

/**
 * Created by theo on 8/06/17.
 */
public class HealthSystem extends GameSystem {
    public HealthSystem() {
        super(SystemType.HEALTH);
    }

    public void update(){
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.HEALTH);
        for (NEntity e: entities){
            if(((HealthComponent)e.getComponent(ComponentType.HEALTH)).getHealth() < 0){
                DeathComponent dc = (DeathComponent)e.getComponent(ComponentType.DEATH);
                if(dc != null){
                    dc.die(e);
                }
                Engine.getEngine().getNEntityStream().removeEntity(e);
            }
        }
    }
}
