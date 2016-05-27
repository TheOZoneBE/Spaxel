package code.entity;

import code.engine.Engine;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import javafx.scene.control.TreeItem;

/**
 * Created by theo on 18-5-2016.
 */
public class TrailSegment extends Entity{
    private Sprite trail;
    private TrailSegment previous;
    private int life;
    private double maxLife;

    public TrailSegment(double x, double y, double rot, Sprite trail, TrailSegment previous){
        super(x,y, rot);
        this.trail = trail;
        this.previous = previous;
        life = 50;
        maxLife = life;
    }

    public void update(){
        if (life > 0){
            life--;
        }
        else {
            setDead();
        }

    }

    public void render(int xPos, int yPos, RenderBuffer render){
        if (previous != null){
            int steps = 6;
            if (rot == previous.getRot()){
                double xStep = (x - previous.getX())/steps;
                double yStep = (y - previous.getY())/steps;
                for (int i = 0; i < steps; i++){
                    trail.render((int)(previous.getX() + i*xStep) +xPos,(int)(previous.getY() + i*yStep)+yPos, render,1 - (life/maxLife)/2);
                }
            }
            else {
                double dx1 = Math.sin(rot+Math.PI);
                double dy1 = Math.cos(rot+Math.PI);
                double dx2 = Math.sin(previous.getRot());
                double dy2 = Math.cos(previous.getRot());
                double j  = (y*dx1 - previous.getY()*dx1 + previous.getX()*dy1 - x*dy1)/(dx1*dy2 - dx2*dy1);
                double xMid = previous.getX() + j*dx2;
                double yMid = previous.getY() + j*dy2;
                double x1Step = (xMid - previous.getX())/steps;
                double y1Step = (yMid - previous.getY())/steps;
                double x2Step = (x - xMid)/steps;
                double y2Step = (y - yMid)/steps;
                for (int i = 0; i < steps; i++){
                    double xStep = ((xMid + i*x2Step) - (previous.getX() + i*x1Step))/steps;
                    double yStep = ((yMid + i*y2Step) - (previous.getY() + i*y1Step))/steps;
                    trail.render((int)(previous.getX() + i*x1Step + i*xStep) +xPos,(int)(previous.getY() + i*y1Step + i*yStep)+yPos, render,1 - (life/maxLife)/2);
                }
            }
        }
    }
}
