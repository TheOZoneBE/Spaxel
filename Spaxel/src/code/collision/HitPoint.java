package code.collision;

public class HitPoint {
	private int xOffset;
	private int yOffset;

	public HitPoint(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public HitPoint getClosestHitPoint(int x, int y){
		return this;
	}

}
