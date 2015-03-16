package code.inventory;

import code.graphics.Sprite;

public class ToggleItem extends Item{
	private int cooldown;
	private int cd = 0;

	public ToggleItem(Sprite sprite, int cooldown) {
		super(sprite);
		this.cooldown = cooldown;
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		reduceCD();
	}
	public void reduceCD(){
		if (cd != 0) cd--;
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

}
