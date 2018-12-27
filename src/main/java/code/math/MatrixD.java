package code.math;

import code.util.BufferUtils;

import java.nio.FloatBuffer;

public class MatrixD {
	private int m;
	private int n;
	private double[] matrix;

	public MatrixD(int m, int n) {
		this.m = m;
		this.n = n;
		matrix = new double[m * n];
	}

	public MatrixD(int m, int n, double[] matrix) {
		this.m = m;
		this.n = n;
		this.matrix = matrix;
	}

	public void setValue(int x, int y, double value) {
		matrix[x * n + y] = value;
	}

	public double getValue(int x, int y) {
		return matrix[x * n + y];
	}

	public void setMatrix(double[] matrix) {
		this.matrix = matrix;
	}

	public double[] getMatrix() {
		return matrix;
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	public MatrixD multiplicate(MatrixD mat) {
		MatrixD sol = new MatrixD(m, mat.getN());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < mat.getN(); j++) {
				double sum = 0;
				for (int l = 0; l < n; l++) {
					sum += (matrix[i * n + l] * mat.getValue(l, j));
				}
				sol.setValue(i, j, sum);
			}
		}
		return sol;
	}

	public VectorD multiplicate(VectorD vec) {
		VectorD sol = new VectorD(n);
		for (int i = 0; i < m; i++) {
			double sum = 0;
			for (int l = 0; l < n; l++) {
				sum += (matrix[i * n + l] * vec.getValue(l));
			}
			sol.setValue(i, sum);
		}
		return sol;
	}

	public void print() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i * n + j] + " ");
			}
			System.out.println();
		}
	}

	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(matrix);
	}

	public MatrixD multiply(double value) {
		double[] sol = new double[m * n];
		for (int i = 0; i < m * n; i++) {
			sol[i] = matrix[i] * value;
		}
		return new MatrixD(m, n, sol);
	}

	public MatrixD transpose() {
		double[] sol = new double[m * n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sol[i * n + j] = matrix[i + j * m];
			}
		}
		return new MatrixD(n, m, sol);
	}

}
