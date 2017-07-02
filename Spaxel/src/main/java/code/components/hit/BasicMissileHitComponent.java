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
 * Created by theod on 2-7-2017.
 */
public class BasicMissileHitComponent extends HitComponent {
    public BasicMissileHitComponent(int damage) {
        super(HitType.BASIC_MISSILE, damage);
    }

    public void hit(NEntity entity, NEntity victim){
        DamageComponent dc = (DamageComponent)victim.getComponent(ComponentType.DAMAGE);
        SpriteComponent sc = (SpriteComponent)victim.getComponent(ComponentType.SPRITE);
        SpawnerIndustry hpsi = (SpawnerIndustry) Engine.getEngine().getIndustryMap().get("missile_hit_particle_spawner_industry");
        dc.addDamage(new Damage(damage));
        ParticleComponent pac = new ParticleComponent(sc.getSprite().getRandomPart(4,4), sc.getScale());
        Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(
                (PositionComponent)entity.getComponent(ComponentType.POSITION).clone(), pac
        ));
        Engine.getEngine().getNEntityStream().removeEntity(entity);
    }
}
