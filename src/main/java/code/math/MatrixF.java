package code.math;

import code.util.BufferUtils;

import java.nio.FloatBuffer;

public class MatrixF {
	private int m;
	private int n;
	private float[] matrix;

	public MatrixF(int m, int n) {
		this.m = m;
		this.n = n;
		matrix = new float[m * n];
	}

	public MatrixF(int m, int n, float[] matrix) {
		this.m = m;
		this.n = n;
		this.matrix = matrix;
	}

	public void setValue(int x, int y, float value) {
		matrix[x * n + y] = value;
	}

	public float getValue(int x, int y) {
		return matrix[x * n + y];
	}

	public void setMatrix(float[] matrix) {
		this.matrix = matrix;
	}

	public float[] getMatrix() {
		return matrix;
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	public MatrixF multiplicate(MatrixF mat) {
		MatrixF sol = new MatrixF(m, mat.getN());
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < mat.getN(); j++) {
				float sum = 0;
				for (int l = 0; l < n; l++) {
					sum += (matrix[i * n + l] * mat.getValue(l, j));
				}
				sol.setValue(i, j, sum);
			}
		}
		return sol;
	}

	public VectorF multiplicate(VectorF vec) {
		VectorF sol = new VectorF(n);
		for (int i = 0; i < m; i++) {
			float sum = 0;
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

	public MatrixF multiply(float value) {
		float[] sol = new float[m * n];
		for (int i = 0; i < m * n; i++) {
			sol[i] = matrix[i] * value;
		}
		return new MatrixF(m, n, sol);
	}

	public MatrixF transpose() {
		float[] sol = new float[m * n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sol[i * n + j] = matrix[i + j * m];
			}
		}
		return new MatrixF(n, m, sol);
	}

}
