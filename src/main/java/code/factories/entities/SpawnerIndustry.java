package code.factories.entities;

import code.components.Component;
import code.components.ComponentType;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.transformation.TransformationStorage;
import code.entity.EntityType;
import code.entity.Entity;
import java.util.Map;

/**
 * Created by theo on 6/06/17.
 */
public class SpawnerIndustry extends EntityIndustry {
    public Entity produce(TransformationStorage trnsfrmStore, RenderableStorage rndrStore) {
        Entity entity = new Entity(EntityType.SPAWNER);
        Map<ComponentType, Component> components = buildComponents();
        components.put(rndrStore.getType(), rndrStore);
        components.put(trnsfrmStore.getType(), trnsfrmStore);
        entity.setComponents(components);
        return entity;
    }
}
