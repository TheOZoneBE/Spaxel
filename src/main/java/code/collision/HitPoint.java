package code.collision;

import code.graphics.RenderBuffer;
import code.math.MatrixD;
import code.math.VectorD;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HitPoint {
	private static final int HITPOINT_DIM = 3;
	private VectorD vector;

	public HitPoint() {
		vector = new VectorD(HITPOINT_DIM);
	}

	public HitPoint(VectorD vector) {
		this.vector = vector;
	}

	@JsonCreator
	public HitPoint(@JsonProperty("xOffset") double xOffset, @JsonProperty("yOffset") double yOffset) {
		this.vector = new VectorD(new double[] { xOffset, yOffset, 1 });
	}

	public void updateVector(VectorD vector) {
		this.vector = vector;
	}

	public VectorD getVector() {
		return vector;
	}

	public HitPoint update(MatrixD updateMatrixF) {
		HitPoint updated = new HitPoint();
		VectorD v = updateMatrixF.multiplicate(vector);
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
