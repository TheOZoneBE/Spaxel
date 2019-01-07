package code.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javax.imageio.ImageIO;
import code.graphics.texture.PackedTexture;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.lwjgl.opengl.GL11.*;
import code.graphics.texture.Texture;
import code.graphics.texture.TextureNode;

/**
 * Provides utility functions for the creation and packing of textures
 */
public final class TextureUtil {
    private static final Logger LOGGER = Logger.getLogger(TextureUtil.class.getName());
    private static final int ONE_BYTE = 8;
    private static final int TWO_BYTES = ONE_BYTE * 2;
    private static final int THREE_BYTES = ONE_BYTE * 3;

    private static final int DIM_BASE = 2;
    private static final int SIZE_4 = 4;
    private static final int SIZE_3 = 3;
    private static final int SIZE_2 = 2;

    private TextureUtil() {

    }

    /**
     * Pack this list of textures into one PackedTexture
     * 
     * @param textures the textures to pack
     * 
     * @return the packed texture
     */
    public static PackedTexture packTextures(Iterable<Texture> textures) {
        Map<Integer, List<TextureNode>> nodes = new HashMap<>();

        for (Texture texture : textures) {
            TextureNode node = new TextureNode(texture);
            nodes.putIfAbsent(node.getDim(), new ArrayList<>());

            nodes.get(node.getDim()).add(node);
        }
        int currentDim = DIM_BASE;
        while (fullSize(nodes) > 1) {
            if (nodes.containsKey(currentDim)) {
                List<TextureNode> dimNodes = nodes.get(currentDim);
                int size = dimNodes.size() > SIZE_3 ? SIZE_4 : dimNodes.size();
                TextureNode newNode = new TextureNode(currentDim * DIM_BASE);
                switch (size) {
                    case SIZE_4:
                        newNode.setTopLeft(dimNodes.remove(dimNodes.size() - 1));
                    case SIZE_3:
                        newNode.setTopRight(dimNodes.remove(dimNodes.size() - 1));
                    case SIZE_2:
                        newNode.setBotLeft(dimNodes.remove(dimNodes.size() - 1));
                    case 1:
                        newNode.setBotRight(dimNodes.remove(dimNodes.size() - 1));

                    default:
                        break;
                }
                nodes.putIfAbsent(currentDim * DIM_BASE, new ArrayList<>());
                nodes.get(currentDim * DIM_BASE).add(newNode);
                if (dimNodes.isEmpty()) {
                    nodes.remove(currentDim);
                }
            } else {
                currentDim *= DIM_BASE;
            }
        }
        return new PackedTexture(nodes.get(currentDim * DIM_BASE).get(0));
    }

    private static int fullSize(Map<Integer, List<TextureNode>> nodes) {
        return nodes.values().stream().map(List::size).reduce(0,
                (Integer accSize, Integer size) -> accSize += size);
    }

    /**
     * Load a texture tree into memory
     * 
     * @param root the root node of the tree
     * 
     * @return the texture data of the tree
     */
    public static int[] loadTextureTree(TextureNode root) {
        int[] dest = new int[root.getDim() * root.getDim()];
        if (root.getTexture() == null) {
            if (root.getTopLeft() != null) {
                blitData(0, 0, root.getDim(), root.getDim() / DIM_BASE, root.getDim() / DIM_BASE,
                        loadTextureTree(root.getTopLeft()), dest);
            }
            if (root.getTopRight() != null) {
                blitData(root.getDim() / DIM_BASE, 0, root.getDim(), root.getDim() / DIM_BASE,
                        root.getDim() / DIM_BASE, loadTextureTree(root.getTopRight()), dest);
            }
            if (root.getBotLeft() != null) {
                blitData(0, root.getDim() / DIM_BASE, root.getDim(), root.getDim() / DIM_BASE,
                        root.getDim() / DIM_BASE, loadTextureTree(root.getBotLeft()), dest);
            }
            if (root.getBotRight() != null) {
                blitData(root.getDim() / DIM_BASE, root.getDim() / DIM_BASE, root.getDim(),
                        root.getDim() / DIM_BASE, root.getDim() / DIM_BASE,
                        loadTextureTree(root.getBotRight()), dest);
            }
            return dest;
        } else {
            Texture texture = root.getTexture();
            blitData(0, 0, root.getDim(), (int) texture.getDim().getValue(0),
                    (int) texture.getDim().getValue(1), loadImage(texture), dest);
            return dest;
        }


    }

    private static void blitData(int x, int y, int width, int sourceWidth, int sourceHeight,
            int[] source, int[] dest) {
        for (int i = 0; i < sourceHeight; i++) {
            for (int j = 0; j < sourceWidth; j++) {
                dest[x + j + ((y + i) * width)] = source[j + i * sourceWidth];
            }
        }
    }

    /**
     * Load a texture into memory
     * 
     * @param texture the texture to load
     * 
     * @return the texture data
     */
    public static int[] loadImage(Texture texture) {
        int width = (int) texture.getDim().getValue(0);
        int height = (int) texture.getDim().getValue(1);
        String path = texture.getPath();
        int[] pixels = new int[width * height];
        try {
            BufferedImage image = ImageIO.read(TextureUtil.class.getResource(path));
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
        return data;
    }

    /**
     * Create a new GL texture and load the data into it
     * 
     * @param width  the width of the texture
     * @param height the height of the texture
     * @param data   the data of the texture
     * 
     * @return the id of the texture
     */
    public static int createGPUTexture(int width, int height, int[] data) {
        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE,
                BufferUtils.createIntBuffer(data));

        glBindTexture(GL_TEXTURE_2D, 0);
        return id;
    }
}
