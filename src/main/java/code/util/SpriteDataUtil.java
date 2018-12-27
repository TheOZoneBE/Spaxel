package code.util;

import code.graphics.SpriteData;

import java.util.Random;

/**
 * Created by theo on 15/07/17.
 */
public final class SpriteDataUtil {

    private static Random random = new Random();

    private SpriteDataUtil() {

    }

    public static SpriteData getRandomPart(SpriteData spriteData, int width, int height) {
        int x = random.nextInt(spriteData.getWidth() - width);
        int y = random.nextInt(spriteData.getHeight() - height);
        SpriteData part = new SpriteData(width, height, spriteData.getxPos() + x, spriteData.getyPos() + y,
                spriteData.getSpritesheet());
        part.initialize();
        return part;
    }
}
