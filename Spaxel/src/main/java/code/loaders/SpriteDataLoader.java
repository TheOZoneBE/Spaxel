package code.loaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import code.graphics.SpriteData;

public class SpriteDataLoader extends Loader{
	
	public Map<String, SpriteData> loadSpriteDatas(String[] sprites){
		try {
			Map<String, SpriteData> spriteMap = new HashMap<>();
			for (String s: sprites){
				loadFile(s);
				ObjectMapper mapper = new ObjectMapper();
				spriteMap.putAll(mapper.readValue(file, new TypeReference<Map<String, SpriteData>>(){}));
			}

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
