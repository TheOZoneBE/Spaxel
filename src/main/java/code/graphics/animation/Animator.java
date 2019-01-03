package code.graphics.animation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import code.graphics.RenderData;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type",
        visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = FrameAnimator.class, name = "FRAME"),
        @JsonSubTypes.Type(value = XScaleAnimator.class, name = "X_SCALE"),
        @JsonSubTypes.Type(value = YScaleAnimator.class, name = "Y_SCALE")})
public abstract class Animator {
    private AnimatorType type;

    public Animator(AnimatorType type) {
        this.type = type;
    }

    public abstract void animate(double percentage, RenderData data);

    /**
     * @return the type
     */
    public AnimatorType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AnimatorType type) {
        this.type = type;
    }


}
