package code.math;

import code.graphics.RenderBuffer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VectorD {
	private static final int DEFAULT_DIM = 3;
	private int m = DEFAULT_DIM;
	private double[] vector;

	public VectorD(int m) {
		this.m = m;
		vector = new double[m];
	}

	public VectorD(double[] vector) {
		m = vector.length;
		this.vector = vector;
	}

	public VectorD(@JsonProperty("x") double x, @JsonProperty("y") double y) {
		this.vector = new double[] { x, y, 1 };
	}

	public double getValue(int i) {
		return vector[i];
	}

	public void setValue(int i, double value) {
		vector[i] = value;
	}

	public double dotProduct(VectorD vec) {
		double sol = 0;
		for (int i = 0; i < m; i++) {
			sol += (vector[i] * vec.getValue(i));
		}
		return sol;
	}

	/*
	 * only works on 2d vectors
	 */
	public double crossProduct(VectorD vec) {
		return vector[0] * vec.getValue(1) - vector[1] * vec.getValue(0);
	}

	public VectorD multiplicate(double a) {
		VectorD sol = new VectorD(m);
		for (int i = 0; i < m; i++) {
			sol.setValue(i, vector[i] * a);
		}
		return sol;
	}

	public VectorD sum(VectorD vec) {
		VectorD sol = new VectorD(m);
		for (int i = 0; i < m; i++) {
			sol.setValue(i, vector[i] + vec.getValue(i));
		}
		return sol;
	}

	public VectorD diff(VectorD vec) {
		VectorD sol = new VectorD(m);
		for (int i = 0; i < m; i++) {
			sol.setValue(i, vector[i] - vec.getValue(i));
		}
		return sol;
	}

	/*
	 * only works with 2d vectors!!
	 */
	public VectorD normal() {
		VectorD sol = new VectorD(m);
		sol.setValue(0, vector[1]);
		sol.setValue(1, -vector[0]);
		return sol;
	}

	public void render(int xOffset, int yOffset, RenderBuffer render) {
		throw new UnsupportedOperationException();
	}

	public void print() {
		for (int i = 0; i < m; i++) {
			System.out.print(vector[i] + " ");
		}
	}

	// only works with 2d vectors
	public double length() {
		return Math.sqrt((vector[0] * vector[0]) + (vector[1] * vector[1]));
	}

	public VectorD copy() {
		return new VectorD(vector.clone());
	}

}
