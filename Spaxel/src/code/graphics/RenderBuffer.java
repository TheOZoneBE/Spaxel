package code.graphics;

import code.entity.Entity;
import code.util.BufferUtils;
import org.lwjgl.system.CallbackI;

import java.nio.FloatBuffer;
import java.util.*;

public class RenderBuffer {

    //private List<FloatBuffer> trscBuffer;
    //private List<FloatBuffer> sinCosBuffer;
    //private List<FloatBuffer> texOffsetBuffer;

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


        //trscBuffer = new ArrayList<>();
        //sinCosBuffer = new ArrayList<>();
        //texOffsetBuffer = new ArrayList<>();
    }

/*
    public void addTrsc(FloatBuffer trsc){
        trscBuffer.add(trsc);
    }

    public void addSinCos(FloatBuffer sinCos){
        sinCosBuffer.add(sinCos);
    }

    public void addTexOffset(FloatBuffer texOffset){
        texOffsetBuffer.add(texOffset);
    }*/

    public FloatBuffer getTrscBuffer(){
        return trscBuffer;
    }

    public FloatBuffer getSinCosBuffer(){
        return sinCosBuffer;
    }

    public FloatBuffer getTexOffsetBuffer(){
        return texOffsetBuffer;
    }


    public void clear() {
        trscBuffer.clear();
        sinCosBuffer.clear();
        texOffsetBuffer.clear();
    }

    public int size(){
        return size;
    }


}
