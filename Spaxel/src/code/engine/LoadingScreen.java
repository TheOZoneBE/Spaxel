package code.engine;

import code.Game;
import code.ui.Label;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;
import code.ui.UIBar;

import java.awt.*;

/**
 * Created by theo on 31-5-2016.
 */
public class LoadingScreen {
    private SpriteData overlay;

    private UIBar progress;

    private Label message;

    private RenderBuffer buffer;

    private Label title;

    public LoadingScreen(){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/8-bit.ttf"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //TODO scale in entity
        overlay = new SpriteData(1280, 720, 0xff000000);
        //scale 2
        progress = new UIBar(320,640,640, (float)Math.PI/2, new SpriteData(1,8, 0xffffffff));
        message = new Label(640,680, "",2);
        //buffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        title = new Label(640, 320, "SPAXEL", 18);
    }

    public UIBar getProgress(){
        return progress;
    }

    public Label getMessage(){
        return message;
    }

    public void render(Graphics g){
        //overlay.renderSprite(0,0,1, 0,1,false, buffer);
        //progress.render(g, buffer);

        for (int i = 0; i < Game.GAME_WIDTH * Game.GAME_HEIGHT; i++) {
            //Game.game.pixels[i] = buffer.getPixel(i);
        }
        //message.render(g, buffer);
        //title.render(g, buffer);

    }
}
