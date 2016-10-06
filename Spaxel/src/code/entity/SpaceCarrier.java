package code.entity;

import code.graphics.AnimSprite;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;

/**
 * Created by theo on 30-6-2016.
 */
public class SpaceCarrier extends Actor {
    private SpriteData turret;
    private AnimSprite doors;
    private float turretRot;

    public SpaceCarrier(float x, float y, float rot, int health, SpriteData sprite, SpriteData turret, AnimSprite doors, float maxspeed, float acc) {
        super(x, y, rot, health, sprite, maxspeed, acc);
        this.turret = turret;
        this.doors = doors;
        turretRot = 0;
    }

    public void update(){
        //rot -= 0.05;
        turretRot += 0.05;
        if(doors.done()){
            doors.setCurrentFrame(0);
        }
        doors.update();
    }

    public void render(int xPos, int yPos, RenderBuffer render){
        float dx1 = (float)Math.cos(rot+Math.PI/2);
        float dy1 = (float)Math.sin(rot+Math.PI/2);
        float dx2 = (float)Math.cos(rot+Math.PI);
        float dy2 = (float)Math.sin(rot+Math.PI);
        //TODO SpriteData test =new SpriteData(1,1,2,0xffff0000);
        //TODO sprite.render((int) (x + xPos), (int) (y + yPos), rot, render);
        //TODO turret.render((int) (x + dx1*4*5 + dx2*4*21 + xPos), (int) (y + dy1*4*5 + dy2*4*21 + yPos), rot+turretRot, render);
        //TODO doors.render((int) (x + dx1*4*8 + dx2*4*5 + xPos), (int) (y + dy1*4*8 + dy2*4*5 + yPos), rot, render);
        //TODO test.render((int) (x + xPos), (int) (y + yPos), rot, render);
        //test.render((int) (x + dx*4*8 + xPos), (int) (y + dy*4*5 + yPos), rot, render);
    }


}
