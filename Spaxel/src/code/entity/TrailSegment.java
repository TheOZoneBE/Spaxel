package code.entity;

import code.graphics.RenderBuffer;
import javafx.scene.control.TreeItem;

/**
 * Created by theo on 18-5-2016.
 */
public class TrailSegment extends Entity{
    private int color;
    private TrailSegment previous;
    private int life;
    private boolean alive;

    public TrailSegment(double x, double y, double rot, int color, TrailSegment previous){
        super(x,y, rot);
        this.color = color;
        this.previous = previous;
        life = 100;
        alive = true;
    }

    public void update(){
        if (life > 0){
            life--;
        }
        else {
            setDead();
        }

    }

    public void setDead(){
        alive = false;
    }

    public boolean isAlive(){
        return alive;
    }

    public void render(int xPos, int yPos, RenderBuffer render){
        if (previous != null){
            int steps = 50;
            double dx1 = Math.sin(rot+Math.PI);
            double dy1 = Math.cos(rot+Math.PI);
            double dx2 = Math.sin(previous.getRot());
            double dy2 = Math.cos(previous.getRot());
            double xMid = (x - previous.getX())/(dx2-dx1);
            double yMid = (y - previous.getY())/(dy2-dy1);
            double x1Step = (xMid - previous.getX())/steps;
            double y1Step = (yMid - previous.getY())/steps;
            double x2Step = (x - xMid)/steps;
            double y2Step = (y - yMid)/steps;
            for (int i = 0; i < steps; i++){
                double xStep = ((xMid + i*x2Step) - (previous.getX() + i*x1Step))/steps;
                double yStep = ((yMid + i*y2Step) - (previous.getY() + i*y1Step))/steps;
                render.setPixel((int)(previous.getX() + i*x1Step + i*xStep) +xPos,(int)(previous.getY() + i*y1Step + i*yStep)+yPos, color);
            }
        }
    }
}
