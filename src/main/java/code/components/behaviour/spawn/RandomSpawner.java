package code.components.behaviour.spawn;

import java.util.ArrayList;
import java.util.List;
import code.Constants;
import code.components.ComponentType;
import code.components.storage.age.AgeStorage;
import code.components.storage.change.ChangeStorage;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.spawn.SpawnStorage;
import code.components.storage.transformation.TransformationStorage;
import code.entity.Entity;
import code.factories.entities.RandomParticleIndustry;
import code.math.VectorD;
import code.util.SpaxelRandom;
import code.engine.Resources;

public class RandomSpawner extends Spawner {
        private SpaxelRandom rand;

        public RandomSpawner() {
                super(SpawnerType.RANDOM);
                this.rand = new SpaxelRandom();
        }

        public List<Entity> spawn(Entity entity) {
                SpawnStorage spwnStorage =
                                (SpawnStorage) entity.getComponent(ComponentType.SPAWN_STORE);
                TransformationStorage trnsfrmStorage = (TransformationStorage) entity
                                .getComponent(ComponentType.TRANSFORMATION);
                RenderableStorage rndrStorage =
                                (RenderableStorage) entity.getComponent(ComponentType.RENDERABLE);
                RandomParticleIndustry industry = (RandomParticleIndustry) Resources.get()
                                .getIndustryMap().get(spwnStorage.getIndustry());

                List<Entity> spawned = new ArrayList<>();
                for (int i = 0; i < spwnStorage.getRate(); i++) {
                        int life = rand.nextInt(spwnStorage.getMaxLife());
                        double dir = rand.nextDouble(Constants.FULL_CIRCLE);
                        double speed = rand.nextDouble(spwnStorage.getMaxSpeed());
                        double deltaRot = rand.between(-spwnStorage.getMaxDeltaRot(),
                                        spwnStorage.getMaxDeltaRot());
                        double dx = Math.sin(dir) * speed;
                        double dy = Math.cos(dir) * speed;
                        spawned.add(industry.produce(trnsfrmStorage.copy(),
                                        new AgeStorage(life, life),
                                        new ChangeStorage(new VectorD(dx, dy), deltaRot, 0),
                                        rndrStorage.copy()));
                }
                return spawned;
        }
}
