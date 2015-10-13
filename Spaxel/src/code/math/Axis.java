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

}
