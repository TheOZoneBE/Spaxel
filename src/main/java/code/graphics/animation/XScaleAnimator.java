package code.graphics.animation;

import code.graphics.RenderData;

public class XScaleAnimator extends Animator {
    private double maxScale;

    public XScaleAnimator() {
        super(AnimatorType.X_SCALE);
    }

    public void animate(double percentage, RenderData data) {
        double scale = percentage * maxScale;

        data.applyXScale(scale);
    }

    /**
     * @return the maxScale
     */
    public double getMaxScale() {
        return maxScale;
    }

    /**
     * @param maxScale the maxScale to set
     */
    public void setMaxScale(double maxScale) {
        this.maxScale = maxScale;
    }


}
