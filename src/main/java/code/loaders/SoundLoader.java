package code.loaders;

import java.io.IOException;
import java.util.Map;

import code.Game;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import code.sound.Music;

public class SoundLoader extends Loader {
	private static final double SOUND_LOAD_PERCENTAGE = 0.7;

	public Map<String, Music> loadSounds(String path) {
		try {
			super.loadFile(path);
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Music> music = mapper.readValue(file, new TypeReference<Map<String, Music>>() {
			});
			float count = music.size();
			float i = 0;
			for (Music m : music.values()) {
				m.initialize();
				i++;
				Game.game.loadingScreen.getProgress().setPercent((float) SOUND_LOAD_PERCENTAGE * i / count);
			}
			return music;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
