package code.graphics.animation;

import java.util.List;
import code.graphics.RenderData;

public class Animation {
    private List<Animator> animators;

    public Animation() {
        super();
    }

    public RenderData getDataAt(double percentage) {
        RenderData data = new RenderData();
        for (Animator anim : animators) {
            anim.animate(percentage, data);
        }
        return data;
    }
}
