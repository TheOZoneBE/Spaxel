package code.system;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
import code.inventory.Item;
import code.input.Keyboard;
import code.input.MouseWrapper;
import code.inventory.ProjectileItem;

import java.util.Iterator;

public class ActorSystem extends GameSystem{

	public ActorSystem() {
		super(SystemType.ACTOR);
	}
	
	public void update(){
		MouseWrapper mouseWrapper = Engine.getEngine().getMouseWrapper();
		Keyboard keys = Engine.getEngine().getKeyboard();
		EntityStream entities = Engine.getEngine().getEntityStream();
		Player player = (Player)entities.getEntities(EntityType.PLAYER).get(0);
		int mouseX = mouseWrapper.getX();
		int mouseY = mouseWrapper.getY();
		player.update(keys, mouseX, mouseY);

		if (mouseWrapper.mouse1){
			Iterator<Item> mouse1 = player.getItemIterator(EntityType.MOUSE1ITEM);
			while (mouse1.hasNext()){
				Entity e = mouse1.next();
				ProjectileItem i = (ProjectileItem) e;
				i.activate(player.getX(), player.getY(), player.getRot());
			}
		}
		if (mouseWrapper.mouse2){
			Iterator<Item> mouse3 = player.getItemIterator(EntityType.MOUSE3ITEM);
			while (mouse3.hasNext()){
				Entity e = mouse3.next();
				ProjectileItem i = (ProjectileItem) e;
				i.activate(player.getX(), player.getY(), player.getRot());
			}
		}

		Iterator<Entity> enemies = Engine.getEngine().getEntityStream().getIterator(EntityType.ENEMY);
		while (enemies.hasNext()){
			Entity e = enemies.next();
			e.update();
		}
	}

}
