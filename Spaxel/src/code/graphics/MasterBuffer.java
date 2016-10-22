package code.graphics;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by theod on 15/10/2016.
 */
public class MasterBuffer {
    private Map<Integer, List<RenderData>> data;

    public MasterBuffer(Map<String, Spritesheet> spritesheets){
        data = new HashMap<>();
        for (Spritesheet sheet: spritesheets.values()){
            data.put(sheet.getId(), new ArrayList<>());
        }
        data.put(0, new ArrayList<>());
    }

    public void addNewSprite(int spritesheetID, RenderData rdata){
        data.get(spritesheetID).add(rdata);
    }

    public void clear(){
        for(List<RenderData> datalist: data.values()){
            datalist.clear();
        }
    }

    public Map<Integer, List<RenderData>> getData(){
        return data;
    }


}
