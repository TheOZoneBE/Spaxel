package code.entity;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import code.collision.HitPoint;
import code.collision.HitShape;
import code.graphics.RenderBuffer;
import code.math.Axis;
import code.math.Matrix;
import code.math.MatrixMaker;
import code.math.Projection;
import code.math.VectorD;

public class Entity {
	protected double x;
	protected double y;
	protected double rot;
	protected HitShape oriHitShape;
	protected HitShape updHitShape;
	
	public Entity(double x, double y, double rot){
		this.x = x;
		this.y = y;
		this.rot = rot;
	}
	
	public Entity(double x, double y){
		this.x = x;
		this.y = y;
		rot = 0;
	}
	
	public Entity(){
		x = 0;
		y = 0;
		rot = 0;
	}
		
	
	public void render(int xPos, int yPos, RenderBuffer render){
		
	}
	
	public void render(Graphics g){
		
	}
	
	public void update(){	
		if(oriHitShape != null){
			updateHitShape();
		}		
	}
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getRot(){
		return rot;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setRot(double rot){
		this.rot = rot;
	}
	
	public void updateHitShape(){
		Matrix updateMatrix = MatrixMaker.getTransRotMatrix(x, y, rot);
		updHitShape = oriHitShape.update(updateMatrix);
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
