package code.graphics;

public class Render {

	private int width;
	private int height;
	private int[] pixels;
	private Spritesheet sheet;
	private Sprite sprite;

	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		sheet = new Spritesheet(32,32,"/spritesheets/Ships.png");
		sprite = new Sprite(16,16,0,0,4,sheet);
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
	double i = 0;
	public void render(){
		clear();
		sprite.render(250,250,i, this);
		i += 0.01;
	}
	
	public void clear(){
		for (int i = 0; i < width*height; i++){
			pixels[i] = 0;
		}
	}

}
