package code.entity;

import code.engine.Engine;
import code.engine.EntityType;
import code.inventory.*;

/**
 * Created by theo on 21-6-2016.
 */
public enum ActorType {
    //TODO see if still necessary
    /*
    WHITE{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("white"),20,.25f, 0.5f);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_white"));
            Item i = Engine.getEngine().getItems().getItem("basic_laser");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("basic_missile");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("active_shield");
            player.addItem(i.getType(), i);
            player.update();
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    RED{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100000, Engine.getEngine().getSpriteAtlas().get("red"),20,.25f, 0.5f);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_red"));
            Item i = Engine.getEngine().getItems().getItem("piercing_laser");
            i.setStacks(5);
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("homing_missile");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("force_shield");
            player.addItem(i.getType(), i);
            player.update();
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    GREEN{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("green"),20, .25f, 0.5f);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_green"));
            Item i = Engine.getEngine().getItems().getItem("disrupt_laser");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("hacking_missile");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("anti_shield");
            player.addItem(i.getType(), i);
            player.update();
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    },
    BLUE{
        @Override
        public void initialize(){
            Player player = new Player(0, 0, 0, 100, Engine.getEngine().getSpriteAtlas().get("blue"),20,.25f,0.5f);
            player.setHitShape(Engine.getEngine().getHitShapeAtlas().get("hitshape_blue"));
            Item i = Engine.getEngine().getItems().getItem("slowing_laser");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("cluster_missile");
            player.addItem(i.getType(), i);
            i = Engine.getEngine().getItems().getItem("basic_shield");
            player.addItem(i.getType(), i);
            player.update();
            Engine.getEngine().getEntityStream().addEntity(EntityType.PLAYER, player);
        }
    };

    public abstract void initialize();
*/
}
