package code.engine;

public class System {
	SystemType type;
	Engine engine;
	
	public System(Engine engine){
		this.engine = engine;
	}
	
	public void update(){
		
	}
	public SystemType getType(){
		return type;
	}

}
