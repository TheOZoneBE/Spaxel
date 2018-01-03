package code.system;

import code.components.ComponentType;
import code.components.item.ItemType;
import code.components.link.LinkComponent;
import code.components.position.PositionComponent;
import code.components.primary.PrimaryComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.factories.entities.EnemyIndustry;
import code.factories.entities.EntityIndustry;
import code.math.VectorF;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by theo on 3/01/18.
 */
public class DifficultySystem extends GameSystem {
    private int nextSpawn = 0;
    private int spawnCap = 2;
    private String[] enemyIndustries = {"enemy_green_industry", "enemy_white_industry", "enemy_red_industry", "enemy_blue_industry"};
    private Random rand;

    public DifficultySystem() {
        super(SystemType.DIFFICULTY);
        rand = new Random();
    }

    public void update(){
        if (nextSpawn >0){
            nextSpawn--;
        }
        Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(EntityType.ENEMY);
        NEntity player = new ArrayList<>(Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER)).get(0);
        PositionComponent playerPos = (PositionComponent)player.getComponent(ComponentType.POSITION);

        if(nextSpawn == 0 && enemies.size() < spawnCap){
            EnemyIndustry ei = (EnemyIndustry) Engine.getEngine().getIndustryMap().get(enemyIndustries[rand.nextInt(4)]);
            NEntity entity = ei.produce(
                    new PositionComponent(new VectorF(playerPos.getCoord().getValue(0) + rand.nextInt(256) - 128, playerPos.getCoord().getValue(1) + rand.nextInt(256) - 128), 0));
            NEntity item = Engine.getEngine().getItems().produceRandom(prop -> prop.getType() == ItemType.PRIMARY);
            ((PrimaryComponent)entity.getComponent(ComponentType.PRIMARY)).addItem(item);
            item.addComponent(new LinkComponent(entity));
            Engine.getEngine().getNEntityStream().addEntity(entity);
            nextSpawn = Engine.getEngine().getGameProperties().getGameTime() < 300 ? 350 - Engine.getEngine().getGameProperties().getGameTime() : 50;
            int temp = Engine.getEngine().getGameProperties().getGameTime() / 120;
            spawnCap = 2 + temp*temp;
        }
    }
}
