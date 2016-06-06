package code.engine;

import code.graphics.RenderSystem;

import java.awt.*;
import java.util.EnumMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by theo on 5-6-2016.
 */
public class SystemUpdater {
    private EnumMap<SystemType, Thread> threads;
    private ExecutorService e = Executors.newCachedThreadPool();
    private volatile boolean updated = true;

    public SystemUpdater(){
        threads = new EnumMap<>(SystemType.class);
    }

    public void update(){
        updated = false;
        Engine.getEngine().getKeyboard().update();
        if (Engine.getEngine().getGameState() == Engine.GameState.MENU){
            Engine.getEngine().setSystemsToUpdate(3);
            e.execute(threads.get(SystemType.SOUND));
            e.execute(threads.get(SystemType.UI));
            e.execute(threads.get(SystemType.RENDER));
        }
        else {
            Engine.getEngine().setSystemsToUpdate(9);
            e.execute(threads.get(SystemType.PLAYER));
            e.execute(threads.get(SystemType.AI));
            e.execute(threads.get(SystemType.SOUND));
            e.execute(threads.get(SystemType.INVENTORY));
            e.execute(threads.get(SystemType.UI));
            e.execute(threads.get(SystemType.PROJECTILE));
            e.execute(threads.get(SystemType.PARTICLE));
            e.execute(threads.get(SystemType.TRAIL));
            e.execute(threads.get(SystemType.RENDER));
        }
        synchronized (this){
            try{
                while(!updated){
                    this.wait();
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Engine.getEngine().getEntityStream().cleanup();
    }

    public void done(){
        updated = true;
    }

    public void render(){
        ((RenderSystem) Engine.getEngine().getSystem(SystemType.RENDER)).render();
    }

    public void drawText(Graphics g){
        ((RenderSystem) Engine.getEngine().getSystem(SystemType.RENDER)).drawText(g);
    }

    public void addSystem(GameSystem system){
        threads.put(system.getType(),new Thread(system));
    }
}
