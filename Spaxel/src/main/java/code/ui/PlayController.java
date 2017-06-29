package code.ui;

import code.components.ComponentType;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.components.primary.PrimaryComponent;
import code.components.secondary.SecondaryComponent;
import code.components.ship.ShipComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.factories.uielements.ItemViewFactory;
import code.input.Keyboard;
import code.logger.Logger;
import code.math.VectorF;
import code.system.UISystem;

import java.util.ArrayList;
import java.util.List;

public class PlayController extends Controller{
    UIBar xpBar;
    UIBar hpBar;
    UILabel xpLabel;
    UILabel hpLabel;
    UILabel scoreCounter;
    UIElement primaryContainer;
    UIElement secondaryContainer;
    UIElement shipContainer;
    ItemViewFactory itemViewFactory;

    public void initialize(){
        xpBar = (UIBar)root.findById("play_xp_bar");
        hpBar = (UIBar)root.findById("play_hp_bar");
        xpLabel = (UILabel)root.findById("play_xp_label");
        hpLabel = (UILabel)root.findById("play_hp_label");
        scoreCounter = (UILabel)root.findById("play_score_counter");
        primaryContainer = root.findById("play_primary_container");
        secondaryContainer = root.findById("play_secondary_container");
        shipContainer = root.findById("play_ship_container");
        itemViewFactory = new ItemViewFactory();
    }

    public PlayController() {
        super(UI.PLAY);
    }

    private void updateElements(NEntity player){
        ExperienceComponent ec = (ExperienceComponent)player.getComponent(ComponentType.EXPERIENCE);
        xpBar.setPercent((float)ec.getXp()/(float)ec.getXpToLevel());
        xpLabel.setText(ec.getXp() + " / " + ec.getXpToLevel());
        HealthComponent hc = (HealthComponent)player.getComponent(ComponentType.HEALTH);
        hpBar.setPercent((float)hc.getHealth()/(float)hc.getMaxHealth());
        hpLabel.setText(hc.getHealth() + " / " + hc.getMaxHealth());
        scoreCounter.setText(String.valueOf(Engine.getEngine().getGameProperties().getScore()));
    }

    public void updateItems(NEntity player) {
        PrimaryComponent pc = (PrimaryComponent) player.getComponent(ComponentType.PRIMARY);
        SecondaryComponent sc = (SecondaryComponent) player.getComponent(ComponentType.SECONDARY);
        ShipComponent shc = (ShipComponent) player.getComponent(ComponentType.SHIP);
        //TODO figure out real offsets
        VectorF primOffset = new VectorF(32,700);
        ArrayList<UIElement> primChildren = new ArrayList();
        for (NEntity item : pc.getItems()){
            primChildren.add(itemViewFactory.produce(primOffset, item));
            primOffset.sum(new VectorF(0, -32));
        }
        primaryContainer.setChildren(primChildren);
        VectorF secOffset = new VectorF(1240,700);
        ArrayList<UIElement> secChildren = new ArrayList();
        for (NEntity item : sc.getItems()){
            secChildren.add(itemViewFactory.produce(secOffset, item));
            secOffset.sum(new VectorF(0, -32));
        }
        secondaryContainer.setChildren(secChildren);
        VectorF shipOffset = new VectorF(32,32);
        ArrayList<UIElement> shipChildren = new ArrayList();
        for (NEntity item : shc.getItems()){
            shipChildren.add(itemViewFactory.produce(shipOffset, item));
            shipOffset.sum(new VectorF(32,0));
        }
        shipContainer.setChildren(shipChildren);
    }

    public void update(){
        //TODO update and labels and items
        super.update();
        NEntity player =new ArrayList<>(Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER)).get(0);
        updateElements(player);
        updateItems(player);
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.escState.getState() && !k.escState.getPrevState()){
            Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PAUSE));
            Engine.getEngine().setGameState(Engine.GameState.PAUSE);
        }
        if (k.iState.getState() && !k.iState.getPrevState()){
            Engine.getEngine().getGameProperties().setDebug(!Engine.getEngine().getGameProperties().isDebug());
        }
        if (k.lState.getState() && !k.lState.getPrevState()){
            Engine.getEngine().getGameProperties().setLogging(!Engine.getEngine().getGameProperties().isLogging());
            if (Engine.getEngine().getGameProperties().isLogging()){
                Engine.getEngine().setLogger(new Logger(1000,100));
            }
            else {
                Engine.getEngine().setLogger(null);
            }
        }
    }

}
