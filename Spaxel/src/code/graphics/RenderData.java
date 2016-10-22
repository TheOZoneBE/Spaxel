
package code.graphics;

/**
 * Created by theod on 22/10/2016.
 */
public class RenderData {
    private float[] trSc;
    private float[] sinCos;
    private float[] texOffset;

    public RenderData(float[] trSc, float[] sinCos, float[] texOffset){
        this.trSc = trSc;
        this.sinCos = sinCos;
        this.texOffset = texOffset;
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
}
