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
	protected UIBar cooldownBar;
	
	public Item(EntityType type, Sprite sprite, Sprite bar){
		super();
		this.type = type;
		stacks = 0;
		this.sprite = sprite;
		cooldownBar = new UIBar(0, 0, 48, Math.PI/2, bar);
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
