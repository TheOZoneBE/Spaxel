package code.math;

public class Axis {
	private VectorF dirVec;
	
	public Axis(){
		dirVec = new VectorF(2);
	}
	
	public void initializeAxis(VectorF vec1, VectorF vec2){
		dirVec = vec1.diff(vec2);
	}
	
	public void initializeNormal(VectorF vec1, VectorF vec2){
		dirVec = vec1.diff(vec2).normal();
	}
	
	public VectorF project(VectorF vec){
		VectorF sol = new VectorF(2);
		float mul = dirVec.dotProduct(vec)/ dirVec.dotProduct(dirVec);
		sol = dirVec.multiplicate(mul);
		return sol;
	}

	public void print(){
		dirVec.print();
	}

}
