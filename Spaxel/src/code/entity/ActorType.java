package code.entity;

import code.engine.Engine;
import code.engine.EntityType;
import code.inventory.BasicShield;
import code.inventory.Item;

/**
 * Created by theo on 21-6-2016.
 */
public enum ActorType {
    WHITE{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("white"),20,0.5);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_white"));
            Item i = Engine.getEngine().getItems().getItem("basic_laser");
            Engine.getEngine().getEntityStream().addEntity(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("basic_missile");
            Engine.getEngine().getEntityStream().addEntity(i.getType(),i );
            Engine.getEngine().getEntityStream().addEntity(EntityType.SHIPITEM,new BasicShield(EntityType.SHIPITEM, Engine.getEngine().getSpriteAtlas().get("basic_shield_item"), Engine.getEngine().getSpriteAtlas().get("cooldown_bar"),250, Engine.getEngine().getSpriteAtlas().get("basic_shield_effect"),50));
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    RED{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("red"),20,0.5);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_red"));
            Item i = Engine.getEngine().getItems().getItem("piercing_laser");
            Engine.getEngine().getEntityStream().addEntity(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("homing_missile");
            Engine.getEngine().getEntityStream().addEntity(i.getType(),i );
            Engine.getEngine().getEntityStream().addEntity(EntityType.SHIPITEM,new BasicShield(EntityType.SHIPITEM, Engine.getEngine().getSpriteAtlas().get("basic_shield_item"), Engine.getEngine().getSpriteAtlas().get("cooldown_bar"),250, Engine.getEngine().getSpriteAtlas().get("basic_shield_effect"),50));
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    GREEN{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("green"),20,0.5);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_green"));
            Item i = Engine.getEngine().getItems().getItem("disrupt_laser");
            Engine.getEngine().getEntityStream().addEntity(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("hacking_missile");
            Engine.getEngine().getEntityStream().addEntity(i.getType(),i );
            Engine.getEngine().getEntityStream().addEntity(EntityType.SHIPITEM,new BasicShield(EntityType.SHIPITEM, Engine.getEngine().getSpriteAtlas().get("basic_shield_item"), Engine.getEngine().getSpriteAtlas().get("cooldown_bar"),250, Engine.getEngine().getSpriteAtlas().get("basic_shield_effect"),50));
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    BLUE{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("blue"),20,0.5);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_blue"));
            Item i = Engine.getEngine().getItems().getItem("slowing_laser");
            Engine.getEngine().getEntityStream().addEntity(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("cluster_missile");
            Engine.getEngine().getEntityStream().addEntity(i.getType(),i );
            Engine.getEngine().getEntityStream().addEntity(EntityType.SHIPITEM,new BasicShield(EntityType.SHIPITEM, Engine.getEngine().getSpriteAtlas().get("basic_shield_item"), Engine.getEngine().getSpriteAtlas().get("cooldown_bar"),250, Engine.getEngine().getSpriteAtlas().get("basic_shield_effect"),50));
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    };

    public abstract void initialize();

}
