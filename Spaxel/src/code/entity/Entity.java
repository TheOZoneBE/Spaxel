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
import code.math.Vector;

public class Entity {
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
	/*
	 * collision detection by the principle of SAT
	 */
	public boolean collision(Entity e){
		List<Axis> normals = new ArrayList<>();
		//getting hitshapes
		HitShape a = updHitShape;
		HitShape b = e.getUpdHitShape();
		//getting hitpoints
		List<HitPoint> hitpointsA = a.getHitPoints();
		List<HitPoint> hitpointsB = b.getHitPoints();
		//sizes of lists
		int aSize = hitpointsA.size();
		int bSize = hitpointsB.size();
		
		//getting normals of first hitshape
		if(aSize > 1){
			for (int i= 0; i < aSize; i++){
				// getting vectors for normal
				Vector v1 = hitpointsA.get(i).getVector();
				Vector v2 = hitpointsA.get((i + 1) % aSize).getVector();
				//build normal
				Axis normal = new Axis();
				normal.initializeNormal(v1, v2);
				normals.add(normal);
			}
		}		
		//getting normals for second hitshape
		if(bSize > 1){
			for (int i= 0; i < bSize; i++){
				// getting vectors for normal
				Vector v1 = hitpointsB.get(i).getVector();
				Vector v2 = hitpointsB.get((i + 1) % bSize).getVector();
				//build normal
				Axis normal = new Axis();
				normal.initializeNormal(v1, v2);
				normals.add(normal);
			}
		}
		
		//end of prep: now we have to check the projection of each normal
		for (Axis ax: normals){
			Projection p1 = a.project(ax);
			Projection p2 = b.project(ax);
			if (!p1.overlap(p2)){
				return false;
			}
		}
		return true;
	}
}
