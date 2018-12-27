package code.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private String path;
	private Clip clip;

	public Sound() {

	}

	public Sound(String path) {
		this.path = path;
	}

	public void initialize() {
		try {
			URL url = getClass().getResource(path);
			clip = AudioSystem.getClip();
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			clip.open(audio);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void play() {
		clip.start();
	}

	public void close() {
		clip.close();
	}

	public boolean isActive() {
		return clip.isActive();
	}

	public void stop() {
		clip.stop();
		clip.setFramePosition(0);
	}

}
