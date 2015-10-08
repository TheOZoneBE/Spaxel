package code.math;

public class MatrixMaker {

	public static Matrix getTransRotMatrix(double x, double y, double rot) {
		double sin = Math.sin(rot);
		double cos = Math.cos(rot);
		Matrix mat = new Matrix(3, 3, new double[] { cos, -sin, x, sin, cos, y, 0, 0, 1 });
		return mat;
	}

}
