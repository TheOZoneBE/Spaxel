package code.graphics;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
		pixels = new int[width * height];
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
		pixels = new int[width* height];
		for (int i = 0; i< width*height; i++){
			pixels[i] = color;
		}
		r = new Random();
	}

	public void load() {
		int[] tempixels = spritesheet.getPixels();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixels[i + j * width] = tempixels[xPos * width + i + spritesheet.getWidth() * (yPos * height + j)];
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
