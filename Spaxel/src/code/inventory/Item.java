package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.entity.Entity;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.ui.UIBar;

public class Item extends Entity {
	protected EntityType type;
	protected int stacks;
	protected Sprite sprite;
	protected UIBar cooldownBar;
	protected int cooldown;
	protected int cd;
	
	public Item(EntityType type, Sprite sprite, Sprite bar, int cooldown){
		super();
		this.type = type;
		stacks = 0;
		this.sprite = sprite;
		cooldownBar = new UIBar(0, 0, 48, Math.PI/2, bar);
		this.cooldown = cooldown;
		cd = 0;
	}

	public void reduceCD(){
		if (cd != 0){
			cd--;
		}
	}
	
	public void update(){
		
	}

	public EntityType getType(){
		return type;
	}

	public void render(int xPos, int yPos, RenderBuffer render){
		sprite.render(xPos, yPos, render);
	}

	public int getCooldown(){
		return cooldown;
	}

	public int getCD(){
		return cd;
	}

	public void setCD(int cd){
		this.cd = cd;
	}

	public Sprite getSprite(){
		return sprite;
	}

	public int getStacks(){
		return stacks;
	}

	public void setStacks(int stacks){
		this.stacks = stacks;
	}

}
