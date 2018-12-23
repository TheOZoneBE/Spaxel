package code.loaders;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import code.Game;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import code.sound.Music;

public class SoundLoader extends Loader{
	
	public Map<String, Music> loadSounds(String path) {
		try {
			super.loadFile(path);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Music> music = mapper.readValue(file, new TypeReference<Map<String, Music>>(){});
			float count = music.size();
			float i = 0;
			for (Music m: music.values()){
				m.initialize();
				i++;
				Game.game.loadingScreen.getProgress().setPercent(0.7f*i/count);
			}
			return music;
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

}
