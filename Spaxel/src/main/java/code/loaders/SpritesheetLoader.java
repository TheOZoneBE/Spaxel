package code.loaders;

import code.graphics.Spritesheet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by theo on 28/05/17.
 */
public class SpritesheetLoader extends Loader {

    public Map<String, Spritesheet> loadSpritesheets(String path){
        try {
            loadFile(path);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Spritesheet> spritesheetMap = mapper.readValue(file, new TypeReference<Map<String, Spritesheet>>(){});
            for (Spritesheet s: spritesheetMap.values()){
                s.load();
            }
            return spritesheetMap;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
