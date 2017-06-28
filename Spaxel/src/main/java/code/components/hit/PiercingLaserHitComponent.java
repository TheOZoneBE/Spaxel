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

/**
 * Created by theod on 28-6-2017.
 */
public class PiercingLaserHitComponent extends HitComponent {
    public PiercingLaserHitComponent(int damage) {
        super(HitType.PIERCING_LASER, damage);
    }

    public void hit(NEntity entity, NEntity victim){
        DamageComponent dc = (DamageComponent)victim.getComponent(ComponentType.DAMAGE);
        SpriteComponent sc = (SpriteComponent)victim.getComponent(ComponentType.SPRITE);
        SpawnerIndustry hpsi = (SpawnerIndustry) Engine.getEngine().getIndustryMap().get("hit_particle_spawner_industry");
        dc.addDamage(new Damage(damage));
        ParticleComponent pac = new ParticleComponent(sc.getSprite().getRandomPart(3,3), sc.getScale());
        Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(
                (PositionComponent)entity.getComponent(ComponentType.POSITION).clone(), pac
        ));
    }
}
