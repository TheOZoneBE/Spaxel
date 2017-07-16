package code.graphics;

/**
 * Created by theo on 30-6-2016.
 */
public class AnimSprite {
    private int nrOfFrames;
    private SpriteData[] frames;
    private int currentFrame;
    private int frameDuration;
    private int frameTime;
    private boolean pause;

    public AnimSprite(int nrOfFrames,int frameDuration, SpriteData[] frames){
        this.nrOfFrames = nrOfFrames;
        this.frameDuration = frameDuration;
        this.frames = frames;
        currentFrame = 0;
        frameTime = 0;
        pause = false;
    }

    public AnimSprite(int duration, Spritesheet sheet, int width, int height, int nrRows, int nrColumns){

    }

    public void pause(){
        pause = true;
    }

    public void play(){
        pause = false;
    }

    public boolean done(){
        return currentFrame == nrOfFrames-1;
    }

    public void setCurrentFrame(int currentFrame){
        this.currentFrame = currentFrame;
    }

    public void update(){
        if(!pause){
            if (currentFrame != nrOfFrames - 1){
                if (frameTime == frameDuration){
                    frameTime = 0;
                    currentFrame++;
                }
                frameTime++;
            }
        }
    }

    /*
    //TODO rework
    public void renderSprite(int x, int y, int scale, float rot, float transparency, boolean blur, MasterBuffer render){
        frames[currentFrame].renderSprite(x, y, scale, rot, transparency, blur, render);
    }
    */

}
