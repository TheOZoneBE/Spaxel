package code.loaders;

import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import code.graphics.animation.Animation;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;

/**
 * The AnimationLoader is responsible for loading all the Animations
 */
public class AnimationLoader extends Loader {
    private static final Logger LOGGER = Logger.getLogger(HitShapeLoader.class.getName());

    /**
     * Load the animations specified at the given file
     * 
     * @param path the path to the file that contains the properties of the animations
     * 
     * @return map from animation name to Animation object
     */
    public static Map<String, Animation> loadAnimations(String path) {
        try {
            InputStream file = loadFileStatic(path);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<Map<String, Animation>>() {
            });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

}
