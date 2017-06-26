package code.ui;

import code.components.ComponentType;
import code.components.experience.ExperienceComponent;
import code.components.health.HealthComponent;
import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;
import code.engine.SystemType;
import code.input.Keyboard;
import code.system.UISystem;

import java.util.ArrayList;

public class PlayController extends Controller{
    UIBar xpBar;
    UIBar hpBar;
    UILabel xpLabel;
    UILabel hpLabel;
    UILabel scoreCounter;

    public void initialize(){
        xpBar = (UIBar)root.findById("play_xp_bar");
        hpBar = (UIBar)root.findById("play_hp_bar");
        xpLabel = (UILabel)root.findById("play_xp_label");
        hpLabel = (UILabel)root.findById("play_hp_label");
        scoreCounter = (UILabel)root.findById("play_score_counter");
    }

    public PlayController() {
        super(UI.PLAY);
    }

    private void updateElements(){
        NEntity player =new ArrayList<>(Engine.getEngine().getNEntityStream().getEntities(EntityType.PLAYER)).get(0);
        //TODO experience
        ExperienceComponent ec = (ExperienceComponent)player.getComponent(ComponentType.EXPERIENCE);
        xpBar.setPercent((float)ec.getXp()/(float)ec.getXpToLevel());
        xpLabel.setText(ec.getXp() + " / " + ec.getXpToLevel());
        HealthComponent hc = (HealthComponent)player.getComponent(ComponentType.HEALTH);
        hpBar.setPercent((float)hc.getHealth()/(float)hc.getMaxHealth());
        hpLabel.setText(hc.getHealth() + " / " + hc.getMaxHealth());
        scoreCounter.setText(String.valueOf(Engine.getEngine().getGameProperties().getScore()));
    }

    public void update(){
        //TODO update score and labels and items
        super.update();
        updateElements();
        Keyboard k = Engine.getEngine().getKeyboard();
        if (k.escState.getState() && !k.escState.getPrevState()){
            Engine.getEngine().setController(Engine.getEngine().getUIAtlas().get(UI.PAUSE));
            Engine.getEngine().setGameState(Engine.GameState.PAUSE);
        }
    }

}
