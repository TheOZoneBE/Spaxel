package code.components.hit;

import code.components.Component;
import code.components.ComponentType;
import code.components.affect.AffectComponent;
import code.components.storage.damage.Damage;
import code.components.storage.damage.DamageStorage;
import code.components.effect.EffectComponent;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Engine;
import code.entity.Entity;
import code.factories.entities.EffectIndustry;
import code.factories.entities.SpawnerIndustry;
import code.engine.Resources;

/**
 * Created by theo on 18/06/17.
 */
public abstract class HitComponent extends Component {
    private HitType hitType;
    protected int damage;

    public HitComponent(HitType hitType, int damage) {
        super(ComponentType.HIT);
        this.hitType = hitType;
        this.damage = damage;
    }

    public abstract void hit(Entity entity, Entity victim);

    public void dealDamage(Entity victim) {
        DamageStorage dc = (DamageStorage) victim.getComponent(ComponentType.DAMAGE);
        dc.addDamage(new Damage(damage));
    }

    public void addParticleSpawner(Entity entity, RenderableStorage particleComponent,
            String spawnerIndustry) {
        SpawnerIndustry hpsi =
                (SpawnerIndustry) Resources.get().getIndustryMap().get(spawnerIndustry);
        Engine.get().getNEntityStream().addEntity(hpsi.produce(
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION).copy(),
                particleComponent));
    }

    public void addEffect(Entity victim, String effectIndustry) {
        EffectComponent ec = (EffectComponent) victim.getComponent(ComponentType.EFFECT);
        EffectIndustry efi = (EffectIndustry) Resources.get().getIndustryMap().get(effectIndustry);
        Entity effect = efi.produce();
        victim.addLink(effect);
        ((AffectComponent) effect.getComponent(ComponentType.AFFECT)).affect(effect, victim);
        ec.getEffects().add(effect);
        Engine.get().getNEntityStream().addEntity(effect);
    }

    public HitType getHitType() {
        return hitType;
    }

    public void setHitType(HitType hitType) {
        this.hitType = hitType;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
