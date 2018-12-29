package code.util;

import code.graphics.SpriteData;
import code.math.VectorD;

/**
 * Created by theo on 15/07/17.
 */
public final class SpriteDataUtil {

    private static SpaxelRandom random = new SpaxelRandom();

    private SpriteDataUtil() {

    }

    public static SpriteData getRandomPart(SpriteData spriteData, int width, int height) {
        int x = random.nextInt((int) spriteData.getDim().getValue(0) - width);
        int y = random.nextInt((int) spriteData.getDim().getValue(1) - height);

        VectorD pos = spriteData.getPos().sum(new VectorD(x, y));

        SpriteData part = new SpriteData(width, height, (int) pos.getValue(0), (int) pos.getValue(1),
                spriteData.getSpritesheet());
        part.initialize();
        return part;
    }
}
