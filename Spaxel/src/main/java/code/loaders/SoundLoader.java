package code.loaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import code.Game;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import code.sound.Music;
import code.sound.Sound;

public class SoundLoader extends Loader{
	
	public List<Music> loadSounds(String path) {
		try {
			super.loadFile(path);
			ObjectMapper mapper = new ObjectMapper();
			List<Music> music = mapper.readValue(file, new TypeReference<List<Music>>(){});
			float count = music.size();
			float i = 0;
			for (Music m: music){
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
