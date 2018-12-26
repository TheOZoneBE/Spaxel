package code.graphics;

import java.util.*;

/**
 * Created by theod on 15/10/2016.
 */
public class MasterBuffer {
    private EnumMap<RenderLayer, Map<Integer, List<RenderData>>> layers;

    public MasterBuffer(Map<String, Spritesheet> spritesheets){
        //data = new HashMap<>();
        layers = new EnumMap<>(RenderLayer.class);
        for (RenderLayer l: RenderLayer.values()){
            Map<Integer, List<RenderData>> data = new HashMap<>();
            for (Spritesheet sheet: spritesheets.values()){
                data.put(sheet.getId(), new ArrayList<>());
            }
            data.put(0, new ArrayList<>());

            layers.put(l, data);
        }
    }

    public void addNewSprite(RenderLayer layer, RenderData rdata){
        layers.get(layer).get(rdata.getSpriteSheetID()).add(rdata);
    }

    public void clear(){
        for(Map<Integer, List<RenderData>> layer: layers.values()){
            for(List<RenderData> datalist: layer.values()){
                datalist.clear();
            }
        }

    }

    public Map<Integer, List<RenderData>> getData(RenderLayer layer){
        return layers.get(layer);
    }


}
