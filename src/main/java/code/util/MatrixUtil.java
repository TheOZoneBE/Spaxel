package code.util;

import code.math.MatrixD;
import code.math.VectorD;

public final class MatrixUtil {
	private static final int MATRIX_DIM = 3;
	private static final int PROJECTION_DIM = 4;
	private static final int PROJECTION_NUMERATOR = 2;

	private MatrixUtil() {

	}

	public static MatrixD getTransformationMatrix(VectorD coord, double rot, VectorD scale) {
		return getRotationMatrix(rot).multiplicate(getScaleMatrix(scale)).sum(getTranslationMatrix(coord));
	}

	public static MatrixD getTransRotationMatrix(VectorD coord, double rot) {
		return getRotationMatrix(rot).sum(getTranslationMatrix(coord));
	}

	public static MatrixD getRotationMatrix(double rot) {
		double sin = Math.sin(rot);
		double cos = Math.cos(rot);
		return new MatrixD(MATRIX_DIM, MATRIX_DIM, new double[] { cos, sin, 0, -sin, cos, 0, 0, 0, 1 });
	}

	public static MatrixD getScaleMatrix(VectorD scale) {
		return new MatrixD(MATRIX_DIM, MATRIX_DIM,
				new double[] { scale.getValue(0), 0, 0, 0, scale.getValue(1), 0, 0, 0, 1 });
	}

	public static MatrixD getTranslationMatrix(VectorD coord) {
		return new MatrixD(MATRIX_DIM, MATRIX_DIM,
				new double[] { 0, 0, coord.getValue(0), 0, 0, coord.getValue(1), 0, 0, 0 });
	}

	public static MatrixD orthographic(double left, double right, double bottom, double top, double near, double far) {
		return new MatrixD(PROJECTION_DIM, PROJECTION_DIM,
				new double[] { PROJECTION_NUMERATOR / (right - left), 0, 0, -(right + left) / (right - left), 0,
						PROJECTION_NUMERATOR / (top - bottom), 0, -(top + bottom) / (top - bottom), 0, 0,
						-PROJECTION_NUMERATOR / (far - near), -(far + near) / (far - near), 0, 0, 0, 1 });
	}

}
