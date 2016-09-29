package code.entity;

import code.engine.Engine;
import code.engine.EntityType;
import code.graphics.RenderBuffer;
import code.graphics.SpriteData;
import code.inventory.StatusEffect;
import code.projectiles.BasicLaser;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Actor {
    private int cooldown;

    public Enemy(float x, float y, float rot, int health, SpriteData sprite, float maxspeed, float acc) {
        super(x, y, rot, health, sprite, maxspeed,acc);
    }

    public void updateAI(Player player){
        List<StatusEffect> todelete = new ArrayList<>();
        synchronized (effects){
            for (StatusEffect e : effects) {
                e.update();
                if (!e.isAlive()) {
                    e.undo(this);
                    todelete.add(e);
                }
            }
            effects.removeAll(todelete);
        }

        if (health < 0) {
            alive = false;
        } else {
            rot = (float)(Math.PI + Math.atan2(player.getX() - x, player.getY() - y));
            float dx = 0;
            float dy = 0;

            dx =(float)-Math.sin(rot) * acc;
            dy = (float)-Math.cos(rot) * acc;

            float dist = distanceTo(player);
            if (dist > 250) {
                if (controlSpeed(xdir + dx, ydir + dy)) {
                    xdir += dx;
                    ydir += dy;
                } else {
                    xdir = xdir - xdir / (maxspeed * 2) + dx;
                    ydir = ydir - ydir / (maxspeed * 2) + dy;
                }
            } else {
                xdir -= dx;
                ydir -= dy;
            }
            if (canshoot && cooldown == 0) {
                Engine.getEngine().getEntityStream().addEntity(EntityType.ENEMY_PROJECTILE, new BasicLaser(x, y, rot, Engine.getEngine().getSpriteAtlas().get("basic_laser_projectile"), Engine.getEngine().getSpriteAtlas().get("white_trail"), 5, 100, 20));
                cooldown =100;
            }
        }
        cooldown--;
    }

    public void update() {
        x += xdir*Engine.getEngine().getUpdateTime();
        y += ydir*Engine.getEngine().getUpdateTime();
        super.update();
    }

    @Override
    public void render(int xPos, int yPos, RenderBuffer render) {
        //TODO sprite.render((int) (x + xPos), (int) (y + yPos), rot, render);
    }

}
