package code.math;

public class Vector {
	private int m;
	private double[] vector;
	
	public Vector(int m){
		this.m = m;
		vector = new double[m];
	}
	
	public Vector(int m, double[] vector){
		this.m = m;
		this.vector = vector;
	}
	
	public double getValue(int i){
		return vector[i];
	}
	
	public void setValue(int i, double value){
		vector[i] = value;
	}
	
	public double dotProduct(Vector vec){
		int sol = 0;
		for(int i = 0; i < m; i++){
			sol += (vector[i]*vec.getValue(i));
		}
		return sol;
	}
	
	public Vector multiplicate(double a){
		Vector sol = new Vector(m);
		for(int i = 0; i< m; i++){
			sol.setValue(i, vector[i] * a);
		}
		return sol;
	}
	
	public Vector sum(Vector vec){
		Vector sol = new Vector(m);
		for(int i = 0; i< m; i++){
			sol.setValue(i, vector[i]+ vec.getValue(i));
		}
		return sol;
	}
	
	public Vector diff(Vector vec){
		Vector sol = new Vector(m);
		for(int i = 0; i< m; i++){
			sol.setValue(i, vector[i]- vec.getValue(i));
		}
		return sol;
	}
	
	
	/*
	 * only works with 2d vectors!!
	 */
	public Vector normal(){
		Vector sol = new Vector(m);
		sol.setValue(0, vector[1]);
		sol.setValue(1, -vector[0]);		
		return sol;
	}
	
	
}