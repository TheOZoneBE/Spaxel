package code.entity;

import code.graphics.Render;

public abstract class Entity {
	protected int x;
	protected int y;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
		
	public abstract void update();
	
	public abstract void render(int xPos, int yPos, Render render);
}
