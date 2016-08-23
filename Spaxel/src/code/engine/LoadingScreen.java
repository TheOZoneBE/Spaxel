package code.engine;

import code.Game;
import code.ui.Label;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.ui.UIBar;

import java.awt.*;

/**
 * Created by theo on 31-5-2016.
 */
public class LoadingScreen {
    private Sprite overlay;

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
        overlay = new Sprite(1280, 720,1, 0xff000000);
        progress = new UIBar(320,640,640, (float)Math.PI/2, new Sprite(1,8,2, 0xffffffff));
        message = new Label(640,680, "",font , 16f);
        buffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
        title = new Label(640, 320, "SPAXEL", font, 128f);
    }

    public UIBar getProgress(){
        return progress;
    }

    public Label getMessage(){
        return message;
    }

    public void render(Graphics g){
        overlay.render(640,360,buffer);
        progress.render(g, buffer);

        for (int i = 0; i < Game.GAME_WIDTH * Game.GAME_HEIGHT; i++) {
            Game.game.pixels[i] = buffer.getPixel(i);
        }
        message.render(g, buffer);
        title.render(g, buffer);

    }
}
