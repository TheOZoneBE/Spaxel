package code.inventory;

import code.entity.Entity;
import code.graphics.Sprite;

public class Item extends Entity {
	private int stacks;
	private Sprite sprite;
	
	public Item(double x, double y, Sprite sprite){
		super(x, y);
		stacks = 0;
		this.sprite = sprite;
	}
	
	public void update(){
		
	}

}
