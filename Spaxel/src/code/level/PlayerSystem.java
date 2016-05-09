package code.level;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
import code.projectiles.Projectile;
import code.input.Keyboard;
import code.input.Mouse;
import code.inventory.ToggleItem;

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

		if (mouse.mouse1){
			for (Entity e : entities.getEntities(EntityType.MOUSE1ITEM)){
				ToggleItem i = (ToggleItem) e;
				Projectile p = i.activate(player.getX(), player.getY(), player.getRot());
				if (p != null){
					entities.addEntity(EntityType.PROJECTILE, p);
				}
			}
			
		}
		if (mouse.mouse3){
			for (Entity e : entities.getEntities(EntityType.MOUSE3ITEM)){
				ToggleItem i = (ToggleItem) e;
				Projectile p = i.activate(player.getX(), player.getY(), player.getRot());
				if (p != null){
					entities.addEntity(EntityType.PROJECTILE, p);
				}
			}
		}
	}

}
