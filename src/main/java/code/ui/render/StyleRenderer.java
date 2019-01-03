package code.ui.render;

import code.ui.styles.Style;
import code.graphics.MasterBuffer;
import code.graphics.SpriteData;
import code.math.VectorD;
import code.engine.Engine;
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
        double completion = Double.valueOf(style.getProperty("completion"));
        SpriteData frame = Engine.getEngine().getAnimationAtlas()
                .get(style.getProperty("animation")).getFrame(completion);
        double x = Double.valueOf(style.getProperty("x"));
        double y = Double.valueOf(style.getProperty("y"));
        double rot = Double.valueOf(style.getProperty("rot"));
        double scale = Double.valueOf(style.getProperty("scale"));

        addRenderData(x, y, rot, scale, frame, buffer);
    }

    private static void renderSprite(Style style, MasterBuffer buffer) {
        SpriteData sprite = Engine.getEngine().getSpriteAtlas().get(style.getProperty("sprite"));
        double x = Double.valueOf(style.getProperty("x"));
        double y = Double.valueOf(style.getProperty("y"));
        double rot = Double.valueOf(style.getProperty("rot"));
        double scale = Double.valueOf(style.getProperty("scale"));

        addRenderData(x, y, rot, scale, sprite, buffer);
    }

    private static void addRenderData(double x, double y, double rot, double scale,
            SpriteData sprite, MasterBuffer buffer) {
        RenderData data = new RenderData();
        data.setPos(new VectorD(x, y));
        data.setRot(rot);
        data.setScale(sprite.getDim().multiplicate(scale));
        data.setSpriteSheetID(sprite.getSpritesheetID());
        data.setTexOffset(sprite.getSpriteProperties());
        buffer.addNewSprite(RenderLayer.UI, data);
    }

}
