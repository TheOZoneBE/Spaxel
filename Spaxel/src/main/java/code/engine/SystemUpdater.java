package code.engine;

import code.system.GameSystem;
import code.system.RenderSystem;
import org.lwjgl.system.CallbackI;

import java.awt.*;
import java.util.EnumMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by theo on 5-6-2016.
 */
public class SystemUpdater {
    private EnumMap<SystemType, Thread> threads;
    private EnumMap<SystemType, GameSystem> systems;
    private ExecutorService e = Executors.newCachedThreadPool();

    public SystemUpdater(){
        threads = new EnumMap<>(SystemType.class);
    }

    public void setSystems(EnumMap<SystemType, GameSystem> systems){
        this.systems = systems;
    }

    public class SystemWrapper implements Runnable{
        private GameSystem system;
        private CountDownLatch latch;

        public SystemWrapper(GameSystem system, CountDownLatch latch){
            this.system = system;
            this.latch = latch;
        }
        @Override
        public void run() {
            system.update();
            latch.countDown();
        }
    }

    /**
     * general update 50 times a second
     *
     * LifeSystem in entities.cleanup
     * UISystem
     * SoundSystem
     *
     */
    public void generalUpdate(){
        if (!e.isShutdown()){
            Engine.getEngine().getMouseWrapper().update();
            Engine.getEngine().getKeyboard().update();

            if (Engine.getEngine().getGameState() != Engine.GameState.PLAY){
                systems.get(SystemType.SOUND).update();
                systems.get(SystemType.UI).update();
            }
            else {
                //TODO increment for each new system + temporary, clean this up with config or smth
                systems.get(SystemType.AI).update();
                systems.get(SystemType.SOUND).update();
                systems.get(SystemType.UI).update();
                systems.get(SystemType.SPAWNER).update();
                //TODO new systems
                systems.get(SystemType.AGE).update();
                systems.get(SystemType.VELOCITY).update();
                systems.get(SystemType.DAMAGE).update();
                systems.get(SystemType.HEALTH).update();
                systems.get(SystemType.COOLDOWN).update();
                systems.get(SystemType.HIT).update();
                systems.get(SystemType.INPUT).update();
                systems.get(SystemType.EQUIP).update();
                //Engine.getEngine().temp.update();
            }
            Engine.getEngine().getEntityStream().cleanup();
            Engine.getEngine().getNEntityStream().cleanup();
        }
    }

    public void render(){
        ((RenderSystem) Engine.getEngine().getSystem(SystemType.RENDER)).render();
    }

    public void renderloading(LoadingScreen loading) {
        ((RenderSystem) Engine.getEngine().getSystem(SystemType.RENDER)).renderloading(loading);
    }

    public void shutdown(){
        e.shutdown();
    }
}
