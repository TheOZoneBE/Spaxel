package code.components.death.actor;

import code.components.ComponentType;
import code.components.age.AgeComponent;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.components.equip.EquipComponent;
import code.components.experience.ExperienceComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.entity.DroppedItem;
import code.factories.entities.SpawnerIndustry;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by theo on 24/06/17.
 */
public class BasicEnemyDeathComponent extends DeathComponent {
    Random random;

    public BasicEnemyDeathComponent() {
        super(DeathType.BASIC_ENEMY);
        random = new Random();
    }

    public void die(NEntity entity){
        SpawnerIndustry hpsi = (SpawnerIndustry)Engine.getEngine().getIndustryMap().get("enemy_death_particle_spawner_industry");
        SpriteComponent esc = (SpriteComponent)entity.getComponent(ComponentType.SPRITE);
        PositionComponent epc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = new ParticleComponent(esc.getSprite().getRandomPart(6,6), esc.getScale());
        //add particle effect
        Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(epc, pac));

        Engine.getEngine().getGameProperties().addScore(100);
        //add experience
        NEntity player = new ArrayList<>(Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER)).get(0);
        ExperienceComponent exp = (ExperienceComponent)player.getComponent(ComponentType.EXPERIENCE);
        exp.setXp(exp.getXp() + 25);
        //chance of dropping item
        if (random.nextInt(100) < 25){
            NEntity item = Engine.getEngine().getItems().produceRandom();
            item.addComponent(new EquipComponent());
            item.addComponent(new AgeComponent(500,500));
            item.addComponent(epc.clone());
            item.addComponent(entity.getComponent(ComponentType.VELOCITY));
            item.addComponent(entity.getComponent(ComponentType.RENDER));
            Engine.getEngine().getNEntityStream().addEntity(item);
        }
    }
}
