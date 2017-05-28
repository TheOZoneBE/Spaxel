package code.system;

import java.util.List;
import java.util.Random;

import code.engine.Engine;
import code.engine.SystemType;
import code.sound.Music;
import code.sound.Sound;

public class SoundSystem extends GameSystem{
	private Random r;
	private Music currentMusic;
	
	public SoundSystem(){
		super(SystemType.SOUND);
		r = new Random();
	}
	
	public void update(){
		/*
		if(!currentMusic.isActive()){
			nextSong();
		}*/
	}
	
	public void nextSong(){
		List<Music> sounds = Engine.getEngine().getMusicList();
		int i = r.nextInt(sounds.size());
		currentMusic = (Music)sounds.get(i);
		play();
	}
	
	public void play(){
		currentMusic.play();
	}
	
	public void stop(){
		currentMusic.close();
	}

}
