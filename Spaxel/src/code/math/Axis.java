package code.math;

public class Axis {
	private Vector dirVec;
	
	public Axis(){
		dirVec = new Vector(2);
	}
	
	public void initializeAxis(Vector vec1, Vector vec2){
		dirVec = vec1.diff(vec2);
	}
	
	public void initializeNormal(Vector vec1, Vector vec2){
		dirVec = vec1.diff(vec2).normal();
	}
	
	public Vector project(Vector vec){
		Vector sol = new Vector(2);
		double mul = dirVec.dotProduct(vec)/ dirVec.dotProduct(dirVec);
		sol = dirVec.multiplicate(mul);
		return sol;
	}

}
