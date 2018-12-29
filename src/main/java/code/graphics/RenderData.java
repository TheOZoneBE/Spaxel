
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

    private int spriteSheetID;

    public RenderData() {
        trSc = new float[ATTRIB_DIM];
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

    public void setScale(VectorD scale) {
        trSc[SCALE_OFFSET] = (float) scale.getValue(0);
        trSc[SCALE_OFFSET + 1] = (float) scale.getValue(1);
    }

    public void setRot(double rot) {
        sinCos[0] = (float) Math.sin(rot);
        sinCos[1] = (float) Math.cos(rot);
    }

    public void setAlpha(double alpha) {
        sinCos[ALPHA_INDEX] = (float) alpha;
    }

    public void setColor(int color) {
        sinCos[COLOR_INDEX] = color;
    }
}
