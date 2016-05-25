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

    public int getPixel(int x, int y) {
        return pixels[x + y * width];
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

    public void dots(int xOffset, int yOffset) {
        for (int i = (xOffset % 64); i < width; i += 64) {
            for (int j = (yOffset % 64); j < height; j += 64) {
                setPixel(i, j, 0xffffffff);
            }
        }
    }

    public void clear() {
        for (int i = 0; i < width * height; i++) {
            pixels[i] = 0;
        }
    }
    /*
	 * distortion algorithm, not centered and very intensive
	 
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
	}*/
	
	/*
	 * awful distortion
	 
	public void distort(RenderBuffer original){
		int midWidth = width/2;
		int midHeight = height/2;
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				int dx = i - midWidth;
				int dy = j - midHeight;
				double fx = Math.abs((double)dx/(double)midWidth);
				double fy = Math.abs((double)dy/(double)midHeight);
				fx = Math.sqrt(fx);
				fy = Math.sqrt(fy);
				setPixel(i, j, original.getPixel(midWidth+(int)(dx*fx), midHeight+(int)(dy*fy)));
			}
		}
	}*/
	
	/*
	 * better but still big performance hit
	 *
	public void distort(RenderBuffer original){
		int midWidth = width/2;
		int midHeight = height/2;
		double distance = Math.sqrt(midWidth*midWidth + midHeight*midHeight);
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				int dx = i - midWidth;
				int dy = j - midHeight;
				double distance2 = Math.sqrt(dx*dx+dy*dy);
				double f = distance2/distance;
				setPixel(i, j, original.getPixel(midWidth+(int)(dx*f), midHeight+(int)(dy*f)));
			}
		}
	}*/
	
	/*
	 * maybe cool pixel effect
	 *
	public void distort(RenderBuffer original){
		int midWidth = width/2;
		int midHeight = height/2;
		double distance = Math.sqrt(midWidth*midWidth + midHeight*midHeight);
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				int color = original.getPixel(i, j);
				if (color != 0){
					int dx = i - midWidth;
					int dy = j - midHeight;
					double distance2 = Math.sqrt(dx*dx+dy*dy);
					double f = distance2/distance;
					for (int k = 0; k < f*1; k++){
						for (int l = 0; l < f*1; l++){
							setPixel(i+(int)(dx*f) + k,j+(int)(dy*f)+l , color);
						}
					}					
				}				
			}
		}
	}*/

    /**
     * fastblur algorithm mostly based of http://incubator.quasimondo.com/processing/superfast_blur.php
     *
     * @param source
     * @param radius
     */
    void fastblur(RenderBuffer source, int radius) {
        int w = source.getWidth();
        int h = source.getHeight();
        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;
        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, p1, p2, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];
        int vmax[] = new int[Math.max(w, h)];

        int dv[] = new int[256 * div];
        for (i = 0; i < 256 * div; i++) {
            dv[i] = (i / div);
        }

        yw = yi = 0;

        for (y = 0; y < h; y++) {
            rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = source.getPixel(yi + Math.min(wm, Math.max(i, 0)));
                rsum += (p & 0xff0000) >> 16;
                gsum += (p & 0x00ff00) >> 8;
                bsum += p & 0x0000ff;
            }
            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                    vmax[x] = Math.max(x - radius, 0);
                }
                p1 = source.getPixel(yw + vmin[x]);
                p2 = source.getPixel(yw + vmax[x]);

                rsum += ((p1 & 0xff0000) - (p2 & 0xff0000)) >> 16;
                gsum += ((p1 & 0x00ff00) - (p2 & 0x00ff00)) >> 8;
                bsum += (p1 & 0x0000ff) - (p2 & 0x0000ff);
                yi++;
            }
            yw += w;
        }

        for (x = 0; x < w; x++) {
            rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;
                rsum += r[yi];
                gsum += g[yi];
                bsum += b[yi];
                yp += w;
            }
            yi = x;
            for (y = 0; y < h; y++) {
                pixels[yi] = 0xff000000 | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];
                if (x == 0) {
                    vmin[y] = Math.min(y + radius + 1, hm) * w;
                    vmax[y] = Math.max(y - radius, 0) * w;
                }
                p1 = x + vmin[y];
                p2 = x + vmax[y];

                rsum += r[p1] - r[p2];
                gsum += g[p1] - g[p2];
                bsum += b[p1] - b[p2];

                yi += w;
            }
        }
    }

    public void pixelBlur(RenderBuffer source, int radius, int tilesize){
        RenderBuffer temp = new RenderBuffer(source.getWidth(), source.getHeight());
        temp.decimate(source, tilesize);
        //RenderBuffer temp2 = new RenderBuffer(source.getWidth(), source.getHeight());
        temp.fastblur(temp, radius);
        for (int i = 0; i < width; i++){
            for (int j = 0; j< height; j++){
                pixels[i + j*width] = temp.getPixel(i/tilesize + j/tilesize*width);
            }
        }
    }

    public void decimate(RenderBuffer source, int dec){
        for (int i = 0; i < source.getWidth()/dec; i++){
            for (int j = 0; j< source.getHeight()/dec; j++){
                pixels[i + j*width] = source.getPixel(i*dec + j*dec*width);
            }
        }
    }

    public void pixelBlur2(RenderBuffer source, int radius, int tilesize){
        int w = source.getWidth();
        int h = source.getHeight();
        int wTls = w/tilesize;
        int hTls = h/tilesize;
        int whTls = wTls*hTls;
        int div = (radius*2 +1)*(radius*2+1);
        int[] preRed = new int[whTls];
        int[] preGreen = new int[whTls];
        int[] preBlue = new int[whTls];
        for (int i = 0; i< wTls; i++){
            for (int j = 0; j< hTls; j++){
                int dest = j*wTls +i;
                int[] rgb = getRGB(source.getPixel(i*tilesize, j*tilesize));
                preRed[dest] = rgb[0];
                preGreen[dest] = rgb[1];
                preBlue[dest] = rgb[2];
                if (i != 0) {
                    preRed[dest] += preRed[dest - 1];
                    preGreen[dest] += preGreen[dest -1];
                    preBlue[dest] += preBlue[dest-1];
                }
                if (j != 0) {
                    preRed[dest] += preRed[dest-wTls];
                    preGreen[dest] += preGreen[dest-wTls];
                    preBlue[dest] += preBlue[dest-wTls];
                }
                if (i!=0 && j!= 0){
                    preRed[dest] -= preRed[dest-wTls-1];
                    preGreen[dest] -= preGreen[dest-wTls-1];
                    preBlue[dest] -= preBlue[dest-wTls-1];
                }
            }
        }
        for (int i = 0; i< wTls; i++){
            for (int j = 0; j< hTls; j++){
                int[] values = {i + radius, j+radius, i-radius-1, j-radius-1};
                int r = 0;
                r+= preRed[getValue(values[0], values[1], wTls, hTls)];
                r+= preRed[getValue(values[2], values[3], wTls, hTls)];
                r-= preRed[getValue(values[2], values[1], wTls, hTls)];
                r-= preRed[getValue(values[0], values[3], wTls, hTls)];
                int g = 0;
                g+= preGreen[getValue(values[0], values[1], wTls, hTls)];
                g+= preGreen[getValue(values[2], values[3], wTls, hTls)];
                g-= preGreen[getValue(values[2], values[1], wTls, hTls)];
                g-= preGreen[getValue(values[0], values[3], wTls, hTls)];
                int b = 0;
                b+= preBlue[getValue(values[0], values[1],  wTls, hTls)];
                b+= preBlue[getValue(values[2], values[3], wTls, hTls)];
                b-= preBlue[getValue(values[2], values[1], wTls, hTls)];
                b-= preBlue[getValue(values[0], values[3], wTls, hTls)];
                pixels[j*tilesize*w +i*tilesize] = blurredPixel(r, g, b, div);
            }
        }
        if (tilesize > 1){
            blowup(tilesize);
        }

    }

    private int getValue(int x, int y, int w, int h){
        int x2 = x;
        int y2 = y;
        if (x < 0){
            x2 = 0;
        }
        else if (x >= w){
            x2 = w-1;
        }
        if (y < 0){
            y2 =0;
        }
        else  if (y >= h){
            y2 = h-1;
        }
        return x2 + y2*w;
    }

    private void blowup(int tilesize){
        for (int i = 0; i < width; i++){
            for (int j =0; j <height; j++){
                pixels[i+ j*width] = pixels[i-i%tilesize + (j-j%tilesize)*width];
            }
        }
    }

    private int[] getRGB(int i){
        int[] rgb = new int[3];
        rgb[0] += (i & 0xff0000) >> 16;
        rgb[1] += (i & 0x00ff00) >> 8;
        rgb[2] += i & 0x0000ff;
        return rgb;
    }

    private int blurredPixel(int r, int g, int b, int div){
        return 0xff000000 | r/div << 16 | g/div << 8 |b/div;
    }

}
