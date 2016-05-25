package code.entity;

import code.engine.Engine;
import code.graphics.RenderBuffer;
import javafx.scene.control.TreeItem;

/**
 * Created by theo on 18-5-2016.
 */
public class TrailSegment extends Entity{
    private int color;
    private TrailSegment previous;
    private int life;

    public TrailSegment(double x, double y, double rot, int color, TrailSegment previous){
        super(x,y, rot);
        this.color = color;
        this.previous = previous;
        life = 50;
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
            int steps = 32;
            if (rot == previous.getRot()){
                double xStep = (x - previous.getX())/steps;
                double yStep = (y - previous.getY())/steps;
                for (int i = 0; i < steps; i++){
                    render.setPixel((int)(previous.getX() + i*xStep) +xPos,(int)(previous.getY() + i*yStep)+yPos, color);

                    //proof of concept to use sprites
                    //Engine.getEngine().getSpriteAtlas().get("hp_bar").render((int)(previous.getX() + i*xStep) +xPos,(int)(previous.getY() + i*yStep)+yPos,rot + Math.PI/2, render);
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
                    render.setPixel((int)(previous.getX() + i*x1Step + i*xStep) +xPos,(int)(previous.getY() + i*y1Step + i*yStep)+yPos, color);

                    //proof of concept to use sprites
                    //Engine.getEngine().getSpriteAtlas().get("hp_bar").render((int)(previous.getX() + i*x1Step + i*xStep) +xPos,(int)(previous.getY() + i*y1Step + i*yStep)+yPos,Math.atan2(xStep, yStep)+Math.PI/2, render);
                }
            }
        }
    }
}
