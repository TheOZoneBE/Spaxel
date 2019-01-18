package code.components.effect;

import code.components.Component;
import code.components.ComponentType;
import code.engine.Engine;
import code.entity.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by theod on 28-6-2017.
 */
public class EffectComponent extends Component {
    private List<Entity> effects;

    public EffectComponent() {
        super(ComponentType.EFFECT);
        effects = new ArrayList<>();
    }

    public List<Entity> getEffects() {
        return effects;
    }

    public void setEffects(List<Entity> effects) {
        this.effects = effects;
    }

    @Override
    public void addCascade(Entity entity) {
        for (Entity e : effects) {
            // TODO linking ideally somewhere else
            entity.addLink(e);
            Engine.get().getNEntityStream().addEntity(e);
        }
    }

    @Override
    public void removeCascade() {
        for (Entity e : effects) {
            e.destroy();
        }
    }

    public Component copy() {
        return new EffectComponent();
    }
}
