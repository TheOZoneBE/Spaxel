package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.graphics.MasterBuffer;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;
import code.ui.Label;
import code.ui.UIBar;
import code.ui.UIElement;

import java.awt.*;

public class Item extends UIElement {
	protected EntityType type;
	protected int stacks;
	protected SpriteData sprite;
	protected UIBar cooldownBar;
	protected Label stackCounter;
	protected int cooldown;
	protected int cd;
	protected SpriteData bar;
	protected String name;
	
	public Item(EntityType type, String name, SpriteData sprite, SpriteData bar, int cooldown){
		super(0,0,null);
		this.type = type;
		this.bar = bar;
		this.name = name;
		this.stacks = 0;
		this.sprite = sprite;
		cooldownBar = new UIBar(0, 0, 48, (float)Math.PI/2, bar);
		stackCounter = new Label(0,0,String.valueOf(stacks+ 1), 1);
		this.cooldown = cooldown;
		cd = 0;
		life = -1;
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

	public void render(int xPos, int yPos, MasterBuffer render){
		sprite.renderSprite(xPos, yPos, 2, 0, 1, false, render);
		stackCounter.render(xPos + 20, yPos + 20, render);
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

	public SpriteData getSprite(){
		return sprite;
	}

	public int getStacks(){
		System.out.println("returned " +  stacks + " stacks");
		return stacks;
	}

	public void setStacks(int s){
		System.out.println("set stacks to: " + s);
		stacks = s;
		stackCounter.setText(String.valueOf(stacks + 1));
	}

	public Item copy(){
		return null;
	}

	public boolean equals(Object o){
		if (o instanceof Item){
			return name.equals(((Item)o).name);
		}
		else {
			return false;
		}
	}

}
