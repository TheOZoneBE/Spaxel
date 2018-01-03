package code.system;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.factories.entities.EnemyIndustry;
import code.math.VectorF;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by theo on 3/01/18.
 */
public class DifficultySystem extends GameSystem {
    public DifficultySystem() {
        super(SystemType.DIFFICULTY);
    }

    public void update(){
        Random rand = new Random();
        Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(ComponentType.AI);
        NEntity player = new ArrayList<>(Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER)).get(0);
        PositionComponent playerPos = (PositionComponent)player.getComponent(ComponentType.POSITION);

        if(enemies.size() < 1){
            EnemyIndustry ei = (EnemyIndustry) Engine.getEngine().getIndustryMap().get("enemy_green_industry");
            NEntity entity = ei.produce(
                    new PositionComponent(new VectorF(playerPos.getCoord().getValue(0) + rand.nextInt(256) - 128, playerPos.getCoord().getValue(1) + rand.nextInt(256) - 128), 0));
            Engine.getEngine().getNEntityStream().addEntity(entity);
        }
    }
}
