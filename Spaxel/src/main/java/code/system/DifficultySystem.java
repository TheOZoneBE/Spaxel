package code.system;

import code.components.ComponentType;
import code.components.health.HealthComponent;
import code.components.inventory.InventoryComponent;
import code.components.item.ItemComponent;
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
    private int spawnCap = 1;
    private int numItems = 1;
    private int maxLevel = 1;
    private String[] enemyIndustries = {"enemy_green_industry", "enemy_white_industry", "enemy_red_industry", "enemy_blue_industry"};
    private Random rand;

    public DifficultySystem() {
        super(SystemType.DIFFICULTY);
        rand = new Random();
    }

    public void update(){
        if (nextSpawn > 0){
            nextSpawn--;
        }
        Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(EntityType.ENEMY);
        NEntity player = new ArrayList<>(Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER)).get(0);
        PositionComponent playerPos = (PositionComponent)player.getComponent(ComponentType.POSITION);

        if(nextSpawn == 0 && enemies.size() < spawnCap){
            EnemyIndustry ei = (EnemyIndustry) Engine.getEngine().getIndustryMap().get(enemyIndustries[rand.nextInt(4)]);
            //entity settings
            NEntity entity = ei.produce(
                    new PositionComponent(new VectorF(playerPos.getCoord().getValue(0) + rand.nextInt(256) - 128, playerPos.getCoord().getValue(1) + rand.nextInt(256) - 128), 0));
            ((HealthComponent)entity.getComponent(ComponentType.HEALTH)).levelUp(1 + rand.nextInt(maxLevel));

            //items
            addRandomItems(entity, (2 + numItems)/3, ItemType.PRIMARY, ComponentType.PRIMARY);
            addRandomItems(entity, (1 + numItems)/3, ItemType.SECONDARY, ComponentType.SECONDARY);
            addRandomItems(entity, numItems/3, ItemType.SHIP, ComponentType.SHIP);

            //Add entity
            Engine.getEngine().getNEntityStream().addEntity(entity);


            //update difficulty
            nextSpawn = Engine.getEngine().getGameProperties().getGameTime() < 450 ? 500 - Engine.getEngine().getGameProperties().getGameTime() : 50;
            int temp = Engine.getEngine().getGameProperties().getGameTime();
            spawnCap = 1 + (int)(2 * Math.sqrt(temp));
            numItems = 1 + temp / 120;
            maxLevel = 1 + temp / 60;
        }
    }

    private void addRandomItems(NEntity entity, int number, ItemType type, ComponentType ctype){
        InventoryComponent ic = (InventoryComponent)entity.getComponent(ctype);
        for (int i = 0; i < number; i++){
            NEntity item = Engine.getEngine().getItems().produceRandom(prop -> prop.getType() == type);
            ic.addItem(item);
            item.addComponent(new LinkComponent(entity));
        }
    }
}
