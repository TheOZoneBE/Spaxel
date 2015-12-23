package code.level;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
import code.entity.Projectile;
import code.input.Keyboard;
import code.input.Mouse;
import code.inventory.Item;
import code.inventory.Weapon;

public class PlayerSystem extends GameSystem{

	public PlayerSystem(Engine engine) {
		super(engine);
		type = SystemType.PLAYER;
	}
	
	public void update(){
		Mouse mouse = engine.getMouse();
		Keyboard keys = engine.getKeyboard();
		EntityStream entities = engine.getEntityStream();
		Player player = (Player)entities.getEntities(EntityType.PLAYER).get(0);
		int mouseX = mouse.getX();
		int mouseY = mouse.getY();
		player.update(keys, mouseX, mouseY);
		//todo get mousebutton input and call methods to do things with that
		if (mouse.mouse1){
			for (Entity e : entities.getEntities(EntityType.ITEM)){
				Weapon i = (Weapon)e;
				Projectile p = i.getProjectile(player.getX(), player.getY(), player.getRot());
				if (p != null){
					entities.addEntity(EntityType.PROJECTILE, p);
				}
			}
			
		}
		if (mouse.mouse2){
			
		}
	}

}
