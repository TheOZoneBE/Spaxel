package code.collision;

import java.util.ArrayList;
import java.util.List;

import code.graphics.RenderBuffer;
import code.math.Axis;
import code.math.MatrixF;
import code.math.Projection;
import code.math.VectorF;


public class HitShape {
	private List<HitPoint> hitPoints;
	
	public HitShape(){
		hitPoints = new ArrayList<>();
	}
	
	public HitShape(HitPoint hitPoint){
		hitPoints = new ArrayList<>();
		hitPoints.add(hitPoint);
	}
	
	public void addHitPoint(HitPoint hitPoint){
		hitPoints.add(hitPoint);
	}
	
	public List<HitPoint> getHitPoints(){
		return hitPoints;
	}

	public void setHitPoints(List<HitPoint> hitPoints) {
		this.hitPoints = hitPoints;
	}

	public HitShape update(MatrixF updateMatrixF){
		HitShape updated = new HitShape();
		for (HitPoint h: hitPoints){
			updated.addHitPoint(h.update(updateMatrixF));
		}
		return updated;
	}
	
	public Projection project(Axis ax){
		Projection pro = new Projection();
		for (HitPoint h: hitPoints){
			pro.addVector(ax.project(h.getVector()));
		}
		return pro;
	}
	
	public void render(int xOffset, int yOffset, RenderBuffer render){
		for (HitPoint h: hitPoints){
			h.render(xOffset, yOffset, render);
		}
	}
	
	public void print(){
		for (HitPoint h: hitPoints){
			h.print();
			System.out.print(" | ");
		}
		System.out.println();
	}
	
	/*
	 * collision detection by the principle of SAT
	 */
	public boolean collision(HitShape hitshape){
		List<Axis> normals = new ArrayList<>();
		//getting hitpoints
		List<HitPoint> hitpointsA = this.getHitPoints();
		List<HitPoint> hitpointsB = hitshape.getHitPoints();
		//sizes of lists
		int aSize = hitpointsA.size();
		int bSize = hitpointsB.size();
		
		//getting normals of first hitshape
		if(aSize > 1){
			for (int i= 0; i < aSize; i++){
				// getting vectors for normal
				VectorF v1 = hitpointsA.get(i).getVector();
				VectorF v2 = hitpointsA.get((i + 1) % aSize).getVector();
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
				VectorF v1 = hitpointsB.get(i).getVector();
				VectorF v2 = hitpointsB.get((i + 1) % bSize).getVector();
				//build normal
				Axis normal = new Axis();
				normal.initializeNormal(v1, v2);
				normals.add(normal);
			}
		}
		
		//end of prep: now we have to check the projection of each normal
		for (Axis ax: normals){
			Projection p1 = this.project(ax);
			Projection p2 = hitshape.project(ax);
			if (!p1.overlap(p2)){
				return false;
			}
		}
		return true;
	}
	

}
