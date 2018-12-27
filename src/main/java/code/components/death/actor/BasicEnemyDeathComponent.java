package code.components.death.actor;

import code.components.ComponentType;
import code.components.Component;
import code.components.age.AgeComponent;
import code.components.ai.DroppedItemAIComponent;
import code.components.death.DeathComponent;
import code.components.death.DeathType;
import code.components.equip.EquipComponent;
import code.components.experience.ExperienceComponent;
import code.components.item.ItemComponent;
import code.components.particle.ParticleComponent;
import code.components.position.PositionComponent;
import code.components.primary.PrimaryComponent;
import code.components.secondary.SecondaryComponent;
import code.components.ship.ShipComponent;
import code.components.sprite.SpriteComponent;
import code.engine.Engine;
import code.engine.NEntity;
import code.factories.entities.SpawnerIndustry;
import code.util.SpriteDataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by theo on 24/06/17.
 */
public class BasicEnemyDeathComponent extends DeathComponent {
    Random random;

    public BasicEnemyDeathComponent() {
        super(DeathType.BASIC_ENEMY);
        random = new Random();
    }

    public void die(NEntity entity) {
        SpawnerIndustry hpsi = (SpawnerIndustry) Engine.getEngine().getIndustryMap()
                .get("enemy_death_particle_spawner_industry");
        SpriteComponent esc = (SpriteComponent) entity.getComponent(ComponentType.SPRITE);
        PositionComponent epc = (PositionComponent) entity.getComponent(ComponentType.POSITION);
        ParticleComponent pac = new ParticleComponent(SpriteDataUtil.getRandomPart(esc.getSprite(), 6, 6),
                esc.getScale());
        // add particle effect
        Engine.getEngine().getNEntityStream().addEntity(hpsi.produce(epc, pac));

        Engine.getEngine().getGameProperties().addScore(100);
        // add experience
        NEntity player = Engine.getEngine().getNEntityStream().getPlayer();
        ExperienceComponent exp = (ExperienceComponent) player.getComponent(ComponentType.EXPERIENCE);
        exp.setXp(exp.getXp() + 25);
        // chance of dropping item
        if (random.nextInt(100) < 25) {
            NEntity item = Engine.getEngine().getItems()
                    .produceRandom(prop -> getAllItemNames(entity).contains(prop.getName()));
            item.addComponent(new EquipComponent());
            item.addComponent(new AgeComponent(500, 500));
            item.addComponent(epc.copy());
            item.addComponent(entity.getComponent(ComponentType.VELOCITY));
            item.addComponent(entity.getComponent(ComponentType.RENDER));
            item.addComponent(new DroppedItemAIComponent());
            Engine.getEngine().getNEntityStream().addEntity(item);
        }
    }

    private List<String> getAllItemNames(NEntity entity) {
        List<String> items = new ArrayList<>();
        PrimaryComponent pc = (PrimaryComponent) entity.getComponent(ComponentType.PRIMARY);
        SecondaryComponent sc = (SecondaryComponent) entity.getComponent(ComponentType.SECONDARY);
        ShipComponent shc = (ShipComponent) entity.getComponent(ComponentType.SHIP);
        for (NEntity e : pc.getItems()) {
            items.add(((ItemComponent) e.getComponent(ComponentType.ITEM)).getName());
        }
        for (NEntity e : sc.getItems()) {
            items.add(((ItemComponent) e.getComponent(ComponentType.ITEM)).getName());
        }
        for (NEntity e : shc.getItems()) {
            items.add(((ItemComponent) e.getComponent(ComponentType.ITEM)).getName());
        }
        return items;
    }

    public Component copy() {
        return new BasicEnemyDeathComponent();
    }
}
