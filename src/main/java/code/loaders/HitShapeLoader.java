package code.loaders;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import code.collision.HitShape;


public class HitShapeLoader extends Loader{
	
	public Map<String, HitShape> loadHitShapes(String path){
		try {
			super.loadFile(path);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(file, new TypeReference<Map<String, HitShape>>(){});
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
