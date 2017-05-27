package code.ui;

import code.graphics.MasterBuffer;
import code.graphics.SpriteData;

/**
 * Created by theod on 26-11-2016.
 */
public class UIClassSelect extends UIButton {

    public UIClassSelect(int x, int y, Label label, String clickAction, SpriteData sprite, SpriteData hover, SpriteData clicked, SpriteData locked) {
        super(x, y, label, clickAction, sprite, hover, clicked, locked);
    }

    public void render(MasterBuffer render){
        sprite.renderSprite((int)x,(int)y,4, 0, 1, false ,render);
        if (click){
            clicked.renderSprite((int)x,(int)y,4,0,1, false, render);
            label.render(render);
        }
        else if (hovering){
            hover.renderSprite((int)x,(int)y,4, 0,1,false, render);
            label.render(render);
        }

    }
}
