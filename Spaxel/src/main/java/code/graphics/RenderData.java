
package code.graphics;

/**
 * Created by theod on 22/10/2016.
 */

public class RenderData {
    private float[] trSc;
    private float[] sinCos;
    private float[] texOffset;
    private int spriteSheetID;

    public RenderData(){
        trSc = new float[4];
        sinCos = new float[4];
        texOffset = new float[4];
        sinCos[2] = 1;
    }

    public RenderData(int spriteSheetID, float[] trSc, float[] sinCos, float[] texOffset){
        this.trSc = trSc;
        this.sinCos = sinCos;
        this.texOffset = texOffset;
        this.spriteSheetID = spriteSheetID;
    }

    public float[] getTrSc(){
        return trSc;
    }

    public float[] getSinCos(){
        return sinCos;
    }

    public float[] getTexOffset(){
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
}
