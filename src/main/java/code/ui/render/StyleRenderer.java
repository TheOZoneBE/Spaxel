package code.ui.render;

import code.ui.styles.Style;
import code.graphics.MasterBuffer;
import code.graphics.SpriteData;
import code.math.VectorD;
import code.engine.Resources;
import code.graphics.RenderData;
import code.graphics.RenderLayer;

public final class StyleRenderer {
    public static void renderStyle(Style style, MasterBuffer buffer) {
        if (style.contains("sprite")) {
            renderSprite(style, buffer);
        }
        if (style.contains("text")) {
            TextRenderer.renderText(style, buffer);
        }
        if (style.contains("animation")) {
            renderAnimation(style, buffer);
        }
    }

    private static void renderAnimation(Style style, MasterBuffer buffer) {
        double completion = Double.parseDouble(style.getProperty("completion"));
        RenderData data = Resources.get().getAnimationAtlas().get(style.getProperty("animation"))
                .getDataAt(completion);

        applyTranslation(style, data);
        applyScale(style, data);
        applyRotation(style, data);

        buffer.addNewSprite(RenderLayer.UI, data);
    }

    private static void renderSprite(Style style, MasterBuffer buffer) {
        RenderData data = new RenderData();

        applySprite(style, data);
        applyTranslation(style, data);
        applyScale(style, data);
        applyRotation(style, data);

        buffer.addNewSprite(RenderLayer.UI, data);
    }

    private static void applyTranslation(Style style, RenderData data) {
        double x = Double.parseDouble(style.getProperty("x"));
        double y = Double.parseDouble(style.getProperty("y"));

        data.applyTranslation(new VectorD(x, y));
    }

    private static void applyRotation(Style style, RenderData data) {
        double rot = Double.parseDouble(style.getProperty("rot"));

        data.applyRot(rot);
    }

    private static void applyScale(Style style, RenderData data) {
        double scale = Double.parseDouble(style.getProperty("scale"));

        data.applyScale(new VectorD(scale, scale));
    }

    private static void applySprite(Style style, RenderData data) {
        SpriteData sprite = Resources.get().getSpriteAtlas().get(style.getProperty("sprite"));

        data.setSprite(sprite);
        data.applyScale(sprite.getDim());
    }
}
