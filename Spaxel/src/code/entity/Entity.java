package code.entity;

import code.graphics.Render;

public abstract class Entity {
	private double x;
	private double y;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
		
	
	public abstract void render(int xPos, int yPos, Render render);
	public void update(){		
	}
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
}
