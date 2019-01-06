package code.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
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

public final class TextureUtil {
    private static final Logger LOGGER = Logger.getLogger(TextureUtil.class.getName());
    private static final int ONE_BYTE = 8;
    private static final int TWO_BYTES = ONE_BYTE * 2;
    private static final int THREE_BYTES = ONE_BYTE * 3;

    private TextureUtil() {

    }

    public static PackedTexture packTextures(Collection<Texture> textures) {
        Map<Integer, List<TextureNode>> nodes = new HashMap<>();

        for (Texture texture : textures) {
            TextureNode node = new TextureNode(texture);
            nodes.putIfAbsent(node.getDim(), new ArrayList<>());

            nodes.get(node.getDim()).add(node);
        }
        int currentDim = 2;
        while (fullSize(nodes) > 1) {
            if (nodes.containsKey(currentDim)) {
                List<TextureNode> dimNodes = nodes.get(currentDim);
                int size = dimNodes.size() > 3 ? 4 : dimNodes.size();
                TextureNode newNode = new TextureNode(currentDim * 2);
                switch (size) {
                    case 4:
                        newNode.setTopLeft(dimNodes.remove(dimNodes.size() - 1));
                    case 3:
                        newNode.setTopRight(dimNodes.remove(dimNodes.size() - 1));
                    case 2:
                        newNode.setBotLeft(dimNodes.remove(dimNodes.size() - 1));
                    case 1:
                        newNode.setBotRight(dimNodes.remove(dimNodes.size() - 1));
                    default:
                        break;
                }
                nodes.putIfAbsent(currentDim * 2, new ArrayList<>());
                nodes.get(currentDim * 2).add(newNode);
                if (dimNodes.isEmpty()) {
                    nodes.remove(currentDim);
                }
            } else {
                currentDim *= 2;
            }
        }
        return new PackedTexture(nodes.get(currentDim * 2).get(0));
    }

    private static int fullSize(Map<Integer, List<TextureNode>> nodes) {
        return nodes.values().stream().map(el -> el.size()).reduce(0,
                (Integer accSize, Integer size) -> accSize += size);
    }

    public static int[] loadTextureTree(TextureNode root) {
        int[] dest = new int[root.getDim() * root.getDim()];
        if (root.getTexture() == null) {
            System.out.println("descending");
            if (root.getTopLeft() != null) {
                blitData(0, 0, root.getDim(), root.getDim() / 2, root.getDim() / 2,
                        loadTextureTree(root.getTopLeft()), dest);
            }
            if (root.getTopRight() != null) {
                blitData(root.getDim() / 2, 0, root.getDim(), root.getDim() / 2, root.getDim() / 2,
                        loadTextureTree(root.getTopRight()), dest);
            }
            if (root.getBotLeft() != null) {
                blitData(0, root.getDim() / 2, root.getDim(), root.getDim() / 2, root.getDim() / 2,
                        loadTextureTree(root.getBotLeft()), dest);
            }
            if (root.getBotRight() != null) {
                blitData(root.getDim() / 2, root.getDim() / 2, root.getDim(), root.getDim() / 2,
                        root.getDim() / 2, loadTextureTree(root.getBotRight()), dest);
            }
            for (int i : dest) {
                if (i != 0) {
                    System.out.println("combined");
                    break;
                }
            }
            return dest;
        } else {
            Texture texture = root.getTexture();
            System.out.println(texture.getName());
            blitData(0, 0, root.getDim(), (int) texture.getDim().getValue(0),
                    (int) texture.getDim().getValue(1), loadImage(texture), dest);
            for (int i : dest) {
                if (i != 0) {
                    System.out.println("written");
                    break;
                }
            }
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
