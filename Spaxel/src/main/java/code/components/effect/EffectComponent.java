package code.components.effect;

import code.components.Component;
import code.components.ComponentType;
import code.components.inventory.InventoryComponent;
import code.components.link.LinkComponent;
import code.engine.Engine;
import code.engine.NEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theod on 28-6-2017.
 */
public class EffectComponent extends Component {
    private List<NEntity> effects;

    public EffectComponent() {
        super(ComponentType.EFFECT);
        effects = new ArrayList<>();
    }

    public List<NEntity> getEffects() {
        return effects;
    }

    public void setEffects(List<NEntity> effects) {
        this.effects = effects;
    }

    public void addCascade(NEntity entity){
        for (NEntity e: effects){
            e.addComponent(new LinkComponent(entity));
            Engine.getEngine().getNEntityStream().addEntity(e);
        }
    }

    public void removeCascade(){
        for (NEntity e: effects){
            Engine.getEngine().getNEntityStream().removeEntity(e);
        }
    }
}
