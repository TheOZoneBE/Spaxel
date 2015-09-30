package code.entity;

import code.collision.HitShape;
import code.graphics.Render;

public abstract class Entity {
	private double x;
	private double y;
	private HitShape hitShape;
	
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
	
	public boolean collision(Entity e){
		return false;
		// TODO make collision detection
	}
}
