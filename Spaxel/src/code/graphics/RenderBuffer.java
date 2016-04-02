package code.graphics;

public class RenderBuffer {

	private int width;
	private int height;
	private int[] pixels;

	public RenderBuffer(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public int getPixel(int i) {
		return pixels[i];
	}
	
	public int getPixel(int x, int y){
		return pixels[x + y*width];
	}

	public void setPixel(int x, int y, int value) {
		if (x >= 0 && x < width && y >= 0 && y < height && value != 0xffff00ff) {
			pixels[x + y * width] = value;
		}
	}

	public void setPixel(int i, int value) {
		if (i >= 0 && i < pixels.length) {
			pixels[i] = value;
		}
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void render(int xOffset, int yOffset) {
		clear();
	}

	public void dots(int xOffset, int yOffset){
		for (int i = (xOffset % 64); i < width; i+=64){
			for (int j = (yOffset % 64); j < height;j+=64){
				setPixel(i,j, 0xffffffff);
			}
		}
	}

	public void clear() {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}
	}
	
	public void distort(RenderBuffer original, double k){
		double aspect = (double)width/(double)height;
		aspect*=aspect;
		for (double i = 0; i < width; i++){
			for (double j =0; j < height; j++){
				double r2 = aspect*i*i + j*j;
				double f = 1+r2*k;
				double i2 = f*i;
				double j2 = f*j;
				if(i2>0 && i2<width&& j2>0&&j2<height){
					setPixel((int)i,(int)j, original.getPixel((int)(i*f), (int)(j*f)));
				}				
			}
		}
	}

}
