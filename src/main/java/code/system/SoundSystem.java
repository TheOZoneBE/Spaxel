package code.system;

import code.engine.Engine;
import code.engine.SystemType;
import code.sound.Music;
import code.engine.Resources;

public class SoundSystem extends GameSystem {
	private Music currentMusic;

	public SoundSystem() {
		super(SystemType.SOUND);
	}

	public void update() {
		if (currentMusic == null || !currentMusic.isActive()) {
			nextSong();
		}
	}

	public void nextSong() {
		if (currentMusic != null) {
			currentMusic.stop();
		}
		if (Engine.get().getEngineState() != Engine.EngineState.MENU) {
			currentMusic = Resources.get().getMusicList().getRandomSong();
			play();
		} else {
			currentMusic = Resources.get().getMusicList().getSong("Intro");
			play();
		}

	}

	public void play() {
		currentMusic.play();
	}

	public void stop() {
		currentMusic.close();
	}

}
