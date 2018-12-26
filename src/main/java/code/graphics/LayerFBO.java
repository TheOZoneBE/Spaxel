package code.graphics;

import java.util.EnumMap;

/**
 * Created by theod on 18-9-2017.
 */
public class LayerFBO {
    private EnumMap<RenderLayer, FBO> fbos;

    public LayerFBO(){
        fbos = new EnumMap<>(RenderLayer.class);
        for (RenderLayer layer: RenderLayer.values()){
            fbos.put(layer, new FBO());
        }
    }

    public FBO getFbo(RenderLayer layer){
        return fbos.get(layer);
    }
}
