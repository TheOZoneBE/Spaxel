package code.level;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Player;
import code.input.Keyboard;
import code.input.Mouse;

public class PlayerSystem extends GameSystem{

	public PlayerSystem(Engine engine) {
		super(engine);
		type = SystemType.PLAYER;
	}
	
	public void update(){
		Mouse mouse = engine.getMouse();
		Keyboard keys = engine.getKeyboard();
		EntityStream entities = engine.getEntityStream();
		int mouseX = mouse.getX();
		int mouseY = mouse.getY();
		((Player)entities.getEntities(EntityType.PLAYER).get(0)).update(keys, mouseX, mouseY);
		//todo get mousebutton input and call methods to do things with that
	}

}
