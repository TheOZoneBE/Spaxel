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
        // System.out.println(style.getProperties());
        if (style.contains("sprite")) {
            renderSprite(style, buffer);
        }
        if (style.contains("text")) {
            TextRenderer.renderText(style, buffer);
        }
    }

    private static void renderSprite(Style style, MasterBuffer buffer) {
        SpriteData sprite = Engine.getEngine().getSpriteAtlas().get(style.getProperty("sprite"));
        double x = Double.valueOf(style.getProperty("x"));
        double y = Double.valueOf(style.getProperty("y"));
        double rot = Double.valueOf(style.getProperty("rot"));
        double scale = Double.valueOf(style.getProperty("scale"));

        RenderData data = new RenderData();
        data.setPos(new VectorD(x, y));
        data.setRot(rot);
        data.setScale(sprite.getDim().multiplicate(scale));
        data.setSpriteSheetID(sprite.getSpritesheetID());
        data.setTexOffset(sprite.getSpriteProperties());
        buffer.addNewSprite(RenderLayer.UI, data);
    }

}
