package code.components.behaviour.spawn;

import java.util.Collections;
import code.components.ComponentType;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.spawn_properties.SpawnPropertiesStorage;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Resources;
import code.entity.Entity;
import code.factories.entities.StaticParticleIndustry;
import java.util.List;

public class StaticSpawner extends Spawner {
        public StaticSpawner() {
                super(SpawnerType.STATIC);
        }


        public List<Entity> spawn(Entity entity) {
                SpawnPropertiesStorage spwnStorage = (SpawnPropertiesStorage) entity
                                .getComponent(ComponentType.SPAWN_PROPERTIES);
                TransformationStorage trnsfrmStorage = (TransformationStorage) entity
                                .getComponent(ComponentType.TRANSFORMATION);
                RenderableStorage rndrStorage =
                                (RenderableStorage) entity.getComponent(ComponentType.RENDERABLE);
                StaticParticleIndustry industry = (StaticParticleIndustry) Resources.get()
                                .getIndustryMap().get(spwnStorage.getIndustry());

                return Collections.singletonList(
                                industry.produce(trnsfrmStorage.copy(), rndrStorage.copy()));
        }
}
