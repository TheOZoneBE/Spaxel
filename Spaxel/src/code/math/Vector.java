package code.math;

import code.graphics.Render;

public class Vector {
	private int m;
	private double[] vector;
	
	public Vector(int m){
		this.m = m;
		vector = new double[m];
	}
	
	public Vector(double[] vector){
		m = vector.length;
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
	
	public void render(int xOffset, int yOffset, Render render){
		render.setPixel((int)vector[0]+ xOffset, (int)vector[1] + yOffset, 0xffff0000);
	}
	
	public void print(){
		for(int i = 0; i< m; i++){
			System.out.print((int)vector[i] + " ");
		}
	}
	
	
}
