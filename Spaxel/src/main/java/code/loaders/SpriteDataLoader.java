package code.loaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import code.graphics.SpriteData;
import code.graphics.Spritesheet;

public class SpriteDataLoader extends Loader{
	
	public Map<String, SpriteData> loadSpriteDatas(String sprites){
		try {
			loadFile(sprites);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, SpriteData> spriteMap = mapper.readValue(file, new TypeReference<Map<String, SpriteData>>(){});
			for(SpriteData s: spriteMap.values()){
				s.initialize();
			}
			return spriteMap;
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
