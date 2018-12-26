package code.system;

import java.util.List;
import java.util.Random;

import code.engine.Engine;
import code.engine.SystemType;
import code.sound.Music;

public class SoundSystem extends GameSystem{
	private Random r;
	private Music currentMusic;
	
	public SoundSystem(){
		super(SystemType.SOUND);
		r = new Random();
	}
	
	public void update(){
		if(!currentMusic.isActive()){
			nextSong();
		}
	}
	
	public void nextSong(){
		if (currentMusic !=  null) {
			currentMusic.stop();
		}
		if (Engine.getEngine().getGameState() != Engine.GameState.MENU){
			currentMusic = Engine.getEngine().getMusicList().getRandomSong();
			play();
		}
		else {
			currentMusic = Engine.getEngine().getMusicList().getSong("Intro");
			play();
		}

	}
	
	public void play(){
		currentMusic.play();
	}
	
	public void stop(){
		currentMusic.close();
	}

}
