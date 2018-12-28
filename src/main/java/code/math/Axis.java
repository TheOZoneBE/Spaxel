package code.math;

public class Axis {
	private static final int AXIS_DIM = 2;

	private VectorD dirVec;

	public Axis() {
		dirVec = new VectorD(AXIS_DIM);
	}

	public void initializeAxis(VectorD vec1, VectorD vec2) {
		dirVec = vec1.diff(vec2);
	}

	public void initializeNormal(VectorD vec1, VectorD vec2) {
		dirVec = vec1.diff(vec2).normal();
	}

	public VectorD project(VectorD vec) {
		double mul = dirVec.dotProduct(vec) / dirVec.dotProduct(dirVec);
		return dirVec.multiplicate(mul);
	}

	public String toString() {
		return dirVec.toString();
	}

}
