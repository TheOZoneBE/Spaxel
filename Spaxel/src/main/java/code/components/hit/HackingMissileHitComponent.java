package code.components.hit;

import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.util.SpriteDataUtil;

/**
 * Created by theo on 8/07/17.
 */
public class HackingMissileHitComponent extends HitComponent {
    public HackingMissileHitComponent(int damage) {
        super(HitType.HACKING_MISSILE, damage);
    }

    public void hit(NEntity entity, NEntity victim){
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent)victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, victim,new ParticleComponent(SpriteDataUtil.getRandomPart(sc.getSprite(),4,4), sc.getScale()), "missile_hit_particle_spawner_industry");

        addEffect(victim, "disable_shoot_effect_industry");

        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }
}
