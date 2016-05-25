package code.engine;

public class GameSystem {
	protected SystemType type;

	public GameSystem(SystemType type){
		this.type = type;
	}

	public void update(){
		
	}
	public SystemType getType(){
		return type;
	}

}
