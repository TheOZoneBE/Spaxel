package code.components.hit;

import code.components.ComponentType;
import code.components.particle.ParticleComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;

/**
 * Created by theo on 8/07/17.
 */
public class HomingMissileHitComponent extends HitComponent {
    public HomingMissileHitComponent(int damage) {
        super(HitType.HOMING_MISSILE, damage);
    }

    public void hit(NEntity entity, NEntity victim){
        dealDamage(victim);

        SpriteComponent sc = (SpriteComponent)victim.getComponent(ComponentType.SPRITE);
        addParticleSpawner(entity, victim, new ParticleComponent(sc.getSprite().getRandomPart(4,4), sc.getScale()), "missile_hit_particle_spawner_industry");

        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }
}