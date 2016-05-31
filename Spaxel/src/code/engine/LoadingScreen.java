package code.engine;

import code.Game;
import code.entity.Label;
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

    public LoadingScreen(){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/8-bit.ttf"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        overlay = new Sprite(1280, 720,1, 0xff00ffff);
        progress = new UIBar(320,640,640, Math.PI/2, new Sprite(1,8,2, 0xffffffff));
        message = new Label(640,680, "",font , 16f);
        buffer = new RenderBuffer(Game.GAME_WIDTH, Game.GAME_HEIGHT);
    }

    public UIBar getProgress(){
        return progress;
    }

    public Label getMessage(){
        return message;
    }

    public void update(){
        overlay.render(640,360,buffer);
        progress.render(buffer);
    }

    public void render(){
        for (int i = 0; i < Game.GAME_WIDTH * Game.GAME_HEIGHT; i++) {
            Game.game.pixels[i] = buffer.getPixel(i);
        }
    }

    public void drawText(Graphics g){
        message.render(g);
    }
}
