package code.sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private String path;
	private Clip clip;
	
	public Sound(String path){
		this.path = path;		
	}
	
	public void play(){
		try {
			URL url = getClass().getResource(path);
			clip = AudioSystem.getClip();
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			clip.open(audio);
			clip.start();
		}
		catch (Exception e){
			e.printStackTrace();			
		}
	}
	
	public void close(){
		clip.close();
	}

}
