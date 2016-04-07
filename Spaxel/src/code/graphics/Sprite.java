package code.graphics;

import java.util.Random;

public class Sprite {
	private int width;
	private int height;
	private int[] pixels;
	private int scale;
	private int xPos;
	private int yPos;
	private Spritesheet spritesheet;
	private Random r;

	public Sprite(int width, int height, int xPos, int yPos, int scale, Spritesheet spritesheet) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.xPos = xPos;
		this.yPos = yPos;
		this.spritesheet = spritesheet;
		pixels = new int[width * scale * height * scale];
		r = new Random();
		load();
	}
	
	public Sprite(int width, int height, int scale, int[] pixels){
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.pixels = pixels;
		r = new Random();
	}
	
	public Sprite(int width, int height, int scale, int color){
		this.width = width;
		this.height = height;
		this.scale = scale;
		pixels = new int[width * scale * height * scale];
		for (int i = 0; i< scale*scale*width*height; i++){
			pixels[i] = color;
		}
		r = new Random();
	}

	public void load() {
		int[] tempixels = spritesheet.getPixels();
		for (int i = 0; i < width * scale; i++) {
			for (int j = 0; j < height * scale; j++) {
				pixels[i + j * width * scale] = tempixels[(xPos * width + (i / scale)) + spritesheet.getWidth() * (yPos * height + (j / scale))];
			}
		}
	}

	public void render(int x, int y, double rot, RenderBuffer render) {
		double dx = Math.cos(rot);
		double dy = Math.sin(rot);

		int midWidth = width * scale / 2;
		int midHeight = height * scale / 2;

		for (int i = -midWidth * 2; i < midWidth * 2; i++) {
			for (int j = -midHeight * 2; j < midHeight * 2; j++) {
				render.setPixel(x + (int) ((i * dx / 2) + (j * dy / 2)), y + (int) ((i * -dy / 2) + (j * dx / 2)), pixels[i / 2 + midWidth
						+ (j / 2 + midHeight) * width * scale]);
			}
		}
	}
	
	public Sprite getRandomPart(int width, int height){
		int[] rPixels = new int[width * height];
		int x = r.nextInt(this.width*scale - width);
		int y = r.nextInt(this.height*scale - height);
		for (int i = 0; i < width; i++){
			for (int j = 0; j< height; j++){
				rPixels[i + j*width] = pixels[x + i + (y+j)*this.width*scale];
			}
		}
		return new Sprite(width, height, 1 , rPixels);
	}

	public void render(int x, int y, RenderBuffer render) {
		int midWidth = width * scale / 2;
		int midHeight = height * scale / 2;
		for (int i = -midWidth; i < midWidth; i++) {
			for (int j = -midHeight; j < midHeight; j++) {
				render.setPixel(x + i, y + j, pixels[i + midWidth + (j + midHeight) * width * scale]);
			}
		}
	}
	
	public void render(int x, int y, int midWidth, int midHeight, RenderBuffer render){
		for (int i = -midWidth; i < width * scale  - midWidth; i++) {
			for (int j = -midHeight; j < width * scale - midHeight; j++) {
				render.setPixel(x + i, y + j, pixels[i + midWidth + (j + midHeight) * width * scale]);
			}
		}
	}

	public int[] getPixels() {
		return pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return height;
	}

}
