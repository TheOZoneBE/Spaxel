package code.system;

import code.engine.Engine;
import code.engine.EntityStream;
import code.engine.EntityType;
import code.engine.SystemType;
import code.entity.Entity;
import code.entity.Player;
import code.inventory.Item;
import code.projectiles.Projectile;
import code.input.Keyboard;
import code.input.Mouse;
import code.inventory.ProjectileItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActorSystem extends GameSystem{

	public ActorSystem() {
		super(SystemType.ACTOR);
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
			Iterator<Item> mouse1 = player.getItemIterator(EntityType.MOUSE1ITEM);
			while (mouse1.hasNext()){
				Entity e = mouse1.next();
				ProjectileItem i = (ProjectileItem) e;
				i.activate(player.getX(), player.getY(), player.getRot());
			}
		}
		if (mouse.mouse3){
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
