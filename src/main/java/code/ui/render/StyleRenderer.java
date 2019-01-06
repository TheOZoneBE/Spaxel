package code.ui.render;

import code.ui.styles.Style;
import code.graphics.MasterBuffer;
import code.graphics.SpriteData;
import code.math.VectorD;
import code.engine.Resources;
import code.graphics.RenderData;
import code.graphics.RenderLayer;

/**
 * Provides methods for rendering style configurations of UI Elements
 */
public final class StyleRenderer {
    private StyleRenderer() {

    }

    /**
     * Render a style configuration
     * 
     * @param style  the style of the element
     * @param buffer the masterbuffer of the rendersystem
     */
    public static void renderStyle(Style style, MasterBuffer buffer) {
        if (!style.contains("visible") || "true".equals(style.getProperty("visible"))) {
            VectorD position = derivePosition(style);
            double rot = deriveRotation(style);
            double scale = deriveScale(style);
            if (style.contains("sprite")) {
                renderSprite(position, rot, scale, style, buffer);
            }
            if (style.contains("text")) {
                TextRenderer.renderText(position, style, buffer);
            }
            if (style.contains("animation")) {
                renderAnimation(position, rot, scale, style, buffer);
            }
        }
    }

    private static VectorD derivePosition(Style style) {
        switch (style.getProperty("position")) {
            case "relative":
                return new VectorD(Double.parseDouble(style.getProperty("x")),
                        Double.parseDouble(style.getProperty("y")))
                                .sum(derivePosition(style.getParent()));
            case "flow":
                return deriveFlowPosition(style);
            case "absolute":
            default:
                return new VectorD(Double.parseDouble(style.getProperty("x")),
                        Double.parseDouble(style.getProperty("y")));

        }
    }

    private static VectorD deriveFlowPosition(Style style) {
        VectorD parentPos = derivePosition(style.getParent());
        int index = style.getElement().getIndex();
        int cols = Integer.parseInt(style.getProperty("cols"));
        int rowOffset = Integer.parseInt(style.getProperty("row-offset"));
        int colOffset = Integer.parseInt(style.getProperty("col-offset"));
        int x = index % cols;
        int y = index / cols;
        return parentPos.sum(new VectorD(x * colOffset, y * rowOffset));
    }

    private static double deriveRotation(Style style) {
        switch (style.getProperty("rotation")) {
            case "relative":
                return deriveRotation(style.getParent())
                        + Double.parseDouble(style.getProperty("rot"));
            case "absolute":
            default:
                return Double.parseDouble(style.getProperty("rot"));
        }
    }

    private static double deriveScale(Style style) {
        return Double.parseDouble(style.getProperty("scale"));
    }

    private static void renderAnimation(VectorD position, double rot, double scale, Style style,
            MasterBuffer buffer) {
        double completion = Double.parseDouble(style.getProperty("completion"));
        RenderData data = Resources.get().getAnimationAtlas().get(style.getProperty("animation"))
                .getDataAt(completion);
        data.applyTranslation(position);
        data.applyRot(rot);
        data.applyScale(scale);

        buffer.addNewSprite(RenderLayer.UI, data);
    }

    private static void renderSprite(VectorD position, double rot, double scale, Style style,
            MasterBuffer buffer) {
        RenderData data = new RenderData();
        SpriteData sprite = Resources.get().getSpriteAtlas().get(style.getProperty("sprite"));

        data.setSprite(sprite);
        data.applyTranslation(position);
        data.applyScale(scale);
        data.applyRot(rot);

        buffer.addNewSprite(RenderLayer.UI, data);
    }
}
