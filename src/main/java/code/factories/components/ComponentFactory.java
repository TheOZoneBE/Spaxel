package code.factories.components;

import code.components.Component;
import code.components.ComponentType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract superclass for a factory that creates a new component
 * 
 * Created by theo on 3/06/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = CollisionComponentFactory.class, name = "HITSHAPE"),
        @JsonSubTypes.Type(value = AgeComponentFactory.class, name = "AGE"),
        @JsonSubTypes.Type(value = HealthComponentFactory.class, name = "HEALTH"),
        @JsonSubTypes.Type(value = MoveComponentFactory.class, name = "MOVE"),
        @JsonSubTypes.Type(value = VelocityComponentFactory.class, name = "CHANGE"),
        @JsonSubTypes.Type(value = RenderComponentFactory.class, name = "RENDER"),
        @JsonSubTypes.Type(value = AIComponentFactory.class, name = "AI"),
        @JsonSubTypes.Type(value = DamageComponentFactory.class, name = "DAMAGE"),
        @JsonSubTypes.Type(value = HitComponentFactory.class, name = "HIT"),
        @JsonSubTypes.Type(value = PrimaryComponentFactory.class, name = "PRIMARY"),
        @JsonSubTypes.Type(value = SecondaryComponentFactory.class, name = "SECONDARY"),
        @JsonSubTypes.Type(value = ShipComponentFactory.class, name = "SHIP"),
        @JsonSubTypes.Type(value = CooldownComponentFactory.class, name = "COOLDOWN"),
        @JsonSubTypes.Type(value = ItemComponentFactory.class, name = "ITEM"),
        @JsonSubTypes.Type(value = StackComponentFactory.class, name = "STACK"),
        @JsonSubTypes.Type(value = DeathComponentFactory.class, name = "DEATH"),
        @JsonSubTypes.Type(value = ExperienceComponentFactory.class, name = "EXPERIENCE"),
        @JsonSubTypes.Type(value = ActorComponentFactory.class, name = "ACTOR"),
        @JsonSubTypes.Type(value = AffectComponentFactory.class, name = "AFFECT"),
        @JsonSubTypes.Type(value = EffectComponentFactory.class, name = "EFFECT"),
        @JsonSubTypes.Type(value = MarkerComponentFactory.class, name = "MARKER"),

})
public abstract class ComponentFactory {
    private ComponentType type;

    /**
     * Create a new ComponentFactory
     */
    public ComponentFactory() {
        super();
    }

    /**
     * Create a new Component
     * 
     * @return the created component
     */
    public abstract Component make();

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }
}
