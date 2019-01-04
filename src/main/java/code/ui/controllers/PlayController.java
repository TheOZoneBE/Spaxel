package code.ui.controllers;

import code.components.ComponentType;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.components.inventory.InventoryComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.Resources;
import code.factories.elements.ElementCreator;
import code.input.Keyboard;
import code.ui.elements.Element;
import code.ui.elements.UIType;

/**
 * Callbacks for the elements in the play UI.
 */
public final class PlayController {
    private static final int SECONDS_INA_MIN = 60;
    private static final int TWO_FIGURE = 10;
    private static final String VISIBLE = "visible";

    private PlayController() {
    }

    /**
     * Update the time label while playing the game
     * 
     * @param element the label element
     */
    public static void updateTime(Element element) {
        int gt = Engine.get().getGameState().getGameTime();
        int min = gt / SECONDS_INA_MIN;
        int sec = gt % SECONDS_INA_MIN;
        String mintext = min < TWO_FIGURE ? "0" + min : "" + min;
        String sectext = sec < TWO_FIGURE ? "0" + sec : "" + sec;
        element.setStyleProperty("text", mintext + "\\" + sectext);
    }

    /**
     * Update the score label while playing the game
     * 
     * @param element the score label
     */
    public static void updateScore(Element element) {
        element.setStyleProperty("text", String.valueOf(Engine.get().getGameState().getScore()));
    }

    /**
     * Update the HP bar element while playing the game
     * 
     * @param element the hp bar element
     */
    public static void updateHpBar(Element element) {
        HealthComponent hc = (HealthComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.HEALTH);

        element.setStyleProperty("completion",
                String.valueOf((double) hc.getHealth() / hc.getMaxHealth()));
        element.findById("hp_label").setStyleProperty("text",
                hc.getHealth() + " / " + hc.getMaxHealth());
    }

    /**
     * Update the XP bar elemeent while playing the game
     * 
     * @param element the xp bar element
     */
    public static void updateXpBar(Element element) {
        ExperienceComponent ec = (ExperienceComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.EXPERIENCE);
        element.setStyleProperty("completion",
                String.valueOf((double) ec.getXp() / ec.getXpToLevel()));

        element.findById("xp_label").setStyleProperty("text",
                ec.getXp() + " / " + ec.getXpToLevel());
    }

    private static void updateContainer(Element element, InventoryComponent inventory) {
        element.clearChildren();
        for (NEntity item : inventory.getItems()) {

            element.addElement(ElementCreator.createItemView(item));
        }
        element.initStyle(element.getElementStyle());
    }

    /**
     * Update the container showing all Primary items
     * 
     * @param element the container
     */
    public static void updatePrimaryContainer(Element element) {
        updateContainer(element, (InventoryComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.PRIMARY));
    }

    /**
     * Update the container showing all Secondary items
     * 
     * @param element the container
     */
    public static void updateSecondaryContainer(Element element) {
        updateContainer(element, (InventoryComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.SECONDARY));
    }


    /**
     * Update the container showing all Ship items
     * 
     * @param element the container
     */
    public static void updateShipContainer(Element element) {
        updateContainer(element, (InventoryComponent) Engine.get().getNEntityStream().getPlayer()
                .getComponent(ComponentType.SHIP));
    }

    /**
     * Checks whether the log key is pressed and makes the logger visible if that is the case.
     */
    public static void logCheck() {
        Keyboard k = Engine.get().getKeyboard();

        if (k.getlState().isRelease()) {
            Engine.get().getGameState().setLogging(Engine.get().getGameState().isLogging());
            Engine.get().getCurrentUI().findById("logging_overlay").setStyleProperty(VISIBLE,
                    String.valueOf(Engine.get().getGameState().isLogging()));
        }
    }

    /**
     * Checks whether the debug key is pressed and makes the debug screen visible if that is the
     * case
     */
    public static void debugCheck() {
        Keyboard k = Engine.get().getKeyboard();

        if (k.getiState().isRelease()) {
            Engine.get().getGameState().setDebug(!Engine.get().getGameState().isDebug());
            Engine.get().getCurrentUI().findById("debug_overlay").setStyleProperty(VISIBLE,
                    String.valueOf(Engine.get().getGameState().isDebug()));
        }
    }

    /**
     * Resume playing the game
     */
    public static void resume() {
        Engine.get().setEngineState(Engine.EngineState.PLAY);
        Engine.get().getCurrentUI().findById("pause_controls").setStyleProperty(VISIBLE, "false");
    }

    /**
     * Quit playing the game
     */
    public static void quit() {
        Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.MAIN));
        Engine.get().setEngineState(Engine.EngineState.MENU);
        Engine.get().stopGame();
    }

    /**
     * Checks if the esc key is pressed and toggles the pause menu if that is the case
     */
    public static void escCheck() {
        Keyboard k = Engine.get().getKeyboard();
        if (k.getEscState().isRelease()) {
            if (Engine.get().getEngineState() == Engine.EngineState.PAUSE) {
                resume();
            } else {
                Engine.get().setEngineState(Engine.EngineState.PAUSE);
                Engine.get().getCurrentUI().findById("pause_controls").setStyleProperty(VISIBLE,
                        "true");
            }
        }
    }

}
