
package code.graphics;

import code.math.VectorD;

/**
 * Created by theod on 22/10/2016.
 */

public class RenderData {
    private static final int ATTRIB_DIM = 4;
    private static final int SCALE_OFFSET = 2;
    private static final int ALPHA_INDEX = 2;
    private static final int COLOR_INDEX = 3;
    private float[] texOffset;
    private float[] trSc;
    private float[] sinCos;
    private double rot;

    private int spriteSheetID;

    public RenderData() {
        trSc = new float[] {0.0F, 0.0F, 1.0F, 1.0F};
        sinCos = new float[ATTRIB_DIM];
        texOffset = new float[ATTRIB_DIM];
        sinCos[ALPHA_INDEX] = 1;
    }

    public float[] getTrSc() {
        return trSc;
    }

    public float[] getSinCos() {
        return sinCos;
    }

    public float[] getTexOffset() {
        return texOffset;
    }

    public void setTexOffset(float[] texOffset) {
        this.texOffset = texOffset;
    }

    public int getSpriteSheetID() {
        return spriteSheetID;
    }

    public void setSpriteSheetID(int spriteSheetID) {
        this.spriteSheetID = spriteSheetID;
    }

    public void applyTranslation(VectorD translation) {
        trSc[0] += (float) translation.getValue(0);
        trSc[1] += (float) translation.getValue(1);
    }

    public void applyXScale(double xScale) {
        trSc[SCALE_OFFSET] *= (float) xScale;
    }

    public void applyYScale(double yScale) {
        trSc[SCALE_OFFSET + 1] *= (float) yScale;
    }

    public void applyScale(VectorD scale) {
        applyXScale(scale.getValue(0));
        applyYScale(scale.getValue(1));
    }

    public void applyRot(double rotChange) {
        rot += rotChange;
        sinCos[0] = (float) Math.sin(rot);
        sinCos[1] = (float) Math.cos(rot);
    }

    public void applyAlpha(double alpha) {
        sinCos[ALPHA_INDEX] *= (float) alpha;
    }

    public void setColor(int color) {
        sinCos[COLOR_INDEX] = color;
    }

    public void setSprite(SpriteData sprite) {
        setTexOffset(sprite.getSpriteProperties());
        setSpriteSheetID(sprite.getSpritesheetID());
    }
}
