package code.components.death.actor;

import code.components.ComponentType;
import code.components.Component;
import code.components.age.AgeComponent;
import code.components.ai.DroppedItemAIComponent;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.components.equip.EquipComponent;
import code.components.experience.ExperienceComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.Resources;
import code.entity.Entity;
import code.factories.entities.SpawnerIndustry;
import code.util.SpriteDataUtil;
import code.util.EntityUtil;
import code.util.SpaxelRandom;

/**
 * Executed on the death of the entity
 * 
 * Created by theo on 24/06/17.
 */
public class BasicEnemyDeathComponent extends DeathComponent {
    private static final int BASIC_ENEMY_SCORE = 100;
    private static final double BASIC_ENEMY_DROPRATE = 0.25;
    private static final int ITEM_LIFETIME = 500;
    private static final int DEATH_PARTICLE_SIZE = 6;
    private static final int BASIC_ENEMY_XP_GAIN = 25;

    private SpaxelRandom random;

    /**
     * Create a new BasicEnemyDeathComponent
     */
    public BasicEnemyDeathComponent() {
        super(DeathType.BASIC_ENEMY);
        random = new SpaxelRandom();
    }

    public void die(Entity entity) {
        SpawnerIndustry hpsi = (SpawnerIndustry) Resources.get().getIndustryMap()
                .get("enemy_death_particle_spawner_industry");
        SpriteComponent esc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);
        PositionComponent epc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = new ParticleComponent(SpriteDataUtil.getRandomPart(esc.getSprite(),
                DEATH_PARTICLE_SIZE, DEATH_PARTICLE_SIZE), esc.getScale());
        // add particle effect
        Engine.get().getNEntityStream().addEntity(hpsi.produce(epc, pac));

        Engine.get().getGameState().addScore(BASIC_ENEMY_SCORE);
        // add experience
        Entity player = Engine.get().getNEntityStream().getPlayer();
        ExperienceComponent exp =
                (ExperienceComponent) player.getComponent(ComponentType.EXPERIENCE);
        exp.setXp(exp.getXp() + BASIC_ENEMY_XP_GAIN);
        // chance of dropping item
        if (random.pass(BASIC_ENEMY_DROPRATE)) {
            Entity item = Resources.get().getItems().produceRandom(
                    prop -> EntityUtil.getAllItemNames(entity).contains(prop.getName()));
            item.addComponent(new EquipComponent());
            item.addComponent(new AgeComponent(ITEM_LIFETIME, ITEM_LIFETIME));
            item.addComponent(epc.copy());
            item.addComponent(entity.getComponent(ComponentType.VELOCITY));
            item.addComponent(entity.getComponent(ComponentType.RENDER));
            item.addComponent(new DroppedItemAIComponent());
            Engine.get().getNEntityStream().addEntity(item);
        }
    }

    public Component copy() {
        return new BasicEnemyDeathComponent();
    }
}
