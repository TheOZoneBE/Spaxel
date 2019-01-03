package code.graphics.animation;

import code.graphics.RenderData;

public class YScaleAnimator extends Animator {
    private double maxScale;

    public YScaleAnimator() {
        super(AnimatorType.Y_SCALE);
    }

    public void animate(double percentage, RenderData data) {
        double scale = percentage * maxScale;

        data.applyYScale(scale);
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
