package code.ui.controllers;

import java.util.ArrayList;
import code.components.ComponentType;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.uielements.ItemViewFactory;
import code.input.Keyboard;
import code.logger.Logger;
import code.math.VectorD;
import code.ui.elements.UIType;
import code.ui.elements.UIElement;
import code.ui.elements.Element;

public final class PlayController {

    public static void updateTime(Element element) {
        int gt = Engine.getEngine().getGameProperties().getGameTime();
        int min = gt / 60;
        int sec = gt % 60;
        String mintext = min < 10 ? "0" + min : "" + min;
        String sectext = sec < 10 ? "0" + sec : "" + sec;
        element.setStyleProperty("text", mintext + "\\" + sectext);
    }

    public static void updateScore(Element element) {
        element.setStyleProperty("text",
                String.valueOf(Engine.getEngine().getGameProperties().getScore()));
    }

    public static void updateHpLabel(Element element) {
        HealthComponent hc = (HealthComponent) Engine.getEngine().getNEntityStream().getPlayer()
                .getComponent(ComponentType.HEALTH);
        element.setStyleProperty("text", hc.getHealth() + " / " + hc.getMaxHealth());
    }

    public static void updateHpBar(Element element) {
        HealthComponent hc = (HealthComponent) Engine.getEngine().getNEntityStream().getPlayer()
                .getComponent(ComponentType.HEALTH);
        element.setStyleProperty("completion",
                String.valueOf((double) hc.getHealth() / hc.getMaxHealth()));
    }

    public static void updateXpLabel(Element element) {
        ExperienceComponent ec = (ExperienceComponent) Engine.getEngine().getNEntityStream()
                .getPlayer().getComponent(ComponentType.EXPERIENCE);
        element.setStyleProperty("text", ec.getXp() + " / " + ec.getXpToLevel());
    }

    public static void updateXpBar(Element element) {
        ExperienceComponent ec = (ExperienceComponent) Engine.getEngine().getNEntityStream()
                .getPlayer().getComponent(ComponentType.EXPERIENCE);
        element.setStyleProperty("completion",
                String.valueOf((double) ec.getXp() / ec.getXpToLevel()));
    }

    public static void updatePrimaryContainer(Element element) {
        InventoryComponent inventory = (InventoryComponent) Engine.getEngine().getNEntityStream()
                .getPlayer().getComponent(ComponentType.PRIMARY);
        VectorD primOffset = new VectorD(40, 680);
        ArrayList<UIElement> primChildren = new ArrayList<>();
        for (NEntity item : pc.getItems()) {
            primChildren.add(itemViewFactory.produce(primOffset, item));
            primOffset = primOffset.sum(new VectorD(0, -72));
        }
        primaryContainer.setChildren(primChildren);
    }

    public static void updateSecondaryContainer(Element element) {
        InventoryComponent inventory = (InventoryComponent) Engine.getEngine().getNEntityStream()
                .getPlayer().getComponent(ComponentType.SECONDARY);
        VectorD secOffset = new VectorD(1240, 680);
        ArrayList<UIElement> secChildren = new ArrayList<>();
        for (NEntity item : sc.getItems()) {
            secChildren.add(itemViewFactory.produce(secOffset, item));
            secOffset = secOffset.sum(new VectorD(0, -72));
        }
        secondaryContainer.setChildren(secChildren);
    }

    public static void updateShipContainer(Element element) {
        InventoryComponent inventory = (InventoryComponent) Engine.getEngine().getNEntityStream()
                .getPlayer().getComponent(ComponentType.SHIP);
        VectorD shipOffset = new VectorD(388, 40);
        ArrayList<UIElement> shipChildren = new ArrayList<>();
        for (NEntity item : shc.getItems()) {
            shipChildren.add(itemViewFactory.produce(shipOffset, item));
            shipOffset = shipOffset.sum(new VectorD(72, 0));
        }
        shipContainer.setChildren(shipChildren);
    }

    @Override
    public void update() {
        // TODO cleanup logging
        super.update();
        NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
        updateElements(player);
        updateItems(player);
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.getEscState().isDown() && !k.getEscState().hasBeenDown()) {
            Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.PAUSE));
            Engine.getEngine().setGameState(Engine.GameState.PAUSE);
        }
        if (k.getiState().isDown() && !k.getiState().hasBeenDown()) {
            Engine.getEngine().getGameProperties()
                    .setDebug(!Engine.getEngine().getGameProperties().isDebug());
        }
        if (k.getlState().isDown() && !k.getlState().hasBeenDown()) {
            Engine.getEngine().getGameProperties()
                    .setLogging(!Engine.getEngine().getGameProperties().isLogging());
            if (Engine.getEngine().getGameProperties().isLogging()) {
                Engine.getEngine().setLogger(new Logger(1000, 100));
            } else {
                Engine.getEngine().setLogger(null);
            }
        }
    }

    public static void resume() {
        Engine.getEngine().setGameState(Engine.GameState.PLAY);
        Engine.getEngine().getCurrentUI().findById("pause_controls").setStyleProperty("visible",
                "false");
    }

    public static void quit() {
        Engine.getEngine().setCurrentUI(Engine.getEngine().getUIS().get(UIType.MAIN));
        Engine.getEngine().setGameState(Engine.GameState.MENU);
        Engine.getEngine().stopGame();
    }

    public static void escCheck() {
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.getEscState().isRelease()) {
            if (Engine.getEngine().getGameState() == Engine.GameState.PAUSE) {
                resume();
            } else {
                Engine.getEngine().setGameState(Engine.GameState.PAUSE);
                Engine.getEngine().getCurrentUI().findById("pause_controls")
                        .setStyleProperty("visible", "true");
            }
        }
    }

}
