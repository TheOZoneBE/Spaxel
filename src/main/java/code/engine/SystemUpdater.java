package code.engine;

import code.system.GameSystem;
import code.system.RenderSystem;

import java.util.Map;

/**
 * Created by theo on 5-6-2016.
 */
public class SystemUpdater {
    private static final int TWENTY_MS = 20_000_000;
    private Map<SystemType, GameSystem> systems;

    public SystemUpdater() {
        super();
    }

    public void setSystems(Map<SystemType, GameSystem> systems) {
        this.systems = systems;
    }

    /**
     * general update 50 times a second
     *
     * LifeSystem in entities.cleanup UISystem SoundSystem
     *
     */
    public void generalUpdate() {
        Engine.getEngine().getMouseWrapper().update();
        Engine.getEngine().getKeyboard().update();

        if (Engine.getEngine().getGameState() != Engine.GameState.PLAY) {
            systems.get(SystemType.SOUND).update();
            systems.get(SystemType.UI).update();
        } else {
            // TODO clean this up with config or smth
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
            Engine.getEngine().getGameProperties().addTime(TWENTY_MS);
        }
        Engine.getEngine().getNEntityStream().cleanup();
        if (Engine.getEngine().getGameProperties().isLogging()) {
            Engine.getEngine().getLogger().cleanup();
        }
    }

    public void update(SystemType type) {
        if (Engine.getEngine().getGameProperties().isLogging()) {
            Engine.getEngine().getLogger().registerStart(type);
            systems.get(type).update();
            Engine.getEngine().getLogger().registerEnd(type);
        } else {
            systems.get(type).update();
        }
    }

    public void render() {
        ((RenderSystem) Engine.getEngine().getSystem(SystemType.RENDER)).update();
    }

    public void renderloading(LoadingScreen loading) {
        ((RenderSystem) Engine.getEngine().getSystem(SystemType.RENDER)).renderloading(loading);
    }
}
