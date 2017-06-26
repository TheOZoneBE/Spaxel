package code.logger;

import code.components.Component;
import code.components.ComponentType;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.SystemType;
import code.graphics.MasterBuffer;
import code.math.VectorF;
import code.ui.UILabel;

import java.util.EnumMap;

/**
 * Created by theo on 26/06/17.
 */
public class DebugRenderer {

    public static void renderDebug(MasterBuffer buffer){
        renderEntityStream(buffer);
        if(Engine.getEngine().getGameProperties().isLogging()){
            //Render log times
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
            temp.render(buffer);
            y-=25;
        }
    }
}
