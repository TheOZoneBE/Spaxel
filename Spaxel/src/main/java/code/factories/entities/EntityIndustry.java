package code.factories.entities;


import code.components.ComponentType;
import code.components.Component;
import code.engine.EntityType;
import code.engine.NEntity;
import code.factories.components.ComponentFactory;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.EnumMap;
import java.util.List;

/**
 * Created by theo on 3/06/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HitParticleIndustry.class, name = "HITPARTICLE"),
        @JsonSubTypes.Type(value = TrailSegmentIndustry.class, name = "TRAILSEGMENT"),
        @JsonSubTypes.Type(value = SpawnerIndustry.class, name = "SPAWNER"),
        @JsonSubTypes.Type(value = EnemyIndustry.class, name = "ENEMY"),
        @JsonSubTypes.Type(value = ProjectileIndustry.class, name = "PROJECTILE"),
        @JsonSubTypes.Type(value = ItemIndustry.class, name = "ITEM"),
        @JsonSubTypes.Type(value = PlayerIndustry.class, name = "PLAYER"),
        @JsonSubTypes.Type(value = EffectIndustry.class, name = "EFFECT"),
        @JsonSubTypes.Type(value = EffectIndustry.class, name = "VISUAL_EFFECT"),
})
public class EntityIndustry {
    private EntityType type;
    private List<ComponentFactory> factories;

    public EntityIndustry(){

    }

    public NEntity produce(){
        NEntity entity = new NEntity(type);
        entity.setComponents(buildComponents());
        return entity;
    }

    public EnumMap<ComponentType, Component> buildComponents(){
        EnumMap<ComponentType, Component> components = new EnumMap<>(ComponentType.class);
        for (ComponentFactory factory: factories){
            Component c = factory.make();
            components.put(c.getType(), c);
        }
        return components;
    }

    public List<ComponentFactory> getFactories() {
        return factories;
    }

    public void setFactories(List<ComponentFactory> factories) {
        this.factories = factories;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
