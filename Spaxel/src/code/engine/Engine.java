package code.engine;

import java.util.EnumMap;

import code.input.Keyboard;
import code.input.Mouse;

public class Engine {
	private Keyboard keys;
	private Mouse mouse;
	private EntityStream entities;
	private EnumMap<SystemType, System> systems;
	
	public Engine(){
		keys = new Keyboard();
		mouse = new Mouse();
		entities = new EntityStream();
		initialize();
	}
	
	public void initialize(){
		
	}
	
	public void addSystem(System system){
		systems.put(system.getType(), system);
	}
	
	public System getSystem(SystemType type){
		return systems.get(type);
	}

}
