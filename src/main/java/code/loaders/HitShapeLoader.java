package code.loaders;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import code.collision.HitShape;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HitShapeLoader extends Loader {
	private static final Logger LOGGER = Logger.getLogger(HitShapeLoader.class.getName());

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
