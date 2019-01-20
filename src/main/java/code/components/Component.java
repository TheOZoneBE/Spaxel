package code.components;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import code.components.storage.status.StatusStorage;
import code.components.affect.AffectComponent;
import code.components.ai.AIComponent;
import code.components.behaviour.keyboard.KeyboardBehaviour;
import code.components.behaviour.mouse.MouseBehaviour;
import code.components.behaviour.spawn.SpawnBehaviour;
import code.components.storage.damage.DamageStorage;
import code.components.death.DeathComponent;
import code.components.effect.EffectComponent;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.components.hit.HitComponent;
import code.components.item.ItemComponent;
import code.components.marker.MarkerComponent;
import code.components.storage.move.MoveStorage;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.transformation.TransformationStorage;
import code.components.primary.PrimaryComponent;
import code.components.render.RenderComponent;
import code.components.secondary.SecondaryComponent;
import code.components.ship.ShipComponent;
import code.components.storage.stack.StackStorage;
import code.components.storage.age.AgeStorage;
import code.components.storage.change.ChangeStorage;
import code.components.storage.cooldown.CooldownStorage;
import code.components.storage.hitshape.HitshapeStorage;
import code.components.storage.spawn_properties.SpawnPropertiesStorage;
import code.entity.Entity;

/**
 * Created by theo on 31/05/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = TransformationStorage.class, name = "TRANSFORMATION"),
        @JsonSubTypes.Type(value = HealthComponent.class, name = "HEALTH"),
        @JsonSubTypes.Type(value = RenderableStorage.class, name = "RENDERABLE"),
        @JsonSubTypes.Type(value = RenderComponent.class, name = "RENDER"),
        @JsonSubTypes.Type(value = AIComponent.class, name = "AI"),
        @JsonSubTypes.Type(value = RenderableStorage.class, name = "PARTICLE"),
        @JsonSubTypes.Type(value = HitComponent.class, name = "HIT"),
        @JsonSubTypes.Type(value = PrimaryComponent.class, name = "PRIMARY"),
        @JsonSubTypes.Type(value = SecondaryComponent.class, name = "SECONDARY"),
        @JsonSubTypes.Type(value = ShipComponent.class, name = "SHIP"),
        @JsonSubTypes.Type(value = ItemComponent.class, name = "ITEM"),
        @JsonSubTypes.Type(value = DeathComponent.class, name = "DEATH"),
        @JsonSubTypes.Type(value = ExperienceComponent.class, name = "EXPERIENCE"),
        @JsonSubTypes.Type(value = AffectComponent.class, name = "AFFECT"),
        @JsonSubTypes.Type(value = EffectComponent.class, name = "EFFECT"),
        @JsonSubTypes.Type(value = MarkerComponent.class, name = "MARKER"),

        @JsonSubTypes.Type(value = SpawnBehaviour.class, name = "SPAWN"),
        @JsonSubTypes.Type(value = MouseBehaviour.class, name = "MOUSE"),
        @JsonSubTypes.Type(value = KeyboardBehaviour.class, name = "KEYBOARD"),

        @JsonSubTypes.Type(value = SpawnPropertiesStorage.class, name = "SPAWN_PROPERTIES"),
        @JsonSubTypes.Type(value = AgeStorage.class, name = "AGE"),
        @JsonSubTypes.Type(value = ChangeStorage.class, name = "CHANGE"),
        @JsonSubTypes.Type(value = HitshapeStorage.class, name = "HITSHAPE"),
        @JsonSubTypes.Type(value = CooldownStorage.class, name = "COOLDOWN"),
        @JsonSubTypes.Type(value = TransformationStorage.class, name = "TRANSFORMATION"),
        @JsonSubTypes.Type(value = RenderableStorage.class, name = "RENDERABLE"),
        @JsonSubTypes.Type(value = MoveStorage.class, name = "MOVE"),
        @JsonSubTypes.Type(value = StatusStorage.class, name = "STATUS"),
        @JsonSubTypes.Type(value = DamageStorage.class, name = "DAMAGE"),
        @JsonSubTypes.Type(value = StackStorage.class, name = "STACK"),

})
public abstract class Component {
    private ComponentType type;

    /**
     * Create a new component of the specified type
     * 
     * @param type the type of the component
     */
    public Component(ComponentType type) {
        this.type = type;
    }

    /**
     * Return a copy of this component.
     * 
     * @return The copied component
     */
    public abstract Component copy();

    /**
     * Add an Entity in a cascading way
     * 
     * @param entity the cascading entity
     */
    public void addCascade(Entity entity) {

    }

    /**
     * Remove the cascading entity
     */
    public void removeCascade() {

    }

    public ComponentType getType() {
        return type;
    }
}
