package code.graphics;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class RenderBuffer {

    private List<FloatBuffer> trscBuffer;
    private List<FloatBuffer> sinCosBuffer;
    private List<FloatBuffer> texOffsetBuffer;

    public RenderBuffer() {
        trscBuffer = new ArrayList<>();
        sinCosBuffer = new ArrayList<>();
        texOffsetBuffer = new ArrayList<>();
    }

    public void addTrsc(FloatBuffer trsc){
        trscBuffer.add(trsc);
    }

    public void addSinCos(FloatBuffer sinCos){
        sinCosBuffer.add(sinCos);
    }

    public void addTexOffset(FloatBuffer texOffset){
        texOffsetBuffer.add(texOffset);
    }

    public List<FloatBuffer> getTrscBuffer(){
        return trscBuffer;
    }

    public List<FloatBuffer> getSinCosBuffer(){
        return sinCosBuffer;
    }

    public List<FloatBuffer> getTexOffsetBuffer(){
        return texOffsetBuffer;
    }

    public void clear() {
        trscBuffer.clear();
        sinCosBuffer.clear();
        texOffsetBuffer.clear();
    }

    public int size(){
        return trscBuffer.size();
    }
}
