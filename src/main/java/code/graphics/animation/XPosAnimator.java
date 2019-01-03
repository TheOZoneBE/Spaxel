package code.graphics.animation;

import code.graphics.RenderData;

public class XPosAnimator extends Animator {
    private double maxPos;
    private double minPos;

    public XPosAnimator() {
        super(AnimatorType.X_POS);
    }

    public void animate(double percentage, RenderData data) {
        double pos = percentage * (maxPos - minPos);

        data.applyXTranslation(minPos + pos);
    }

    /**
     * @return the maxPos
     */
    public double getMaxPos() {
        return maxPos;
    }

    /**
     * @param maxPos the maxPos to set
     */
    public void setMaxPos(double maxPos) {
        this.maxPos = maxPos;
    }

    /**
     * @return the minPos
     */
    public double getMinPos() {
        return minPos;
    }

    /**
     * @param minPos the minPos to set
     */
    public void setMinPos(double minPos) {
        this.minPos = minPos;
    }
}
