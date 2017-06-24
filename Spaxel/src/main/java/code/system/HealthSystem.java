package code.system;

import code.components.ComponentType;
import code.components.death.DeathComponent;
import code.components.health.HealthComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.entity.DroppedItem;
import code.entity.Player;
import code.factories.entities.SpawnerIndustry;
import code.ui.UICounter;

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
        Random rand = new Random();
        //Player player = (Player)Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
        UICounter score = (UICounter)Engine.getEngine().getUIAtlas().get("play").getElement("score_counter");
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.HEALTH);
        SpawnerIndustry hpsi = (SpawnerIndustry)Engine.getEngine().getIndustryMap().get("hit_particle_spawner_industry");
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
