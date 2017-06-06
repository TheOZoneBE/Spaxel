package code.components.subcomponents;

import code.engine.NEntity;
import code.graphics.RenderData;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by theo on 5/06/17.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SpriteSheetRenderer.class, name = "SPRITESHEET"),
        @JsonSubTypes.Type(value = VelocityRenderer.class, name = "VELOCITY"),
        @JsonSubTypes.Type(value = StationaryRenderer.class, name = "STATIONARY"),
        @JsonSubTypes.Type(value = FadeRenderer.class, name = "FADE"),
})
public class Renderer {
    private RendererType type;
    public void apply(RenderData data, NEntity entity){

    }

    public RendererType getType() {
        return type;
    }

    public void setType(RendererType type) {
        this.type = type;
    }
}
