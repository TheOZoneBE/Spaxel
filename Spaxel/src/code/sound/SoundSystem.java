package code.sound;

import java.util.Random;

import code.engine.Engine;
import code.engine.GameSystem;
import code.engine.SystemType;

public class SoundSystem extends GameSystem{
	private String[] music = {"Själv.wav", "Beast Mode.wav", "Bipolar Bear.wav", "Catnip.wav", "Boss Ass Bits.wav", "Deambulate.wav", "Duty Cycle GB.wav"};
	private String currentSong;
	private Random r;
	private Music currentMusic;
	private String directory = "/music/";
	
	public SoundSystem(Engine engine){
		super(engine);
		type = SystemType.SOUND;
		r = new Random();
		int i = r.nextInt(music.length);
		currentSong = music[i];
		play();
	}
	
	public void update(){
		if(!currentMusic.isActive()){
			nextSong();
		}
	}
	
	public void nextSong(){
		int i = r.nextInt(music.length);
		currentSong = music[i];
		play();
	}
	
	public void play(){
		currentMusic = new Music(directory + currentSong);
		currentMusic.play();
	}
	
	public void stop(){
		currentMusic.close();
	}

}
