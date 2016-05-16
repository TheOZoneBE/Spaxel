package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.ui.UIBar;

public class Item extends Entity {
	protected EntityType type;

	private int stacks;
	protected Sprite sprite;
	
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

	public void render(int xPos, int yPos, RenderBuffer render){
		sprite.render(xPos, yPos, render);

	}

}
