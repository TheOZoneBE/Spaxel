package code.graphics;

import code.util.BufferUtils;

import java.nio.FloatBuffer;
import java.util.*;

public class RenderBuffer {

    private FloatBuffer trscBuffer;
    private FloatBuffer sinCosBuffer;
    private FloatBuffer texOffsetBuffer;

    private int size;


    public RenderBuffer(List<RenderData> rdata) {
        size = rdata.size();
        trscBuffer = BufferUtils.allocateFloatBuffer(size);
        sinCosBuffer = BufferUtils.allocateFloatBuffer(size);
        texOffsetBuffer = BufferUtils.allocateFloatBuffer(size);
        for(RenderData r: rdata){
            trscBuffer.put(r.getTrSc());
            sinCosBuffer.put(r.getSinCos());
            texOffsetBuffer.put(r.getTexOffset());
        }
        trscBuffer.flip();
        sinCosBuffer.flip();
        texOffsetBuffer.flip();

    }

    public FloatBuffer getTrscBuffer(){
        return trscBuffer;
    }

    public FloatBuffer getSinCosBuffer(){
        return sinCosBuffer;
    }

    public FloatBuffer getTexOffsetBuffer(){
        return texOffsetBuffer;
    }


    public int size(){
        return size;
    }


}
