package code;

import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

import code.engine.Engine;
import code.engine.SystemType;
import code.system.AISystem;
import code.system.AgeSystem;
import code.system.CooldownSystem;
import code.system.DamageSystem;
import code.system.DifficultySystem;
import code.system.EquipSystem;
import code.system.ExperienceSystem;
import code.system.GameSystem;
import code.system.HealthSystem;
import code.system.HitSystem;
import code.system.InputSystem;
import code.system.MarkerSystem;
import code.system.ShipSystem;
import code.system.SoundSystem;
import code.system.SpawnerSystem;
import code.system.UISystem;
import code.system.VelocitySystem;

public class UpdateRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(UpdateRunner.class.getName());
    private volatile boolean running = true;
    private Map<SystemType, GameSystem> systems;

    public UpdateRunner() {
        super();
        systems = new EnumMap<>(SystemType.class);
        // systems
        systems.put(SystemType.SOUND, new SoundSystem());
        systems.put(SystemType.UI, new UISystem());
        systems.put(SystemType.AI, new AISystem());
        systems.put(SystemType.SPAWNER, new SpawnerSystem());
        systems.put(SystemType.AGE, new AgeSystem());
        systems.put(SystemType.VELOCITY, new VelocitySystem());
        systems.put(SystemType.DAMAGE, new DamageSystem());
        systems.put(SystemType.HEALTH, new HealthSystem());
        systems.put(SystemType.COOLDOWN, new CooldownSystem());
        systems.put(SystemType.HIT, new HitSystem());
        systems.put(SystemType.INPUT, new InputSystem());
        systems.put(SystemType.EQUIP, new EquipSystem());
        systems.put(SystemType.EXPERIENCE, new ExperienceSystem());
        systems.put(SystemType.SHIP, new ShipSystem());
        systems.put(SystemType.DIFFICULTY, new DifficultySystem());
        systems.put(SystemType.MARKER, new MarkerSystem());
    }

    public void run() {
        long lastUpdateStart;
        long lastUpdateEnd;
        long accTime = Constants.NS_PER_TICK;
        while (running) {
            lastUpdateStart = System.nanoTime();
            updateSystems();
            accTime -= Constants.NS_PER_TICK;
            lastUpdateEnd = System.nanoTime();
            accTime += lastUpdateEnd - lastUpdateStart;
            if (accTime < Constants.NS_PER_TICK) {
                long sleepTime = (Constants.NS_PER_TICK - accTime) / Constants.NS_PER_MS;
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    LOGGER.log(Level.SEVERE, e.toString(), e);
                    Game.exit();
                    Thread.currentThread().interrupt();
                }
            }
            accTime += System.nanoTime() - lastUpdateEnd;
        }
    }

    public void exit() {
        running = false;
    }

    /**
     * general update 50 times a second
     *
     * LifeSystem in entities.cleanup UISystem SoundSystem
     *
     */
    public void updateSystems() {
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
            Engine.getEngine().getGameProperties().addTime(Constants.NS_PER_TICK);
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
}
