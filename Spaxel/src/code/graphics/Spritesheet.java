package code.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	private int width;
	private int height;
	private int[] pixels;
	private String path;

	public Spritesheet(int width, int height, String path) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.path = path;
		load();
	}

	public void load() {
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
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
