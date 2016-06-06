package code.sound;

import java.util.List;
import java.util.Random;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;

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
		List<Entity> sounds = Engine.getEngine().getEntityStream().getEntities(EntityType.SOUND);
		int i = r.nextInt(sounds.size());
		currentMusic = (Music)sounds.get(i);
		play();
		Engine.getEngine().getEntityStream().releaseLock(EntityType.SOUND);
	}
	
	public void play(){
		currentMusic.play();
	}
	
	public void stop(){
		currentMusic.close();
	}

}
