package code.graphics;

public class Render {

	private int width;
	private int height;
	private int[] pixels;

	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public int getPixel(int i) {
		return pixels[i];
	}

	public void setPixel(int x,int y, int value) {
		if (x > 0 & x < width & y > 0 & y < height & value != 0xffff00ff) {
			pixels[x + y*width] = value;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void render(int xOffset, int yOffset){
		clear();
		
	}
	
	public void clear(){
		for (int i = 0; i < width*height; i++){
			pixels[i] = 0;
		}
	}

}
