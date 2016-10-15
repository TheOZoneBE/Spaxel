package code.entity;

import code.engine.Engine;
import code.engine.EntityType;
import code.inventory.*;

/**
 * Created by theo on 21-6-2016.
 */
public enum ActorType {
    WHITE{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("white"),20,0.5f);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_white"));
            Item i = Engine.getEngine().getItems().getItem("basic_laser");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("basic_missile");
            player.addItem(i.getType(), i);
            i = new ActiveShield(EntityType.SHIPITEM,"active_shield", Engine.getEngine().getSpriteAtlas().get("active_shield_item"), Engine.getEngine().getSpriteAtlas().get("cooldown_bar"),250, Engine.getEngine().getSpriteAtlas().get("active_shield_effect"),50);
            player.addItem(i.getType(), i);
            player.update();
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    RED{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100000, Engine.getEngine().getSpriteAtlas().get("red"),20,0.5f);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_red"));
            Item i = Engine.getEngine().getItems().getItem("piercing_laser");
            i.setStacks(5);
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("homing_missile");
            player.addItem(i.getType(), i);
            i = new ForceShield(EntityType.SHIPITEM,"force_shield", Engine.getEngine().getSpriteAtlas().get("force_shield_item"), Engine.getEngine().getSpriteAtlas().get("cooldown_bar"),250, Engine.getEngine().getSpriteAtlas().get("force_shield_effect"),50);
            player.addItem(i.getType(), i);
            player.update();
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    GREEN{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("green"),20,0.5f);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_green"));
            Item i = Engine.getEngine().getItems().getItem("disrupt_laser");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("hacking_missile");
            player.addItem(i.getType(), i);
            i = new AntiShield(EntityType.SHIPITEM, "anti_shield",Engine.getEngine().getSpriteAtlas().get("anti_shield_item"), Engine.getEngine().getSpriteAtlas().get("cooldown_bar"),250, Engine.getEngine().getSpriteAtlas().get("anti_shield_effect"),50);
            player.addItem(i.getType(), i);
            player.update();
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    BLUE{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("blue"),20,0.5f);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_blue"));
            Item i = Engine.getEngine().getItems().getItem("slowing_laser");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("cluster_missile");
            player.addItem(i.getType(), i);
            i = new BasicShield(EntityType.SHIPITEM, "basic_shield",Engine.getEngine().getSpriteAtlas().get("basic_shield_item"), Engine.getEngine().getSpriteAtlas().get("cooldown_bar"),250, Engine.getEngine().getSpriteAtlas().get("basic_shield_effect"),50);
            player.addItem(i.getType(), i);
            player.update();
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    };

    public abstract void initialize();

}
