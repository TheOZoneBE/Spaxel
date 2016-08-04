package code.graphics;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Sprite {
	private int width;
	private int height;
	private int[] pixels;
	private Map<Integer, Integer[]> blurCache;
	private int[] blurredPixels;
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
		pixels = new int[width * height];
		blurredPixels = new int[(width + 2) * (height + 2)];
		r = new Random();
		load();
		loadBlur();
	}
	
	public Sprite(int width, int height, int scale, int[] pixels){
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.pixels = pixels;
		blurredPixels = new int[(width + 2) * (height + 2)];
		r = new Random();
		loadBlur();
	}
	
	public Sprite(int width, int height, int scale, int color){
		this.width = width;
		this.height = height;
		this.scale = scale;
		pixels = new int[width* height];
		blurredPixels = new int[(width + 2) * (height + 2)];
		for (int i = 0; i< width*height; i++){
			pixels[i] = color;
		}
		r = new Random();
		loadBlur();
	}

	public void load() {
		int[] tempixels = spritesheet.getPixels();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixels[i + j * width] = tempixels[xPos * width + i + spritesheet.getWidth() * (yPos * height + j)];
			}
		}
	}

	public void loadBlur(){
		for (int i = 0; i < (width+2); i++){
			for(int j = 0; j < (height+2); j++){
				int pixel = getKernelAverage(i, j);
				if (pixel == 0){
					pixel = 0xffff00ff;
				}
				blurredPixels[i + j*(width+2)] = pixel;
			}
		}
	}

	public int getKernelAverage(int i, int j){
		int rsum = 0;
		int gsum = 0;
		int bsum = 0;
		for (int x = i - 1; x < i+2; x++){
			for (int y = j-1; y < j+2; y++){
				if (!(x < 1 || y < 1 || x > width || y > height)){
					int pixel = pixels[x -1 + (y-1)*(width)];
					if (pixel == 0xffff00ff){
						pixel = 0;
					}
					rsum += (pixel & 0xff0000) >> 16;
					gsum += (pixel & 0x00ff00) >> 8;
					bsum += pixel & 0x0000ff;
				}
			}
		}
		return 0x00000000 | (rsum/9 << 16) | (gsum/9 << 8) | bsum/9;
	}

	//TODO
	//concept of new render method
	//scale not hardcoded, just the responsibility of the calling entity
	//if rot is 0 its ignored, same with transparency, blur considers scale and will have a matrix of size 2*scale+1 and calculates it at runtime
	//maybe consider caching this to improve performance
	//render(int x, int y, int scale, double rot, double transparency, boolean blur, RenderBuffer render)
	public void renderSprite(int x, int y, int scale, double rot, double transparency, boolean blur, RenderBuffer render){
		boolean trans = transparency != 0;

		int midWidth;
		int midHeight;

		if (blur){
			midWidth = (width + 2) * scale / 2;
			midHeight = (height + 2) * scale / 2;
			if (!blurCache.containsKey(scale)){
				blurCache.put(scale, loadBlurCache(scale));
			}
		}
		else {
			midWidth = width * scale / 2;
			midHeight = height * scale / 2;
		}

		if (rot != 0){
			double dx = Math.cos(rot);
			double dy = Math.sin(rot);

			Integer[] blurredPixels = blurCache.get(scale);

			for (int i = -midWidth * 2; i < midWidth * 2; i++) {
				for (int j = -midHeight * 2; j < midHeight * 2; j++) {
					if (blur){
						render.setPixel(x + (int) ((i * dx / 2) + (j * dy / 2)),y + (int) ((i * -dy / 2) + (j * dx / 2)), transparency, blurredPixels[(i/ 2 + midWidth)
								+ (j / 2 + midHeight) * (width+2*scale)]);
					}
					else {
						render.setPixel(x + (int) ((i * dx / 2) + (j * dy / 2)),y + (int) ((i * -dy / 2) + (j * dx / 2)), transparency, pixels[(i/ 2 + midWidth)/scale
								+ ((j / 2 + midHeight)/scale) * width]);
					}
				}
			}
		}
		else {
			Integer[] blurredPixels = blurCache.get(scale);

			for (int i = -midWidth; i < midWidth; i++) {
				for (int j = -midHeight; j < midHeight; j++) {
					if (blur){
						render.setPixel(x + i, y + j, transparency, blurredPixels[(i + midWidth) + (j + midHeight) * (width+2*scale)]);
					}
					else {
						render.setPixel(x + i, y + j, transparency, pixels[(i + midWidth)/scale + ((j + midHeight)/scale) * width]);
					}
				}
			}
		}



	}

	public Integer[] loadBlurCache(int scale){
		//todo implement
		return null;
	}

	public void renderBlur(int x, int y, double rot, RenderBuffer render,double transparency ){
		double dx = Math.cos(rot);
		double dy = Math.sin(rot);

		int midWidth = (width + 2) * scale / 2;
		int midHeight = (height + 2) * scale / 2;

		for (int i = -midWidth * 2; i < midWidth * 2; i++) {
			for (int j = -midHeight * 2; j < midHeight * 2; j++) {
				render.setPixel(x + (int) ((i * dx / 2) + (j * dy / 2)),y + (int) ((i * -dy / 2) + (j * dx / 2)), transparency, blurredPixels[(i/ 2 + midWidth)/scale
						+ ((j / 2 + midHeight)/scale) * (width+2)]);
			}
		}
	}

	public void renderBlur(int x, int y, RenderBuffer render, double transparency){
		int midWidth = (width + 2) * scale / 2;
		int midHeight = (height + 2) * scale / 2;

		for (int i = -midWidth; i < midWidth; i++) {
			for (int j = -midHeight; j < midHeight; j++) {
				render.setPixel(x + i, y + j, transparency, blurredPixels[(i + midWidth)/scale + ((j + midHeight)/scale) * (width+2)]);
			}
		}
	}

	public void renderBlur(int x, int y, double rot, int scale, RenderBuffer render) {
		double dx = Math.cos(rot);
		double dy = Math.sin(rot);

		int midWidth = width * scale / 2;
		int midHeight = height * scale / 2;

		for (int i = -midWidth * 2; i < midWidth * 2; i++) {
			for (int j = -midHeight * 2; j < midHeight * 2; j++) {
				render.setPixel(x + (int) ((i * dx / 2) + (j * dy / 2)),y + (int) ((i * -dy / 2) + (j * dx / 2)), pixels[(i/ 2 + midWidth)/scale
						+ ((j / 2 + midHeight)/scale) * width]);
			}
		}
	}

	public void renderBlur(int x, int y, int scale, RenderBuffer render) {
		int midWidth = width * scale / 2;
		int midHeight = height * scale / 2;
		for (int i = -midWidth; i < midWidth; i++) {
			for (int j = -midHeight; j < midHeight; j++) {
				render.setPixel(x + i, y + j, pixels[(i + midWidth)/scale + ((j + midHeight)/scale) * width]);
			}
		}
	}

	public void render(int x, int y, double rot, RenderBuffer render) {
		render(x, y, rot,scale, render);
	}

	public void render(int x, int y, RenderBuffer render) {
		render(x,y, scale, render);
	}

	public void render(int x, int y, double rot, RenderBuffer render,double transparency ){
		double dx = Math.cos(rot);
		double dy = Math.sin(rot);

		int midWidth = width * scale / 2;
		int midHeight = height * scale / 2;

		for (int i = -midWidth * 2; i < midWidth * 2; i++) {
			for (int j = -midHeight * 2; j < midHeight * 2; j++) {
				render.setPixel(x + (int) ((i * dx / 2) + (j * dy / 2)),y + (int) ((i * -dy / 2) + (j * dx / 2)), transparency, pixels[(i/ 2 + midWidth)/scale
						+ ((j / 2 + midHeight)/scale) * width]);
			}
		}
	}

	public void render(int x, int y, RenderBuffer render, double transparency){
		int midWidth = width * scale / 2;
		int midHeight = height * scale / 2;
		for (int i = -midWidth; i < midWidth; i++) {
			for (int j = -midHeight; j < midHeight; j++) {
				render.setPixel(x + i, y + j, transparency, pixels[(i + midWidth)/scale + ((j + midHeight)/scale) * width]);
			}
		}
	}

	public void render(int x, int y, double rot, int scale, RenderBuffer render) {
		double dx = Math.cos(rot);
		double dy = Math.sin(rot);

		int midWidth = width * scale / 2;
		int midHeight = height * scale / 2;

		for (int i = -midWidth * 2; i < midWidth * 2; i++) {
			for (int j = -midHeight * 2; j < midHeight * 2; j++) {
				render.setPixel(x + (int) ((i * dx / 2) + (j * dy / 2)),y + (int) ((i * -dy / 2) + (j * dx / 2)), pixels[(i/ 2 + midWidth)/scale
						+ ((j / 2 + midHeight)/scale) * width]);
			}
		}
	}

	public void render(int x, int y, int scale, RenderBuffer render) {
		int midWidth = width * scale / 2;
		int midHeight = height * scale / 2;
		for (int i = -midWidth; i < midWidth; i++) {
			for (int j = -midHeight; j < midHeight; j++) {
				render.setPixel(x + i, y + j, pixels[(i + midWidth)/scale + ((j + midHeight)/scale) * width]);
			}
		}
	}

	public Sprite getRandomPart(int width, int height){
		int[] rPixels = new int[width * height];
		int x = r.nextInt(this.width - width);
		int y = r.nextInt(this.height - height);
		for (int i = 0; i < width; i++){
			for (int j = 0; j< height; j++){
				rPixels[i + j*width] = pixels[x + i + (y+j)*this.width];
			}
		}
		return new Sprite(width, height, scale , rPixels);
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
