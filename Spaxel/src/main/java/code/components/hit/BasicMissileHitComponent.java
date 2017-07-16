package code.components.hit;

import code.components.ComponentType;
import code.components.damage.Damage;
import code.components.damage.DamageComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.SpawnerIndustry;
import code.util.SpriteDataUtil;

/**
 * Created by theod on 2-7-2017.
 */
public class BasicMissileHitComponent extends HitComponent {
    public BasicMissileHitComponent(int damage) {
        super(HitType.BASIC_MISSILE, damage);
    }

    public void hit(NEntity entity, NEntity victim){
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent)victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, victim, new ParticleComponent(SpriteDataUtil.getRandomPart(sc.getSprite(),4,4), sc.getScale()), "missile_hit_particle_spawner_industry");

        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }
}
