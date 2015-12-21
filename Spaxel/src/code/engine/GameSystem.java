package code.engine;

public class GameSystem {
	protected SystemType type;
	protected Engine engine;
	
	public GameSystem(Engine engine){
		this.engine = engine;
	}
	
	public void update(){
		
	}
	public SystemType getType(){
		return type;
	}

}
