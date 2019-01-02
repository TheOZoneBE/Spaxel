package code.system;

import code.components.ComponentType;
import code.components.health.HealthComponent;
import code.components.item.ItemType;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.factories.entities.EnemyIndustry;
import code.math.VectorD;
import code.util.EntityUtil;
import code.util.SpaxelRandom;
import java.util.Set;

/**
 * The DifficultySystem is responsible for spawning new enemies based on the current difficulty
 * 
 * Created by theo on 3/01/18.
 */
public class DifficultySystem extends GameSystem {
    private static final int MIN_SPAWN_DISTANCE = 500;
    private static final int MAX_SPAWN_DISTANCE = 1000;
    private static final int ENEMY_LEVELUP_TICK = 60;
    private static final int ENEMY_ITEMUP_TICK = 120;
    private static final int TICKS_INA_SECOND = 50;
    private static final int MAX_SPAWN_TIME = 7;
    private static final int NUM_INVENTORIES = 3;
    private int nextSpawn;
    private int spawnCap = 1;
    private int numItems = 1;
    private int maxLevel = 1;
    private String[] enemyIndustries = {"enemy_green_industry", "enemy_white_industry",
            "enemy_red_industry", "enemy_blue_industry"};
    private SpaxelRandom rand;

    /**
     * Create a new DifficultySystem
     */
    public DifficultySystem() {
        super(SystemType.DIFFICULTY);
        rand = new SpaxelRandom();
    }

    public void update() {
        if (nextSpawn > 0) {
            nextSpawn--;
        }
        Set<NEntity> enemies = Engine.getEngine().getNEntityStream().getEntities(EntityType.ENEMY);
        NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
        PositionComponent playerPos =
                (PositionComponent) player.getComponent(ComponentType.POSITION);

        if (nextSpawn == 0 && enemies.size() < spawnCap) {
            EnemyIndustry ei = (EnemyIndustry) Engine.getEngine().getIndustryMap()
                    .get(rand.choose(enemyIndustries));
            // entity settings
            int xOffset = rand.choose(1, -1) * rand.between(MIN_SPAWN_DISTANCE, MAX_SPAWN_DISTANCE);
            int yOffset = rand.choose(1, -1) * rand.between(MIN_SPAWN_DISTANCE, MAX_SPAWN_DISTANCE);

            VectorD offset = new VectorD(xOffset, yOffset);

            NEntity entity = ei.produce(new PositionComponent(playerPos.getCoord().sum(offset), 0));
            ((HealthComponent) entity.getComponent(ComponentType.HEALTH))
                    .levelUp(rand.between(1, maxLevel + 1));

            // items
            EntityUtil.addRandomItems(entity, (1 + 1 + numItems) / NUM_INVENTORIES,
                    ItemType.PRIMARY, ComponentType.PRIMARY);
            EntityUtil.addRandomItems(entity, (1 + numItems) / NUM_INVENTORIES, ItemType.SECONDARY,
                    ComponentType.SECONDARY);
            EntityUtil.addRandomItems(entity, numItems / NUM_INVENTORIES, ItemType.SHIP,
                    ComponentType.SHIP);

            // Add entity
            Engine.getEngine().getNEntityStream().addEntity(entity);
            // update difficulty
            updateDifficulty();
        }
    }

    /**
     * Update the difficulty based on the time
     */
    public void updateDifficulty() {
        int time = Engine.getEngine().getGameProperties().getGameTime();
        nextSpawn =
                time < MAX_SPAWN_TIME * TICKS_INA_SECOND ? MAX_SPAWN_TIME * TICKS_INA_SECOND - time
                        : TICKS_INA_SECOND;

        spawnCap = 1 + (int) Math.sqrt(time);
        numItems = 1 + time / ENEMY_ITEMUP_TICK;
        maxLevel = 1 + time / ENEMY_LEVELUP_TICK;
    }
}
