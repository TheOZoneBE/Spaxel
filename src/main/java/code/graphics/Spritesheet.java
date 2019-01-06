package code.graphics;

import code.util.BufferUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static org.lwjgl.opengl.GL11.*;

public class Spritesheet {
	private static final Logger LOGGER = Logger.getLogger(Spritesheet.class.getName());
	private static final int ONE_BYTE = 8;
	private static final int TWO_BYTES = ONE_BYTE * 2;
	private static final int THREE_BYTES = ONE_BYTE * 3;

	private int width;
	private int height;
	private String path;
	private int id;

	public Spritesheet() {
		super();
	}

	public void load() {
		int[] pixels = new int[width * height];
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(path));
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}

		int[] data = new int[width * height];
		for (int i = 0; i < width * height; i++) {
			int a = (pixels[i] & 0xff000000) >> THREE_BYTES;
			int r = (pixels[i] & 0xff0000) >> TWO_BYTES;
			int g = (pixels[i] & 0xff00) >> ONE_BYTE;
			int b = (pixels[i] & 0xff);

			data[i] = a << THREE_BYTES | b << TWO_BYTES | g << ONE_BYTE | r;
		}

		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE,
				BufferUtils.createIntBuffer(data));

		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
