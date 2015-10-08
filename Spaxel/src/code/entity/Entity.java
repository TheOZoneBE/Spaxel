package code.entity;

import code.collision.HitShape;
import code.graphics.Render;
import code.math.Matrix;
import code.math.MatrixMaker;

public abstract class Entity {
	private double x;
	private double y;
	protected double rot;
	private HitShape oriHitShape;
	private HitShape updHitShape;
	
	public Entity(double x, double y, double rot){
		this.x = x;
		this.y = y;
		this.rot = rot;
	}
		
	
	public abstract void render(int xPos, int yPos, Render render);
	public void update(){	
		Matrix updateMatrix = MatrixMaker.getTransRotMatrix(x, y, rot);
		updHitShape = oriHitShape.update(updateMatrix);
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
	}
}
