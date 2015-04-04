package code.sound;

import java.util.Random;

public class MusicPlayer{
	private String[] music = {"Själv.wav", "Beast Mode.wav", "Bipolar Bear.wav", "Catnip.wav", "Boss Ass Bits.wav", "Deambulate.wav", "Duty Cycle GB.wav"};
	private String currentSong;
	private Random r;
	private Music currentMusic;
	private String directory = "/music/";
	
	public MusicPlayer(){
		r = new Random();
		int i = r.nextInt(music.length);
		currentSong = music[i];
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
