package code.collision;

import java.util.ArrayList;
import java.util.List;


public class HitShape {
	private List<HitPoint> hitPoints;
	
	public HitShape(){
		hitPoints = new ArrayList<>();
	}
	
	public void addHitPoint(HitPoint hitPoint){
		hitPoints.add(hitPoint);
	}
	
	
	

}
