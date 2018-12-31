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

    /**
     * Return a random part of a sprite with the specified dimensions.
     * 
     * @param spriteData The sprite to generate the part from
     * @param width      The width of the generated part.
     * @param height     The height of the generated part.
     * 
     * @return A new {@link code.graphics.SpriteData} object containing with the properties of the
     *         generated part.
     */
    public static SpriteData getRandomPart(SpriteData spriteData, int width, int height) {
        int x = random.nextInt((int) spriteData.getDim().getValue(0) - width);
        int y = random.nextInt((int) spriteData.getDim().getValue(1) - height);

        VectorD pos = spriteData.getPos().sum(new VectorD(x, y));

        SpriteData part =
                new SpriteData(new VectorD(width, height), pos, spriteData.getSpritesheet());
        part.initialize();
        return part;
    }
}
