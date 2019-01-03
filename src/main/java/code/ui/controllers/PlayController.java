package code.ui.controllers;

import code.components.ComponentType;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.components.inventory.InventoryComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.Resources;
import code.factories.uielements.ElementCreator;
import code.input.Keyboard;
import code.ui.elements.Element;
import code.ui.elements.UIType;

public final class PlayController {
    private static final int SECONDS_INA_MIN = 60;
    private static final int TWO_FIGURE = 10;
    private static final String VISIBLE = "visible";

    private PlayController() {
    }

    public static void updateTime(Element element) {
        int gt = Engine.get().getGameProperties().getGameTime();
        int min = gt / SECONDS_INA_MIN;
        int sec = gt % SECONDS_INA_MIN;
        String mintext = min < TWO_FIGURE ? "0" + min : "" + min;
        String sectext = sec < TWO_FIGURE ? "0" + sec : "" + sec;
        element.setStyleProperty("text", mintext + "\\" + sectext);
    }

    public static void updateScore(Element element) {
        element.setStyleProperty("text",
                String.valueOf(Engine.get().getGameProperties().getScore()));
    }

    public static void updateHpLabel(Element element) {
        HealthComponent hc = (HealthComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.HEALTH);
        element.setStyleProperty("text", hc.getHealth() + " / " + hc.getMaxHealth());
    }

    public static void updateHpBar(Element element) {
        HealthComponent hc = (HealthComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.HEALTH);
        element.setStyleProperty("completion",
                String.valueOf((double) hc.getHealth() / hc.getMaxHealth()));
    }

    public static void updateXpLabel(Element element) {
        ExperienceComponent ec = (ExperienceComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.EXPERIENCE);
        element.setStyleProperty("text", ec.getXp() + " / " + ec.getXpToLevel());
    }

    public static void updateXpBar(Element element) {
        ExperienceComponent ec = (ExperienceComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.EXPERIENCE);
        element.setStyleProperty("completion",
                String.valueOf((double) ec.getXp() / ec.getXpToLevel()));
    }

    private static void updateContainer(Element element, InventoryComponent inventory) {
        element.clearChildren();
        for (NEntity item : inventory.getItems()) {

            element.addElement(ElementCreator.createItemView(item));
        }
        element.initStyle(element.getElementStyle());
    }

    public static void updatePrimaryContainer(Element element) {
        updateContainer(element, (InventoryComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.PRIMARY));
    }

    public static void updateSecondaryContainer(Element element) {
        updateContainer(element, (InventoryComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.SECONDARY));
    }

    public static void updateShipContainer(Element element) {
        updateContainer(element, (InventoryComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.SHIP));
    }

    public static void logCheck() {
        Keyboard k = Engine.get().getKeyboard();

        if (k.getlState().isRelease()) {
            Engine.get().getGameProperties()
                    .setLogging(Engine.get().getGameProperties().isLogging());
            Engine.get().getCurrentUI().findById("logging_overlay").setStyleProperty(VISIBLE,
                    String.valueOf(Engine.get().getGameProperties().isLogging()));
        }
    }

    public static void debugCheck() {
        Keyboard k = Engine.get().getKeyboard();

        if (k.getiState().isRelease()) {
            Engine.get().getGameProperties().setDebug(!Engine.get().getGameProperties().isDebug());
            Engine.get().getCurrentUI().findById("debug_overlay").setStyleProperty(VISIBLE,
                    String.valueOf(Engine.get().getGameProperties().isDebug()));
        }
    }

    public static void resume() {
        Engine.get().setGameState(Engine.EngineState.PLAY);
        Engine.get().getCurrentUI().findById("pause_controls").setStyleProperty(VISIBLE, "false");
    }

    public static void quit() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
        Engine.get().setGameState(Engine.EngineState.MENU);
        Engine.get().stopGame();
    }

    public static void escCheck() {
        Keyboard k = Engine.get().getKeyboard();
        if (k.getEscState().isRelease()) {
            if (Engine.get().getGameState() == Engine.EngineState.PAUSE) {
                resume();
            } else {
                Engine.get().setGameState(Engine.EngineState.PAUSE);
                Engine.get().getCurrentUI().findById("pause_controls").setStyleProperty(VISIBLE,
                        "true");
            }
        }
    }

}
