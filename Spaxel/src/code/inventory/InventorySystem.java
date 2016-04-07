package code.inventory;

import java.util.ArrayList;
import java.util.List;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.GameSystem;
import code.engine.SystemType;
import code.entity.Projectile;
import code.factories.LaserFactory;
import code.graphics.Sprite;
import code.graphics.Spritesheet;

public class InventorySystem extends GameSystem{
	private List<Weapon> primWeap;//move to entitystream
	private List<Weapon> secWeap;//move to entitystream
	private List<Item> shipItems;//move to entitystream
	private Spritesheet sheet;//this has to go away
	private Sprite sprite;//this also
	
	public InventorySystem(Engine engine){
		super(engine);
		type = SystemType.INVENTORY;
		primWeap = new ArrayList<Weapon>();
		secWeap = new ArrayList<Weapon>();
		shipItems = new ArrayList<Item>();
		sheet = new Spritesheet(32, 32, "/spritesheets/projectiles.png");
		sprite = new Sprite(8, 8, 0, 0, 2, sheet);
		primWeap.add(new Weapon(0,0,sprite, 5, new LaserFactory(sprite, 5, 100, 15.0)));
		//temporary to get something shooting again
		engine.getEntityStream().addEntity(EntityType.ITEM, primWeap.get(0));
	}
	
	public void update(){
		for (Weapon w: primWeap){
			w.update();
		}
		for (Weapon w: secWeap){
			w.update();
		}
		for (Item i: shipItems){
			i.update();
		}
	}

}
