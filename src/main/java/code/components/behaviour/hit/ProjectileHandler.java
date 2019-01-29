package code.components.behaviour.hit;

import code.components.ComponentType;
import code.components.storage.damage.Damage;
import code.components.storage.damage.DamageStorage;
import code.components.storage.projectile.ProjectileStorage;
import code.components.storage.renderable.RenderableStorage;
import code.components.storage.transformation.TransformationStorage;
import code.engine.Engine;
import code.engine.Resources;
import code.entity.Entity;
import code.factories.entities.EffectIndustry;
import code.factories.entities.SpawnerIndustry;
import code.graphics.texture.Texture;
import code.util.SpriteDataUtil;

public abstract class ProjectileHandler extends HitHandler {
    public ProjectileHandler(HitType type) {
        super(type);
    }

    public abstract void payload(Entity entity, Entity victim, ProjectileStorage projStore);

    public void hit(Entity entity, Entity victim) {
        if (victim.hasComponent(ComponentType.DAMAGE) && victim != entity.getParent()) {
            ProjectileStorage projStore =
                    (ProjectileStorage) entity.getComponent(ComponentType.PROJECTILE);
            dealDamage(victim, projStore.getDamage());
            addParticleSpawner(entity, victim, projStore.getOnHitSpawner(),
                    projStore.getParticleSize());
            payload(entity, victim, projStore);
        }
    }

    public void dealDamage(Entity victim, int damage) {
        DamageStorage dc = (DamageStorage) victim.getComponent(ComponentType.DAMAGE);
        dc.addDamage(new Damage(damage));
    }

    public void addParticleSpawner(Entity entity, Entity victim, String particleSpawner,
            int particleSize) {
        RenderableStorage rndrStore =
                (RenderableStorage) victim.getComponent(ComponentType.RENDERABLE);
        SpawnerIndustry hpsi =
                (SpawnerIndustry) Resources.get().getIndustryMap().get(particleSpawner);
        RenderableStorage particle = new RenderableStorage(SpriteDataUtil
                .getRandomPart((Texture) rndrStore.getRenderable(), particleSize, particleSize));
        Engine.get().getNEntityStream().addEntity(hpsi.produce(
                (TransformationStorage) entity.getComponent(ComponentType.TRANSFORMATION).copy(),
                particle));
    }

    public void addEffect(Entity victim, String effectIndustry) {
        EffectIndustry efi = (EffectIndustry) Resources.get().getIndustryMap().get(effectIndustry);
        Entity effect = efi.produce();
        victim.addLink(effect);
        Engine.get().getNEntityStream().addEntity(effect);
    }
}
