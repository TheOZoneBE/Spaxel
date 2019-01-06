package code.graphics.buffer;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import code.engine.Resources;
import code.graphics.texture.PackedTexture;

/**
 * Buffers all render jobs
 * 
 * Created by theod on 15/10/2016.
 */
public class MasterBuffer {
    private EnumMap<RenderLayer, Map<Integer, List<RenderData>>> layers;

    public MasterBuffer() {
        layers = new EnumMap<>(RenderLayer.class);
        for (RenderLayer l : RenderLayer.values()) {
            Map<Integer, List<RenderData>> data = new HashMap<>();
            data.put(0, new ArrayList<>());
            data.put(((PackedTexture) Resources.get().getRenderables().get("packed")).getID(),
                    new ArrayList<>());

            layers.put(l, data);
        }
    }

    public void addNewSprite(RenderLayer layer, RenderData rdata) {
        layers.get(layer).get(rdata.getSpriteSheetID()).add(rdata);
    }

    public void clear() {
        for (Map<Integer, List<RenderData>> layer : layers.values()) {
            for (List<RenderData> datalist : layer.values()) {
                datalist.clear();
            }
        }

    }

    public Map<Integer, List<RenderData>> getData(RenderLayer layer) {
        return layers.get(layer);
    }

}