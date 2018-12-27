
package code.graphics;

import code.math.VectorD;

/**
 * Created by theod on 22/10/2016.
 */

public class RenderData {
    private float[] texOffset;
    private float[] trSc;
    private float[] sinCos;

    private int spriteSheetID;

    public RenderData() {
        trSc = new float[4];
        sinCos = new float[4];
        texOffset = new float[4];
        sinCos[2] = 1;
    }

    public RenderData(int spriteSheetID, float[] trSc, float[] sinCos, float[] texOffset) {
        this.trSc = trSc;
        this.sinCos = sinCos;
        this.texOffset = texOffset;
        this.spriteSheetID = spriteSheetID;
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

    public void setTrSc(float[] trSc) {
        this.trSc = trSc;
    }

    public void setSinCos(float[] sinCos) {
        this.sinCos = sinCos;
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

    public void setPos(VectorD pos) {
        trSc[0] = (float) pos.getValue(0);
        trSc[1] = (float) pos.getValue(1);
    }

    public void setXScale(double xScale) {
        trSc[2] = (float) xScale;
    }

    public void setYScale(double yScale) {
        trSc[3] = (float) yScale;
    }

    public void setRot(double rot) {
        sinCos[0] = (float) Math.sin(rot);
        sinCos[1] = (float) Math.cos(rot);
    }

    public void setAlpha(double alpha) {
        sinCos[2] = (float) alpha;
    }

    public void setColor(int color) {
        sinCos[3] = color;
    }
}
