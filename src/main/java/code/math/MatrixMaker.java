package code.math;

public final class MatrixMaker {
	private MatrixMaker() {

	}

	public static MatrixF getTransformationMatrix(VectorF coord, float rot, float xScale, float yScale) {
		float sin = (float) Math.sin(rot);
		float cos = (float) Math.cos(rot);
		MatrixF mat = new MatrixF(3, 3, new float[] { cos, sin, 0, -sin, cos, 0, 0, 0, 1 })
				.multiplicate(new MatrixF(3, 3, new float[] { xScale, 0, 0, 0, yScale, 0, 0, 0, 1 }));
		mat.setValue(0, 2, coord.getValue(0));
		mat.setValue(1, 2, coord.getValue(1));
		return mat;
	}

	public static MatrixF orthographic(float left, float right, float bottom, float top, float near, float far) {
		return new MatrixF(4, 4,
				new float[] { 2 / (right - left), 0, 0, -(right + left) / (right - left), 0, 2 / (top - bottom), 0,
						-(top + bottom) / (top - bottom), 0, 0, -2 / (far - near), -(far + near) / (far - near), 0, 0,
						0, 1 });
	}

}
