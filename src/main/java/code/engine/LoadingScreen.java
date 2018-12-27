package code.engine;

import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.graphics.MasterBuffer;
import code.math.VectorD;
import code.ui.UILabel;
import code.graphics.SpriteData;
import code.ui.UIBar;
import code.ui.UIVisual;

/**
 * Created by theo on 31-5-2016.
 */
public class LoadingScreen {
    private UIVisual overlay;

    private UIBar progress;

    private UILabel message;

    private UILabel title;

    public LoadingScreen(){
        overlay = new UIVisual();
        overlay.setPosition(new PositionComponent(new VectorD(640,360),0));
        overlay.setSprite(new SpriteComponent(new SpriteData(1280, 720, 0xff000000), 1));
        progress = new UIBar();
        progress.setPosition(new PositionComponent(new VectorD(320, 80), 1));
        progress.setWidth(640);
        progress.setSprite(new SpriteComponent(new SpriteData(1,8, 0xffffffff), 1));
        message = new UILabel();
        message.setPosition(new PositionComponent(new VectorD(640,40), 0));
        message.setScale(2);
        message.setText("");
        title = new UILabel();
        title.setPosition(new PositionComponent(new VectorD(640, 400), 0));
        title.setText("SPAXEL");
        title.setScale(16);
    }

    public UIBar getProgress(){
        return progress;
    }

    public UILabel getMessage(){
        return message;
    }

    public void render(MasterBuffer buffer){
        overlay.render(buffer);
        progress.render(buffer);
        message.render(buffer);
        title.render(buffer);
    }
}
