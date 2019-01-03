package code.components.effect;

import code.components.Component;
import code.components.ComponentType;
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

    @Override
    public void addCascade(NEntity entity) {
        for (NEntity e : effects) {
            e.addComponent(new LinkComponent(entity));
            Engine.get().getNEntityStream().addEntity(e);
        }
    }

    @Override
    public void removeCascade() {
        for (NEntity e : effects) {
            Engine.get().getNEntityStream().removeEntity(e);
        }
    }

    public Component copy() {
        return new EffectComponent();
    }
}
