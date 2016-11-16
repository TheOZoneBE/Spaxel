package code.collision;

import code.graphics.RenderBuffer;
import code.math.MatrixF;
import code.math.VectorF;

public class HitPoint {
	private VectorF vector;

	public HitPoint() {
		vector = new VectorF(3);
	}

	public HitPoint(VectorF vector) {
		this.vector = vector;
	}

	public void updateVector(VectorF vector) {
		this.vector = vector;
	}

	public VectorF getVector() {
		return vector;
	}

	public HitPoint update(MatrixF updateMatrixF) {
		HitPoint updated = new HitPoint();
		VectorF v = updateMatrixF.multiplicate(vector);
		updated.updateVector(v);
		return updated;
	}

	public void render(int xOffset, int yOffset, RenderBuffer render) {
		vector.render(xOffset, yOffset, render);
	}
	
	public void print(){
		vector.print();
	}
}
