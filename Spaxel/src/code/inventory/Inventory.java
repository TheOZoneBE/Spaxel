package code.inventory;

import java.util.ArrayList;
import java.util.List;

import code.entity.Projectile;
import code.graphics.Sprite;
import code.graphics.Spritesheet;

public class Inventory {
	private List<Weapon> primWeap;
	private List<Weapon> secWeap;
	private List<Item> shipItems;
	private Spritesheet sheet;
	private Sprite sprite;
	
	public Inventory(){
		primWeap = new ArrayList<Weapon>();
		secWeap = new ArrayList<Weapon>();
		shipItems = new ArrayList<Item>();
		sheet = new Spritesheet(32, 32, "/spritesheets/projectiles.png");
		sprite = new Sprite(8, 8, 0, 0, 2, sheet);
		primWeap.add(new Weapon(sprite, 10, sprite, 200, 25.0));
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
	
	public List<Projectile> primaryWeapon(int x, int y, double rot){
		List<Projectile> projs = new ArrayList<Projectile>();
		for (Weapon w: primWeap){
			Projectile proj = w.getProjectile(x, y, rot);
			if (proj != null) projs.add(proj);
		}
		return projs;
	}

}
