package code.entity;

import code.graphics.RenderBuffer;
import code.graphics.SpriteData;

/**
 * Created by theo on 18-5-2016.
 */
public class TrailSegment extends Entity{
    private SpriteData trail;
    private TrailSegment previous;
    private float maxLife;

    public TrailSegment(float x, float y, float rot, SpriteData trail, TrailSegment previous){
        super(x,y, rot);
        this.trail = trail;
        this.previous = previous;
        life = 50;
        maxLife = life;
    }

    public void update(){
    }

    public void render(int xPos, int yPos, RenderBuffer render){
        if (previous != null){
            int steps = 4;
            if (rot == previous.getRot()){
                float xStep = (x - previous.getX())/steps;
                float yStep = (y - previous.getY())/steps;
                for (int i = 0; i < steps; i++){
                    //TODO trail.render((int)(previous.getX() + i*xStep) +xPos,(int)(previous.getY() + i*yStep)+yPos, render,1 - (life/maxLife)/2);
                }
            }
            else {
                float dx1 = (float)Math.sin(rot+Math.PI);
                float dy1 = (float)Math.cos(rot+Math.PI);
                float dx2 = (float)Math.sin(previous.getRot());
                float dy2 = (float)Math.cos(previous.getRot());
                float j  = (y*dx1 - previous.getY()*dx1 + previous.getX()*dy1 - x*dy1)/(dx1*dy2 - dx2*dy1);
                float xMid = previous.getX() + j*dx2;
                float yMid = previous.getY() + j*dy2;
                float x1Step = (xMid - previous.getX())/steps;
                float y1Step = (yMid - previous.getY())/steps;
                float x2Step = (x - xMid)/steps;
                float y2Step = (y - yMid)/steps;
                for (int i = 1; i <= steps; i++){
                    float xStep = ((xMid + i*x2Step) - (previous.getX() + i*x1Step))/steps;
                    float yStep = ((yMid + i*y2Step) - (previous.getY() + i*y1Step))/steps;
                    //TODO trail.render((int)(previous.getX() + i*x1Step + i*xStep) +xPos,(int)(previous.getY() + i*y1Step + i*yStep)+yPos, render,1 - (life/maxLife)/2);
                }
            }
        }
    }
}
