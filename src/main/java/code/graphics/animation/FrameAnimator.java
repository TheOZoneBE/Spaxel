package code.graphics.animation;

import code.engine.Resources;
import code.graphics.buffer.RenderJob;
import code.graphics.texture.Renderable;

/**
 * The FrameAnimator animates the current sprite of the animation
 */
public class FrameAnimator extends Animator {
    private String spriteBase;
    private int numFrames;

    /**
     * Create a new FrameAnimator
     */
    public FrameAnimator() {
        super(AnimatorType.FRAME);
    }

    public void animate(double percentage, RenderJob data) {
        Renderable frame;
        if (numFrames != 1) {
            int frameNumber = (int) Math.round(percentage * (numFrames - 1));
            frame = Resources.get().getRenderables().get(spriteBase + "_" + frameNumber);
        } else {
            frame = Resources.get().getRenderables().get(spriteBase);
        }

        data.setRenderable(frame);
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
