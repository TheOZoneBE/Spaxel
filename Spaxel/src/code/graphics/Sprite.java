package code.graphics;

public class Sprite {
	private int width;
	private int height;
	private int[] pixels;
	private int scale;
	private int xPos;
	private int yPos;
	private Spritesheet spritesheet;

	public Sprite(int width, int height, int xPos, int yPos, int scale, Spritesheet spritesheet) {
		this.width = width;
		this.height = height;
		this.scale = scale;
		this.xPos = xPos;
		this.yPos = yPos;
		this.spritesheet = spritesheet;
		pixels = new int[width * scale * height * scale];
		load();
	}

	public void load() {
		int[] tempixels = spritesheet.getPixels();
		for (int i = 0; i < width * scale; i++) {
			for (int j = 0; j < height * scale; j++) {
				pixels[i + j * width * scale] = tempixels[(xPos * width + (i / scale)) + spritesheet.getWidth() * (yPos * height + (j / scale))];
			}
		}
	}

	public void render(int x, int y, double rot, Render render) {
		double dx = Math.cos(rot);
		double dy = Math.sin(rot);

		int ox = x + (width * scale / 2);
		int oy = y + (height * scale / 2);
		System.out.println(dx + " " + dy);

		for (int i = 0; i < width * scale * 2; i++) {
			for (int j = 0; j < height * scale * 2; j++) {
				render.setPixel(ox + (int) ((i - width * scale) * (dx / 2) + (j - height * scale) * (dy / 2))
						, (oy + (int) ((i - width * scale) * (-dy / 2) + (j - height * scale) * (dx / 2))), pixels[i / 2 + j / 2 * width * scale]);
			}
		}
	}

	public void render(int x, int y, Render render) {
		for (int i = 0; i < width * scale; i++) {
			for (int j = 0; j < height * scale; j++) {
				render.setPixel(x + i, y + j, pixels[i + j * width * scale]);
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
