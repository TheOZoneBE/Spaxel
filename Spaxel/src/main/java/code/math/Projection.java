package code.math;

public class Projection {
	private float xMin;
	private float xMax;
	private float yMin;
	private float yMax;
	private boolean empty;
	
	public Projection(VectorF vec){
		xMin = vec.getValue(0);
		xMax = vec.getValue(0);
		yMin = vec.getValue(1);
		yMax = vec.getValue(1);
		empty = false;
	}
	
	public Projection(){
		empty = true;
	}
	
	public void addVector(VectorF vec){
		if(empty){
			xMin = vec.getValue(0);
			xMax = vec.getValue(0);
			yMin = vec.getValue(1);
			yMax = vec.getValue(1);
			empty = false;
		}
		else{
			float x = vec.getValue(0);
			float y = vec.getValue(1);
			if (x < xMin){
				xMin = x;
			}
			if (x > xMax){
				xMax = x;
			}
			if (y < yMin){
				yMin = y;
			}
			if (y > yMax){
				yMax = y;
			}
		}		
	}
	
	public float getXMin(){
		return xMin;
	}
	
	public float getXMax(){
		return xMax;
	}
	
	public float getYMin(){
		return yMin;
	}
	
	public float getYMax(){
		return yMax;
	}
	
	public boolean overlap(Projection p){
		if (xMax < p.getXMin()){
			return false;
		}
		if (xMin > p.getXMax()){
			return false;
		}
		if (yMax < p.getYMin()){
			return false;
		}
		return yMin <= p.getYMax();
	}
	
	public void print(){
		System.out.println((int)xMin +" " + (int)xMax +" | "+ (int)yMin +" "+(int)yMax);
	}
}
