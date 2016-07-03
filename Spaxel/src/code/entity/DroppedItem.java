package code.entity;

import code.engine.Engine;
import code.engine.EntityType;
import code.graphics.RenderBuffer;
import code.inventory.Item;

/**
 * Created by theo on 3-6-2016.
 */
public class DroppedItem extends Entity{
    private Item item;

    public DroppedItem(double x, double y, Item item, int life) {
        super(x, y);
        this.item = item;
        this.life = life;
    }

    public Item getItem(){
        return item;
    }

    public void update(){
        super.update();
        Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
        if (collision(player)){
            setDead();
            if (Engine.getEngine().getEntityStream().getEntities(item.getType()).contains(item)){
                int index = Engine.getEngine().getEntityStream().getEntities(item.getType()).indexOf(item);
                Item i = (Item)Engine.getEngine().getEntityStream().getEntities(item.getType()).get(index);
                i.setStacks(item.getStacks()+1);
            }
            else {
                Engine.getEngine().getEntityStream().addEntity(item.getType(), item);
            }
        }
    }

    public void render (int xPos, int yPos, RenderBuffer render){
        item.getSprite().render((int) (x + xPos), (int) (y+ yPos), 1, render);
    }
}
