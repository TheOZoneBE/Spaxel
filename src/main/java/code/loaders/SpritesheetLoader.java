package code.loaders;

import code.graphics.Spritesheet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The SpritesheetLoader is responsible of loading the Spritesheets
 * 
 * Created by theo on 28/05/17.
 */
public class SpritesheetLoader extends Loader {
    private static final Logger LOGGER = Logger.getLogger(SpritesheetLoader.class.getName());

    /**
     * Load the spritesheets specified in the given file
     * 
     * @param path path to the file that contains the properties for all spritesheets
     * 
     * @return a map from spritesheetname to spritesheet object
     */
    public Map<String, Spritesheet> loadSpritesheets(String path) {
        try {
            loadFile(path);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Spritesheet> spritesheetMap =
                    mapper.readValue(file, new TypeReference<Map<String, Spritesheet>>() {
                    });
            for (Spritesheet s : spritesheetMap.values()) {
                s.load();
            }
            return spritesheetMap;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

}
