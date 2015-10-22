package code.math;

public class Projection {
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	private boolean empty;
	
	public Projection(Vector vec){
		xMin = vec.getValue(0);
		xMax = vec.getValue(0);
		yMin = vec.getValue(1);
		yMax = vec.getValue(1);
		empty = false;
	}
	
	public Projection(){
		empty = true;
	}
	
	public void addVector(Vector vec){
		if(empty){
			xMin = vec.getValue(0);
			xMax = vec.getValue(0);
			yMin = vec.getValue(1);
			yMax = vec.getValue(1);
			empty = false;
		}
		else{
			double x = vec.getValue(0);
			double y = vec.getValue(1);
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
	
	public double getXMin(){
		return xMin;
	}
	
	public double getXMax(){
		return xMax;
	}
	
	public double getYMin(){
		return yMin;
	}
	
	public double getYMax(){
		return yMax;
	}
	
	public boolean overlap(Projection p){
		if (xMax < p.getXMin()){
			return false;
		}
		else if (xMin > p.getXMax()){
			return false;
		}
		else return true;
	}
	
	public void print(){
		System.out.println((int)xMin +" " + (int)xMax +" | "+ (int)yMin +" "+(int)yMax);
	}
}
