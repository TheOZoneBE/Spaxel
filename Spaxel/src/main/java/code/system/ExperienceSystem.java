package code.system;

import code.components.ComponentType;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.SystemType;

import java.util.Set;

/**
 * Created by theo on 26/06/17.
 */
public class ExperienceSystem extends GameSystem {
    public ExperienceSystem() {
        super(SystemType.EXPERIENCE);
    }

    public void update(){
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.EXPERIENCE);
        for (NEntity entity: entities){
            ExperienceComponent ec = (ExperienceComponent)entity.getComponent(ComponentType.EXPERIENCE);
            if (ec.getXpToLevel() < ec.getXp()){
                ec.setXp(ec.getXp() - ec.getXpToLevel());
                ec.setLevel(ec.getLevel() + 1);
                HealthComponent hc = (HealthComponent)entity.getComponent(ComponentType.HEALTH);
                hc.levelUp(ec.getLevel());
            }

        }
    }
}
