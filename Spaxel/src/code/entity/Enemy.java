package code.entity;

import code.engine.Engine;
import code.engine.EntityType;
import code.graphics.RenderBuffer;
import code.graphics.Sprite;
import code.inventory.StatusEffect;
import code.projectiles.BasicLaser;
import code.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends Actor {
    private int cooldown;

    public Enemy(double x, double y, double rot, int health, Sprite sprite, double maxspeed, double acc) {
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
            rot = Math.PI + Math.atan2(player.getX() - x, player.getY() - y);
            double dx = 0;
            double dy = 0;

            dx = -Math.sin(rot) * acc;
            dy = -Math.cos(rot) * acc;

            double dist = distanceTo(player);
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
        sprite.render((int) (x + xPos), (int) (y + yPos), rot, render);
    }

}
