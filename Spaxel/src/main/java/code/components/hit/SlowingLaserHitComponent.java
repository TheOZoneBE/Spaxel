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
import code.util.SpriteDataUtil;

/**
 * Created by theod on 28-6-2017.
 */
public class SlowingLaserHitComponent extends HitComponent {

    public SlowingLaserHitComponent(int damage) {
        super(HitType.SLOWING_LASER, damage);
    }

    public void hit(NEntity entity, NEntity victim){
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent)victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, victim,new ParticleComponent(SpriteDataUtil.getRandomPart(sc.getSprite(),3,3), sc.getScale()), "laser_hit_particle_spawner_industry");

        addEffect(victim, "slow_effect_industry");

        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }
}
