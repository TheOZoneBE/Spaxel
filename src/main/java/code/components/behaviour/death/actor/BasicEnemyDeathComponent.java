package code.components.behaviour.death.actor;

import code.components.ComponentType;
import code.components.storage.age.AgeStorage;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.transformation.TransformationStorage;
import code.components.behaviour.ai.AIBehaviour;
import code.components.behaviour.ai.DroppedItemAIHandler;
import code.components.behaviour.death.DeathHandler;
import code.components.behaviour.death.DeathType;
import code.components.behaviour.hit.EquipHandler;
import code.components.behaviour.hit.HitBehaviour;
import code.components.storage.experience.ExperienceStorage;
import code.engine.Engine;
import code.engine.Resources;
import code.entity.Entity;
import code.factories.entities.SpawnerIndustry;
import code.graphics.texture.Texture;
import code.util.SpriteDataUtil;
import code.util.EntityUtil;
import code.util.SpaxelRandom;

/**
 * Executed on the death of the entity
 * 
 * Created by theo on 24/06/17.
 */
public class BasicEnemyDeathComponent extends DeathHandler {
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
                RenderableStorage rndrStore =
                                (RenderableStorage) entity.getComponent(ComponentType.RENDERABLE);
                TransformationStorage trnsStore = (TransformationStorage) entity
                                .getComponent(ComponentType.TRANSFORMATION);

                RenderableStorage particle = new RenderableStorage(
                                SpriteDataUtil.getRandomPart((Texture) rndrStore.getRenderable(),
                                                DEATH_PARTICLE_SIZE, DEATH_PARTICLE_SIZE));
                // add particle effect
                Engine.get().getNEntityStream().addEntity(hpsi.produce(trnsStore, particle));

                Engine.get().getGameState().addScore(BASIC_ENEMY_SCORE);
                // add experience
                Entity player = Engine.get().getNEntityStream().getPlayer();
                ExperienceStorage exp =
                                (ExperienceStorage) player.getComponent(ComponentType.EXPERIENCE);
                exp.addXp(BASIC_ENEMY_XP_GAIN);
                // chance of dropping item
                if (random.pass(BASIC_ENEMY_DROPRATE)) {
                        Entity item = Resources.get().getItems().produceRandom(prop -> EntityUtil
                                        .getAllItemNames(entity).contains(prop.getName()));
                        item.addComponent(new HitBehaviour(new EquipHandler()));
                        item.addComponent(new AgeStorage(ITEM_LIFETIME, ITEM_LIFETIME));
                        item.addComponent(trnsStore.copy());
                        item.addComponent(entity.getComponent(ComponentType.CHANGE));
                        item.addComponent(entity.getComponent(ComponentType.RENDER));
                        item.addComponent(new AIBehaviour(new DroppedItemAIHandler()));
                        Engine.get().getNEntityStream().addEntity(item);
                }
        }
}
