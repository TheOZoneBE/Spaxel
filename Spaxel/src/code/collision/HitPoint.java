package code.collision;

import code.graphics.Render;
import code.math.Matrix;
import code.math.VectorD;

public class HitPoint {
	private VectorD vector;

	public HitPoint() {
		vector = new VectorD(3);
	}

	public HitPoint(VectorD vector) {
		this.vector = vector;
	}

	public void updateVector(VectorD vector) {
		this.vector = vector;
	}

	public VectorD getVector() {
		return vector;
	}

	public HitPoint update(Matrix updateMatrix) {
		HitPoint updated = new HitPoint();
		VectorD v = updateMatrix.multiplicate(vector);
		updated.updateVector(v);
		return updated;
	}

	public void render(int xOffset, int yOffset, Render render) {
		vector.render(xOffset, yOffset, render);
	}
	
	public void print(){
		vector.print();
	}
}
