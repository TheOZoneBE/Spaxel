package code.entity;


import java.util.ArrayList;
import java.util.List;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.graphics.Render;
import code.math.Axis;
import code.math.Matrix;
import code.math.MatrixMaker;
import code.math.Projection;
import code.math.VectorD;

public class Entity {
	protected double x;
	protected double y;
	protected double rot;
	private HitShape oriHitShape;
	private HitShape updHitShape;
	
	public Entity(double x, double y, double rot){
		this.x = x;
		this.y = y;
		this.rot = rot;
	}
		
	
	public void render(int xPos, int yPos, Render render){
		
	}
	public void update(){	
		if(oriHitShape != null){
			Matrix updateMatrix = MatrixMaker.getTransRotMatrix(x, y, rot);
			updHitShape = oriHitShape.update(updateMatrix);
		}		
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
	
	public void setHitShape(HitShape hitShape){
		oriHitShape = hitShape;
	}
	
	public HitShape getUpdHitShape(){
		return updHitShape;
	}
	
	public HitShape getOriHitShape(){
		return oriHitShape;
	}
	
	public boolean collision(Entity e){
		return updHitShape.collision(e.getUpdHitShape());
	}
}
