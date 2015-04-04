package code.collision;

public class HitPoint extends HitShape {

	public HitPoint(int xOffset, int yOffset) {
		super(xOffset, yOffset);
		// TODO Auto-generated constructor stub
	}
	
	public HitPoint getClosestHitPoint(int x, int y){
		return this;
	}

}
