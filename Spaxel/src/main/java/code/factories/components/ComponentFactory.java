package code.factories.components;

import code.components.Component;
import code.components.ComponentType;
import code.components.cooldown.CooldownComponent;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by theo on 3/06/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PositionComponentFactory.class, name = "POSITION"),
        @JsonSubTypes.Type(value = CollisionComponentFactory.class, name = "COLLISION"),
        @JsonSubTypes.Type(value = AgeComponentFactory.class, name = "AGE"),
        @JsonSubTypes.Type(value = HealthComponentFactory.class, name = "HEALTH"),
        @JsonSubTypes.Type(value = SpriteComponentFactory.class, name = "SPRITE"),
        @JsonSubTypes.Type(value = MoveComponentFactory.class, name = "MOVE"),
        @JsonSubTypes.Type(value = VelocityComponentFactory.class, name = "VELOCITY"),
        @JsonSubTypes.Type(value = RenderComponentFactory.class, name = "RENDER"),
        @JsonSubTypes.Type(value = SpawnerComponentFactory.class, name = "SPAWNER"),
        @JsonSubTypes.Type(value = AIComponentFactory.class, name = "AI"),
        @JsonSubTypes.Type(value = DamageComponentFactory.class, name = "DAMAGE"),
        @JsonSubTypes.Type(value = ParticleComponentFactory.class, name = "PARTICLE"),
        @JsonSubTypes.Type(value = HitComponentFactory.class, name = "HIT"),
        @JsonSubTypes.Type(value = PrimaryComponentFactory.class, name = "PRIMARY"),
        @JsonSubTypes.Type(value = SecondaryComponentFactory.class, name = "SECONDARY"),
        @JsonSubTypes.Type(value = ShipComponentFactory.class, name = "SHIP"),
        @JsonSubTypes.Type(value = CooldownComponentFactory.class, name = "COOLDOWN"),
        @JsonSubTypes.Type(value = ItemComponentFactory.class, name = "ITEM"),
        @JsonSubTypes.Type(value = StackComponentFactory.class, name = "STACK"),
        @JsonSubTypes.Type(value = InputComponentFactory.class, name = "INPUT"),
        @JsonSubTypes.Type(value = DeathComponentFactory.class, name = "DEATH"),
})
public class ComponentFactory {
    private ComponentType type;

    public ComponentFactory(){

    }

    public Component make(){
        return null;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }
}
