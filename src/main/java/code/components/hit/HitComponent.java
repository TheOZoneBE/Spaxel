package code.components.hit;

import code.components.Component;
import code.components.ComponentType;
import code.components.affect.AffectComponent;
import code.components.damage.Damage;
import code.components.damage.DamageComponent;
import code.components.effect.EffectComponent;
import code.components.link.LinkComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.EffectIndustry;
import code.factories.entities.SpawnerIndustry;

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

    public abstract void hit(NEntity entity, NEntity victim);

    public void dealDamage(NEntity victim) {
        DamageComponent dc = (DamageComponent) victim.getComponent(ComponentType.DAMAGE);
        dc.addDamage(new Damage(damage));
    }

    public void addParticleSpawner(NEntity entity, NEntity victim, ParticleComponent particleComponent,
            String spawnerIndustry) {
        SpawnerIndustry hpsi = (SpawnerIndustry) Engine.getEngine().getIndustryMap().get(spawnerIndustry);
        Engine.getEngine().getNEntityStream().addEntity(hpsi
                .produce((PositionComponent) entity.getComponent(ComponentType.POSITION).copy(), particleComponent));
    }

    public void addEffect(NEntity victim, String effectIndustry) {
        EffectComponent ec = (EffectComponent) victim.getComponent(ComponentType.EFFECT);
        EffectIndustry efi = (EffectIndustry) Engine.getEngine().getIndustryMap().get(effectIndustry);
        NEntity effect = efi.produce();
        effect.addComponent(new LinkComponent(victim));
        ((AffectComponent) effect.getComponent(ComponentType.AFFECT)).affect(effect, victim);
        ec.getEffects().add(effect);
        Engine.getEngine().getNEntityStream().addEntity(effect);
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
