package code.ui.controllers;

import java.util.ArrayList;
import code.components.ComponentType;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.components.inventory.InventoryComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.engine.Resources;
import code.input.Keyboard;
import code.logger.Logger;
import code.math.VectorD;
import code.ui.elements.Element;
import code.ui.elements.UIType;

public final class PlayController {

    public static void updateTime(Element element) {
        int gt = Engine.get().getGameProperties().getGameTime();
        int min = gt / 60;
        int sec = gt % 60;
        String mintext = min < 10 ? "0" + min : "" + min;
        String sectext = sec < 10 ? "0" + sec : "" + sec;
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

    public static void updatePrimaryContainer(Element element) {
        InventoryComponent inventory = (InventoryComponent) Engine.get().getNEntityStream()
                .getPlayer().getComponent(ComponentType.PRIMARY);
        VectorD primOffset = new VectorD(40, 680);
        ArrayList<Element> primChildren = new ArrayList<>();
        for (NEntity item : inventory.getItems()) {
            // TODO primChildren.add(itemViewFactory.produce(primOffset, item));
            primOffset = primOffset.sum(new VectorD(0, -72));
        }
        // primaryContainer.setChildren(primChildren);
    }

    public static void updateSecondaryContainer(Element element) {
        InventoryComponent inventory = (InventoryComponent) Engine.get().getNEntityStream()
                .getPlayer().getComponent(ComponentType.SECONDARY);
        VectorD secOffset = new VectorD(1240, 680);
        ArrayList<Element> secChildren = new ArrayList<>();
        for (NEntity item : inventory.getItems()) {
            // TODO secChildren.add(itemViewFactory.produce(secOffset, item));
            secOffset = secOffset.sum(new VectorD(0, -72));
        }
        // secondaryContainer.setChildren(secChildren);
    }

    public static void updateShipContainer(Element element) {
        InventoryComponent inventory = (InventoryComponent) Engine.get().getNEntityStream()
                .getPlayer().getComponent(ComponentType.SHIP);
        VectorD shipOffset = new VectorD(388, 40);
        ArrayList<Element> shipChildren = new ArrayList<>();
        for (NEntity item : inventory.getItems()) {
            // TODO shipChildren.add(itemViewFactory.produce(shipOffset, item));
            shipOffset = shipOffset.sum(new VectorD(72, 0));
        }
        // shipContainer.setChildren(shipChildren);
    }

    public void update() {
        // TODO cleanup logging

        NEntity player = Engine.get().getNEntityStream().getPlayer();
        Keyboard k = Engine.get().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            Engine.get().setCurrentUI(Resources.get().getUIS().get(UIType.PAUSE));
            Engine.get().setGameState(Engine.EngineState.PAUSE);
        }
        if (k.getiState().isDown() && !k.getiState().hasBeenDown()) {
            Engine.get().getGameProperties().setDebug(!Engine.get().getGameProperties().isDebug());
        }
        if (k.getlState().isDown() && !k.getlState().hasBeenDown()) {
            Engine.get().getGameProperties()
                    .setLogging(!Engine.get().getGameProperties().isLogging());
            if (Engine.get().getGameProperties().isLogging()) {
                Engine.get().setLogger(new Logger(1000, 100));
            } else {
                Engine.get().setLogger(null);
            }
        }
    }

    public static void resume() {
        Engine.get().setGameState(Engine.EngineState.PLAY);
        Engine.get().getCurrentUI().findById("pause_controls").setStyleProperty("visible", "false");
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
                Engine.get().getCurrentUI().findById("pause_controls").setStyleProperty("visible",
                        "true");
            }
        }
    }

}
