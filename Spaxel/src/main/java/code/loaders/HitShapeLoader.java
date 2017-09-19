package code.loaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.math.VectorF;


public class HitShapeLoader extends Loader{
	
	public Map<String, HitShape> loadHitShapes(String path){
		try {
			super.loadFile(path);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, HitShape> hitShapeMap = mapper.readValue(file, new TypeReference<Map<String, HitShape>>(){});
			return hitShapeMap;
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
