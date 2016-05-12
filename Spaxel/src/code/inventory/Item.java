package code.inventory;

import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.Sprite;

public class Item extends Entity {
	protected EntityType type;
	private int stacks;
	private Sprite sprite;
	
	public Item(EntityType type, Sprite sprite){
		super();
		this.type = type;
		stacks = 0;
		this.sprite = sprite;
	}
	
	public void update(){
		
	}

	public EntityType getType(){
		return type;
	}

}
