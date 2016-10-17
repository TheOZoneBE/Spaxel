package code.graphics;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by theod on 15/10/2016.
 */
public class MasterBuffer {
    private Map<Integer, RenderBuffer> buffers;

    public MasterBuffer(Map<String, Spritesheet> spritesheets){
        buffers = new HashMap<>();
        for (Spritesheet sheet: spritesheets.values()){
            buffers.put(sheet.getId(), new RenderBuffer());
            buffers.put(0, new RenderBuffer());
        }
    }

    public void addNewSprite(int spritesheetID, FloatBuffer trsc, FloatBuffer sinCos, FloatBuffer texOffset){
        RenderBuffer action = buffers.get(spritesheetID);
        action.addTrsc(trsc);
        action.addSinCos(sinCos);
        action.addTexOffset(texOffset);
    }

    public void clear(){
        for(RenderBuffer buffer: buffers.values()){
            buffer.clear();
        }
    }

    public Map<Integer, RenderBuffer> getBuffers(){
        return buffers;
    }


}
