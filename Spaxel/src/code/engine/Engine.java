package code.engine;

import java.util.EnumMap;

import code.input.Keyboard;
import code.input.Mouse;

public class Engine {
	private Keyboard keys;
	private Mouse mouse;
	private EntityStream entities;
	private EnumMap<SystemType, GameSystem> systems;
	
	public Engine(){
		keys = new Keyboard();
		mouse = new Mouse();
		entities = new EntityStream();
		systems = new EnumMap<>(SystemType.class);
		initialize();
	}
	
	public void initialize(){
		
	}
	
	public void addSystem(GameSystem system){
		systems.put(system.getType(), system);
	}
	
	public GameSystem getSystem(SystemType type){
		return systems.get(type);
	}
	
	public void update(){
		systems.get(SystemType.SOUND).update();
		systems.get(SystemType.INVENTORY).update();
	}
	
	public void render(){
		
	}

}
