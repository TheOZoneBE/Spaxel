package code.sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import code.entity.Entity;

public class Sound extends Entity{
	
	private String path;
	private Clip clip;

	public Sound(){

	}

	public Sound(String path){
		this.path = path;
		life = -1;
	}

	public void initialize(){
		try {
			URL url = getClass().getResource(path);
			clip = AudioSystem.getClip();
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			clip.open(audio);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void play(){
		clip.start();
	}
	
	public void close(){
		clip.close();
	}
	
	public boolean isActive(){
		return clip.isActive();
	}

}