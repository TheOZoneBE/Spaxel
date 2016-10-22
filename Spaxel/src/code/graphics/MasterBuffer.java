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
    //private Map<Integer, RenderBuffer> buffers;

    private Map<Integer, List<RenderData>> data;

    public MasterBuffer(Map<String, Spritesheet> spritesheets){
        //buffers = new HashMap<>();
        data = new HashMap<>();
        for (Spritesheet sheet: spritesheets.values()){
            //buffers.put(sheet.getId(), new RenderBuffer());
            data.put(sheet.getId(), new ArrayList<>());
        }
        //buffers.put(0, new RenderBuffer());
        data.put(0, new ArrayList<>());
    }

    public void addNewSprite(int spritesheetID, FloatBuffer trsc, FloatBuffer sinCos, FloatBuffer texOffset){
        //RenderBuffer action = buffers.get(spritesheetID);
        //action.addTrsc(trsc);
        //action.addSinCos(sinCos);
        //action.addTexOffset(texOffset);
    }

    public void addNewSprite(int spritesheetID, RenderData rdata){
        data.get(spritesheetID).add(rdata);
    }

    public void clear(){
        /*for(RenderBuffer buffer: buffers.values()){
            buffer.clear();
        }*/
        for(List<RenderData> datalist: data.values()){
            datalist.clear();
        }
    }

    /*public Map<Integer, RenderBuffer> getBuffers(){
        return buffers;
    }
    */
    public Map<Integer, List<RenderData>> getData(){
        return data;
    }


}
