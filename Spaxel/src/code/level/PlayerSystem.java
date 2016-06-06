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
import code.inventory.ProjectileItem;

import java.util.List;

public class PlayerSystem extends GameSystem{

	public PlayerSystem() {
		super(SystemType.PLAYER);
	}
	
	public void update(){
		Mouse mouse = Engine.getEngine().getMouse();
		Keyboard keys = Engine.getEngine().getKeyboard();
		EntityStream entities = Engine.getEngine().getEntityStream();
		Player player = (Player)entities.getEntities(EntityType.PLAYER).get(0);
		int mouseX = mouse.getX();
		int mouseY = mouse.getY();
		player.update(keys, mouseX, mouseY);

		if (mouse.mouse1){
			List<Entity> mouse1 = entities.getEntities(EntityType.MOUSE1ITEM);
			for (Entity e : mouse1){
				ProjectileItem i = (ProjectileItem) e;
				Projectile p = i.activate(player.getX(), player.getY(), player.getRot());
				if (p != null){
					entities.addEntity(EntityType.PLAYER_PROJECTILE, p);
				}

			}
			Engine.getEngine().getEntityStream().releaseLock(EntityType.MOUSE1ITEM);
			
		}
		if (mouse.mouse3){
			List<Entity> mouse3 = entities.getEntities(EntityType.MOUSE3ITEM);
			for (Entity e : mouse3){
				ProjectileItem i = (ProjectileItem) e;
				Projectile p = i.activate(player.getX(), player.getY(), player.getRot());
				if (p != null){
					entities.addEntity(EntityType.PLAYER_PROJECTILE, p);
				}

			}
			Engine.getEngine().getEntityStream().releaseLock(EntityType.MOUSE3ITEM);
		}
		Engine.getEngine().getEntityStream().releaseLock(EntityType.PLAYER);
	}

}
