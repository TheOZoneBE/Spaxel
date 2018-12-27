package code.math;

public final class MatrixMaker {
	private MatrixMaker() {

	}

	public static MatrixD getTransformationMatrix(VectorD coord, double rot, double xScale, double yScale) {
		double sin = Math.sin(rot);
		double cos = Math.cos(rot);
		MatrixD mat = new MatrixD(3, 3, new double[] { cos, sin, 0, -sin, cos, 0, 0, 0, 1 })
				.multiplicate(new MatrixD(3, 3, new double[] { xScale, 0, 0, 0, yScale, 0, 0, 0, 1 }));
		mat.setValue(0, 2, coord.getValue(0));
		mat.setValue(1, 2, coord.getValue(1));
		return mat;
	}

	public static MatrixD orthographic(double left, double right, double bottom, double top, double near, double far) {
		return new MatrixD(4, 4,
				new double[] { 2 / (right - left), 0, 0, -(right + left) / (right - left), 0, 2 / (top - bottom), 0,
						-(top + bottom) / (top - bottom), 0, 0, -2 / (far - near), -(far + near) / (far - near), 0, 0,
						0, 1 });
	}

}
