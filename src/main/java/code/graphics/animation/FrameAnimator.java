package code.graphics.animation;

import code.engine.Resources;
import code.graphics.RenderData;
import code.graphics.SpriteData;

public class FrameAnimator extends Animator {
    private String spriteBase;
    private int numFrames;

    public FrameAnimator() {
        super(AnimatorType.FRAME);
    }

    public void animate(double percentage, RenderData data) {
        SpriteData frame;
        if (numFrames != 1) {
            int frameNumber = (int) Math.round(percentage * (numFrames - 1));
            frame = Resources.get().getSpriteAtlas().get(spriteBase + "_" + frameNumber);
        } else {
            frame = Resources.get().getSpriteAtlas().get(spriteBase);
        }

        data.setSprite(frame);
    }

    /**
     * @return the spriteBase
     */
    public String getSpriteBase() {
        return spriteBase;
    }

    /**
     * @param spriteBase the spriteBase to set
     */
    public void setSpriteBase(String spriteBase) {
        this.spriteBase = spriteBase;
    }

    /**
     * @return the numFrames
     */
    public int getNumFrames() {
        return numFrames;
    }

    /**
     * @param numFrames the numFrames to set
     */
    public void setNumFrames(int numFrames) {
        this.numFrames = numFrames;
    }
}
