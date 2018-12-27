package code.collision;

import code.graphics.RenderBuffer;
import code.math.MatrixF;
import code.math.VectorF;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HitPoint {
	private static final int HITPOINT_DIM = 3;
	private VectorF vector;

	public HitPoint() {
		vector = new VectorF(HITPOINT_DIM);
	}

	public HitPoint(VectorF vector) {
		this.vector = vector;
	}

	@JsonCreator
	public HitPoint(@JsonProperty("xOffset") float xOffset, @JsonProperty("yOffset") float yOffset) {
		this.vector = new VectorF(new float[] { xOffset, yOffset, 1 });
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

	public void print() {
		vector.print();
	}
}
