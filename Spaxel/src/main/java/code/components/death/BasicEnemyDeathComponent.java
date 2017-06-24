package code.components.death;

import code.components.ComponentType;
import code.components.age.AgeComponent;
import code.components.equip.EquipComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.entity.DroppedItem;
import code.factories.entities.SpawnerIndustry;

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
        SpawnerIndustry hpsi = (SpawnerIndustry)Engine.getEngine().getIndustryMap().get("hit_particle_spawner_industry");
        SpriteComponent esc = (SpriteComponent)entity.getComponent(ComponentType.SPRITE);
        PositionComponent epc = (PositionComponent)entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = new ParticleComponent(esc.getSprite().getRandomPart(6,6), esc.getScale());
        //add particle effect
        Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(epc, pac));

        //TODO add to score
        //Engine.getEngine().addToCounter(100);
        //TODO experience system
        //player.setXp(player.getXp()+25);
        //chance of dropping item
        if (random.nextInt(100) < 100){
            NEntity item = Engine.getEngine().getItems().produceRandom();
            item.addComponent(new EquipComponent());
            item.addComponent(new AgeComponent(500,500));
            item.addComponent(epc.clone());
            item.addComponent(entity.getComponent(ComponentType.VELOCITY));
            item.addComponent(entity.getComponent(ComponentType.RENDER));
            Engine.getEngine().getNEntityStream().addEntity(item);
            //TODO cleanup
            /*
            DroppedItem item = new DroppedItem(epc.getCoord().getValue(0), epc.getCoord().getValue(1), Engine.getEngine().getItems().getRandomItem(), 500);
            item.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_dropped_item"));
            Engine.getEngine().getEntityStream().addEntity(EntityType.DROPPEDITEM, item);*/
        }
    }
}
