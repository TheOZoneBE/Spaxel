package code.system;

import code.components.ComponentType;
import code.components.health.HealthComponent;
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
        Player player = (Player)Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
        UICounter score = (UICounter)Engine.getEngine().getUIAtlas().get("play").getElement("score_counter");
        Set<NEntity> entities = Engine.getEngine().getNEntityStream().getEntities(ComponentType.HEALTH);
        SpawnerIndustry hpsi = (SpawnerIndustry)Engine.getEngine().getIndustryMap().get("hit_particle_spawner_industry");
        for (NEntity e: entities){
            if(((HealthComponent)e.getComponent(ComponentType.HEALTH)).getHealth() < 0){
                Engine.getEngine().getNEntityStream().removeEntity(e);
                SpriteComponent esc = (SpriteComponent)e.getComponent(ComponentType.SPRITE);
                PositionComponent epc = (PositionComponent)e.getComponent(ComponentType.POSITION);
                //TODO revisit

                SpriteComponent sc = new SpriteComponent(esc.getSprite().getRandomPart(6,6), esc.getScale());
                Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(epc, sc));

                score.addToCounter(100);
                player.setXp(player.getXp()+25);
                if (rand.nextInt(100) < 25){
                    DroppedItem item = new DroppedItem(epc.getCoord().getValue(0), epc.getCoord().getValue(1), Engine.getEngine().getItems().getRandomItem(), 500);
                    item.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_dropped_item"));
                    Engine.getEngine().getEntityStream().addEntity(EntityType.DROPPEDITEM, item);
                }
            }
        }
    }
}
