package code.ui.logic;

import code.collision.HitShape;
import code.collision.HitPoint;
import code.engine.Engine;
import code.input.MouseWrapper;
import code.math.VectorD;
import code.math.MatrixD;
import code.ui.elements.Element;
import code.ui.styles.Style;
import code.util.MatrixUtil;
import code.engine.Resources;

public class HoverLogic implements Logic {
    public void execute(Element element) {
        Style style = element.getElementStyle();
        if (style.contains("hit-shape")) {
            HitShape hitbox =
                    Resources.get().getHitShapeAtlas().get(style.getProperty("hit-shape"));
            if (style.getProperty("x") == null) {
                int i = 0;
                System.out.println(style.getProperties());
            }
            double x = Double.valueOf(style.getProperty("x"));
            double y = Double.valueOf(style.getProperty("y"));
            double rot = Double.valueOf(style.getProperty("rot"));
            double width = Double.valueOf(style.getProperty("width"));
            double height = Double.valueOf(style.getProperty("height"));

            MatrixD transform = MatrixUtil.getTransformationMatrix(new VectorD(x, y), rot,
                    new VectorD(width, height));

            MouseWrapper mouse = Engine.get().getMouseWrapper();
            int mouseX = mouse.getX();
            int mouseY = mouse.getY();

            HitShape updated = hitbox.update(transform);
            boolean inside = updated.collision(
                    new HitShape(new HitPoint(new VectorD(new double[] {mouseX, mouseY, 0}))));

            element.getState().setHover(inside);
        }

    }
}
