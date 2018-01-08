package code.logger;

import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.SystemType;
import code.graphics.MasterBuffer;
import code.math.VectorF;
import code.ui.UILabel;

/**
 * Created by theo on 26/06/17.
 */
public class DebugRenderer {

    public static void renderDebug(MasterBuffer buffer){
        renderEntityStream(buffer);
        if(Engine.getEngine().getGameProperties().isLogging()){
            renderLogger(buffer);
        }
    }

    private static void renderEntityStream(MasterBuffer buffer){
        int x = 100;
        int y = 700;
        for (ComponentType type: ComponentType.values()){
            int size = Engine.getEngine().getNEntityStream().getEntities(type).size();
            UILabel temp = new UILabel();
            temp.setText(type.getName() + ": " + size);
            temp.setPosition(new PositionComponent(new VectorF(x, y), 0));
            temp.setScale(1);
            temp.setAlignLeft(true);
            temp.render(buffer);
            y-=25;
        }
    }

    private static void renderLogger(MasterBuffer buffer){
        int x = 400;
        int y = 500;
        Logger logger = Engine.getEngine().getLogger();
        if(logger.getCurrentAvg() > 0){
            for (SystemType type: SystemType.values()){
                if(type != SystemType.RENDER){
                    long dif = logger.getHistory().get(type).getLast().getDifference() / 1000;
                    long sum = logger.getRollingSum().get(type);
                    long avg = (sum/logger.getCurrentAvg()) / 1000;
                    UILabel temp = new UILabel();
                    temp.setText(type.getName() + ": " + dif + "(" + avg + ")");
                    temp.setPosition(new PositionComponent(new VectorF(x, y), 0));
                    temp.setScale(1);
                    temp.setAlignLeft(true);
                    temp.render(buffer);
                    y-=25;
                }
            }
        }
    }
}
