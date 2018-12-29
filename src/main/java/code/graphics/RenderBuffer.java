package code.graphics;

import code.util.BufferUtils;

import java.nio.FloatBuffer;
import java.util.List;

public class RenderBuffer {
    private static final int RENDERDATA_ELEMENTS = 4;

    private FloatBuffer trscBuffer;
    private FloatBuffer sinCosBuffer;
    private FloatBuffer texOffsetBuffer;

    private int size;

    public RenderBuffer(List<RenderData> rdata) {
        size = rdata.size();
        trscBuffer = BufferUtils.allocateFloatBuffer(size * RENDERDATA_ELEMENTS);
        sinCosBuffer = BufferUtils.allocateFloatBuffer(size * RENDERDATA_ELEMENTS);
        texOffsetBuffer = BufferUtils.allocateFloatBuffer(size * RENDERDATA_ELEMENTS);
        for (RenderData r : rdata) {
            trscBuffer.put(r.getTrSc());
            sinCosBuffer.put(r.getSinCos());
            texOffsetBuffer.put(r.getTexOffset());
        }
        trscBuffer.flip();
        sinCosBuffer.flip();
        texOffsetBuffer.flip();

    }

    public FloatBuffer getTrscBuffer() {
        return trscBuffer;
    }

    public FloatBuffer getSinCosBuffer() {
        return sinCosBuffer;
    }

    public FloatBuffer getTexOffsetBuffer() {
        return texOffsetBuffer;
    }

    public int size() {
        return size;
    }

}
