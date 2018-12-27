package code.math;

public class Axis {
	private static final int AXIS_DIM = 2;

	private VectorF dirVec;

	public Axis() {
		dirVec = new VectorF(AXIS_DIM);
	}

	public void initializeAxis(VectorF vec1, VectorF vec2) {
		dirVec = vec1.diff(vec2);
	}

	public void initializeNormal(VectorF vec1, VectorF vec2) {
		dirVec = vec1.diff(vec2).normal();
	}

	public VectorF project(VectorF vec) {
		float mul = dirVec.dotProduct(vec) / dirVec.dotProduct(dirVec);
		return dirVec.multiplicate(mul);
	}

	public void print() {
		dirVec.print();
	}

}
