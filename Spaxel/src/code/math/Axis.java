package code.math;

public class Axis {
	private VectorD dirVec;
	
	public Axis(){
		dirVec = new VectorD(2);
	}
	
	public void initializeAxis(VectorD vec1, VectorD vec2){
		dirVec = vec1.diff(vec2);
	}
	
	public void initializeNormal(VectorD vec1, VectorD vec2){
		dirVec = vec1.diff(vec2).normal();
	}
	
	public VectorD project(VectorD vec){
		VectorD sol = new VectorD(2);
		double mul = dirVec.dotProduct(vec)/ dirVec.dotProduct(dirVec);
		sol = dirVec.multiplicate(mul);
		return sol;
	}

}
