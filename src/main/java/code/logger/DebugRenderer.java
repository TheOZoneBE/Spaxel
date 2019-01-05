package code.logger;

import code.components.ComponentType;
import code.engine.Engine;
import code.engine.SystemType;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.graphics.SpriteData;
import code.math.VectorD;
import code.ui.elements.Element;
import code.Constants;
import code.engine.Resources;

/**
 * Provides methods for the creation of debug ui elements
 * 
 * Created by theo on 26/06/17.
 */
public final class DebugRenderer {
    private static final int DOT_SEPARATION = 64;


    private DebugRenderer() {
    }


    /**
     * Creates the debug ui element
     * 
     * @return the debug ui element
     */
    public static Element createDebugElement() {
        Element debug = new Element();

        for (ComponentType type : ComponentType.values()) {
            Element label = new Element();
            label.setId(type.getName());
            label.setClasses("debug_label");
            int size = Engine.get().getNEntityStream().getEntities(type).size();
            label.setStyleProperty("text", type.getName() + ": " + size);
            debug.addElement(label);
        }
        return debug;
    }


    /**
     * Creates the logging ui element
     * 
     * @return the logging ui element
     */
    public static Element createLoggerElement() {
        Element log = new Element();

        Logger logger = Engine.get().getLogger();

        for (SystemType type : SystemType.values()) {
            if (type != SystemType.RENDER) {
                long dif = logger.getHistory().get(type).getLast().getDifference()
                        / Constants.NS_PER_US;
                long sum = logger.getRollingSum().get(type);
                long avg = (sum / logger.getCurrentAvg()) / Constants.NS_PER_US;
                Element label = new Element();
                label.setId(type.getName());
                label.setClasses("logging_label");
                label.setStyleProperty("text", type.getName() + ": " + avg + "(" + dif + ")");
                log.addElement(label);
            }
        }
        return log;
    }

    /**
     * Render dots on the screen
     * 
     * @param buffer the master buffer of the game
     */
    public void renderDots(MasterBuffer buffer) {
        SpriteData dot = Resources.get().getSpriteAtlas().get("dot");
        VectorD origin = new VectorD(
                Engine.get().getGameState().getScreenOffset().getValue(0) % DOT_SEPARATION,
                Engine.get().getGameState().getScreenOffset().getValue(1) % DOT_SEPARATION);
        for (int i = 0; i < Constants.GAME_WIDTH; i += DOT_SEPARATION) {
            for (int j = 0; j < Constants.GAME_HEIGHT; j += DOT_SEPARATION) {
                RenderData data = new RenderData();
                data.applyTranslation(origin.sum(new VectorD(i, j)));
                data.applyScale(dot.getDim());
                data.applyRot(0);
                data.setColor(dot.getColor());
                buffer.addNewSprite(RenderLayer.GAME, data);
            }
        }
    }
}
