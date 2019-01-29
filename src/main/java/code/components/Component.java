package code.components;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import code.components.storage.status.StatusStorage;
import code.components.behaviour.affect.AffectBehaviour;
import code.components.behaviour.ai.AIBehaviour;
import code.components.behaviour.keyboard.KeyboardBehaviour;
import code.components.behaviour.mouse.MouseBehaviour;
import code.components.behaviour.spawn.SpawnBehaviour;
import code.components.storage.damage.DamageStorage;
import code.components.storage.event.EventStorage;
import code.components.behaviour.death.DeathBehaviour;
import code.components.behaviour.event.EventBehaviour;
import code.components.storage.experience.ExperienceStorage;
import code.components.storage.health.HealthStorage;
import code.components.behaviour.hit.HitBehaviour;
import code.components.storage.move.MoveStorage;
import code.components.storage.projectile.ProjectileStorage;
import code.components.storage.render.RenderStorage;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.shield.ShieldStorage;
import code.components.storage.shoot.ShootStorage;
import code.components.storage.spawn.SpawnStorage;
import code.components.storage.transformation.TransformationStorage;
import code.components.behaviour.render.RenderBehaviour;
import code.components.storage.stack.StackStorage;
import code.components.storage.affect.AffectStorage;
import code.components.storage.age.AgeStorage;
import code.components.storage.change.ChangeStorage;
import code.components.storage.cooldown.CooldownStorage;
import code.components.storage.hitshape.HitshapeStorage;
import code.components.storage.item.ItemStorage;

/**
 * Created by theo on 31/05/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = HitBehaviour.class, name = "HIT"),
        @JsonSubTypes.Type(value = SpawnBehaviour.class, name = "SPAWN"),
        @JsonSubTypes.Type(value = MouseBehaviour.class, name = "MOUSE"),
        @JsonSubTypes.Type(value = KeyboardBehaviour.class, name = "KEYBOARD"),
        @JsonSubTypes.Type(value = DeathBehaviour.class, name = "DEATH"),
        @JsonSubTypes.Type(value = EventBehaviour.class, name = "EVENT"),
        @JsonSubTypes.Type(value = AffectBehaviour.class, name = "AFFECT"),
        @JsonSubTypes.Type(value = RenderBehaviour.class, name = "RENDER"),
        @JsonSubTypes.Type(value = AIBehaviour.class, name = "AI"),

        @JsonSubTypes.Type(value = SpawnStorage.class, name = "SPAWN_STORE"),
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
        @JsonSubTypes.Type(value = HealthStorage.class, name = "HEALTH"),
        @JsonSubTypes.Type(value = ExperienceStorage.class, name = "EXPERIENCE"),
        @JsonSubTypes.Type(value = ItemStorage.class, name = "ITEM"),
        @JsonSubTypes.Type(value = ShootStorage.class, name = "SHOOT"),
        @JsonSubTypes.Type(value = ShieldStorage.class, name = "SHIELD"),
        @JsonSubTypes.Type(value = EventStorage.class, name = "EVENT_STORE"),
        @JsonSubTypes.Type(value = AffectStorage.class, name = "AFFECT_STORE"),
        @JsonSubTypes.Type(value = RenderStorage.class, name = "RENDER_STORE"),
        @JsonSubTypes.Type(value = ProjectileStorage.class, name = "PROJECTILE"),



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

    public ComponentType getType() {
        return type;
    }
}
