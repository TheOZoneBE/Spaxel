package code.engine;

import code.system.GameSystem;
import code.system.RenderSystem;

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
                //TODO clean this up with config or smth
                update(SystemType.AI);
                update(SystemType.SOUND);
                update(SystemType.SPAWNER);
                update(SystemType.AGE);
                update(SystemType.VELOCITY);
                update(SystemType.SHIP);
                update(SystemType.DAMAGE);
                update(SystemType.HEALTH);
                update(SystemType.COOLDOWN);
                update(SystemType.HIT);
                update(SystemType.INPUT);
                update(SystemType.EQUIP);
                update(SystemType.EXPERIENCE);
                update(SystemType.UI);
                update(SystemType.DIFFICULTY);
                update(SystemType.MARKER);
                Engine.getEngine().getGameProperties().addTime(20000000);
                //Engine.getEngine().temp.update();
            }
            Engine.getEngine().getNEntityStream().cleanup();
            if(Engine.getEngine().getGameProperties().isLogging()){
                Engine.getEngine().getLogger().cleanup();
            }
        }
    }

    public void update(SystemType type){
        if (Engine.getEngine().getGameProperties().isLogging()){
            Engine.getEngine().getLogger().registerStart(type);
            systems.get(type).update();
            Engine.getEngine().getLogger().registerEnd(type);
        }
        else {
            systems.get(type).update();
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
