package code.logger;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.SystemType;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.graphics.SpriteData;
import code.math.VectorD;
import code.ui.elements.UILabel;
import code.Constants;

/**
 * Created by theo on 26/06/17.
 */
public final class DebugRenderer {

    private DebugRenderer() {
    }

    public static void renderDebug(MasterBuffer buffer) {
        renderEntityStream(buffer);
        if (Engine.get().getGameProperties().isLogging()) {
            renderLogger(buffer);
        }
    }

    private static void renderEntityStream(MasterBuffer buffer) {
        int x = 100;
        int y = 700;
        for (ComponentType type : ComponentType.values()) {
            int size = Engine.get().getNEntityStream().getEntities(type).size();
            UILabel temp = new UILabel();
            temp.setText(type.getName() + ": " + size);
            temp.setPosition(new PositionComponent(new VectorD(x, y), 0));
            temp.setScale(1);
            temp.setAlignLeft(true);
            temp.render(buffer);
            y -= 25;
        }
    }

    private static void renderLogger(MasterBuffer buffer) {
        int x = 400;
        int y = 500;
        Logger logger = Engine.get().getLogger();
        if (logger.getCurrentAvg() > 0) {
            for (SystemType type : SystemType.values()) {
                if (type != SystemType.RENDER) {
                    long dif = logger.getHistory().get(type).getLast().getDifference() / 1000;
                    long sum = logger.getRollingSum().get(type);
                    long avg = (sum / logger.getCurrentAvg()) / 1000;
                    UILabel temp = new UILabel();
                    temp.setText(type.getName() + ": " + avg + "(" + dif + ")");
                    temp.setPosition(new PositionComponent(new VectorD(x, y), 0));
                    temp.setScale(1);
                    temp.setAlignLeft(true);
                    temp.render(buffer);
                    y -= 25;
                }
            }
        }
    }

    public void renderDots(MasterBuffer buffer) {
		SpriteData dot = Resources.get().getSpriteAtlas().get("dot");
		VectorD origin = new VectorD(Engine.get().getScreenOffset().getValue(0) % 64,
				Engine.get().getScreenOffset().getValue(1) % 64);
		for (int i = 0; i < Constants.GAME_WIDTH; i += 64) {
			for (int j = 0; j < Constants.GAME_HEIGHT; j += 64) {
				RenderData data = new RenderData();
				data.setPos(origin.sum(new VectorD(i, j)));
				data.setScale(dot.getDim());
				data.setRot(0);
				data.setColor(dot.getColor());
				buffer.addNewSprite(RenderLayer.GAME, data);
			}
		}
	}
}
