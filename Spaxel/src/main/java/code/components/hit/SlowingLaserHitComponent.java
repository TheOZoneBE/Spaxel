package code.components.hit;

import code.components.ComponentType;
import code.components.affect.AffectComponent;
import code.components.damage.Damage;
import code.components.damage.DamageComponent;
import code.components.effect.EffectComponent;
import code.components.link.LinkComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.EffectIndustry;
import code.factories.entities.SpawnerIndustry;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowingLaserHitComponent extends HitComponent {

    public SlowingLaserHitComponent(int damage) {
        super(HitType.SLOWING_LASER, damage);
    }

    public void hit(NEntity entity, NEntity victim){
        //TODO effect system
        DamageComponent dc = (DamageComponent)victim.getComponent(ComponentType.DAMAGE);
        SpriteComponent sc = (SpriteComponent)victim.getComponent(ComponentType.SPRITE);
        EffectComponent ec = (EffectComponent)victim.getComponent(ComponentType.EFFECT);
        SpawnerIndustry hpsi = (SpawnerIndustry) Engine.getEngine().getIndustryMap().get("hit_particle_spawner_industry");
        dc.addDamage(new Damage(damage));
        ParticleComponent pac = new ParticleComponent(sc.getSprite().getRandomPart(3,3), sc.getScale());
        Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(
                (PositionComponent)entity.getComponent(ComponentType.POSITION).clone(), pac
        ));
        EffectIndustry efi = (EffectIndustry)Engine.getEngine().getIndustryMap().get("slow_effect_industry");
        NEntity effect = efi.produce();
        effect.addComponent(new LinkComponent(victim));
        ((AffectComponent)effect.getComponent(ComponentType.AFFECT)).affect(effect, victim);
        ec.getEffects().add(effect);
        Engine.getEngine().getNEntityStream().addEntity(effect);
        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }
}
