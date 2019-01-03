package code.loaders;

import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import code.collision.HitShape;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The HitShapeLoader is responsible for loading all the HitShapes
 */
public class HitShapeLoader extends Loader {
	private static final Logger LOGGER = Logger.getLogger(HitShapeLoader.class.getName());

	/**
	 * Load the hitshapes specified at the given file
	 * 
	 * @param path the path to the file that contains the properties of the hitshapes
	 * 
	 * @return map from hitshape name to HitShape object
	 */
	public Map<String, HitShape> loadHitShapes(String path) {
		try {
			super.loadFile(path);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(file, new TypeReference<Map<String, HitShape>>() {
			});
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		return null;
	}

}
