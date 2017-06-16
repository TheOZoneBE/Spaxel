package code.factories.components;

import code.components.Component;
import code.components.ComponentType;
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
        @JsonSubTypes.Type(value = DamageComponentFactory.class, name = "DAMAGE")
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
