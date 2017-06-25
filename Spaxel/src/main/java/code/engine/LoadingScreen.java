package code.engine;

import code.graphics.MasterBuffer;
import code.ui.UILabel;
import code.graphics.SpriteData;
import code.ui.UIBar;

/**
 * Created by theo on 31-5-2016.
 */
public class LoadingScreen {
    private SpriteData overlay;

    private UIBar progress;

    private UILabel message;

    private UILabel title;

    public LoadingScreen(){
        overlay = new SpriteData(1280, 720, 0xff000000);
        //progress = new UIBar(320,640,640, (float)Math.PI/2, new SpriteData(1,8, 0xffffffff));
        //message = new UILabel(640,680, "",2);
        //title = new UILabel(640, 320, "SPAXEL", 18);
    }

    public UIBar getProgress(){
        return progress;
    }

    public UILabel getMessage(){
        return message;
    }

    public void render(MasterBuffer buffer){
        overlay.renderSprite(0,0,1, 0,1,false, buffer);
        progress.render(buffer);
        message.render(buffer);
        title.render(buffer);

    }
}
