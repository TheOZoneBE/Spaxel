package code.collision;

import code.math.Matrix;
import code.math.Vector;

public class HitPoint {
	private Vector vector;

	public HitPoint() {
		vector = new Vector(3);
	}
	
	public HitPoint(Vector vector){
		this.vector = vector;
	}
	
	public void updateVector(Vector vector){
		this.vector = vector;
	}
	
	public Vector getVector(){
		return vector;
	}
	
	public HitPoint update(Matrix updateMatrix){
		HitPoint updated = new HitPoint();
		Vector v = updateMatrix.multiplicate(vector);
		updated.updateVector(v);
		return updated;
	}
	
}
