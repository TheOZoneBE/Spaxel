package code.collision;

import java.util.ArrayList;
import java.util.List;

import code.math.Axis;
import code.math.Matrix;
import code.math.Projection;


public class HitShape {
	private List<HitPoint> hitPoints;
	
	public HitShape(){
		hitPoints = new ArrayList<>();
	}
	
	public void addHitPoint(HitPoint hitPoint){
		hitPoints.add(hitPoint);
	}
	
	public List<HitPoint> getHitPoints(){
		return hitPoints;
	}
	
	public HitShape update(Matrix updateMatrix){
		HitShape updated = new HitShape();
		for (HitPoint h: hitPoints){
			updated.addHitPoint(h.update(updateMatrix));
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
	
	

}
