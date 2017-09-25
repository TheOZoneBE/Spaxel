package code.components.render.subcomponents;

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
        @JsonSubTypes.Type(value = LinkLinkVelocityRenderer.class, name = "LINK_LINK_VELOCITY"),
        @JsonSubTypes.Type(value = ShieldRenderer.class, name = "SHIELD"),
        @JsonSubTypes.Type(value = ShipFragmentRenderer.class, name = "SHIP_FRAGMENT")
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
