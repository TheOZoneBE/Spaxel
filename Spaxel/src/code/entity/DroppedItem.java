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
    private int life;

    public DroppedItem(double x, double y, Item item, int life) {
        super(x, y);
        this.item = item;
        this.life = life;
    }

    public Item getItem(){
        return item;
    }

    public void update(){
        life--;
        if (life != 0){
            super.update();
            Entity player = Engine.getEngine().getEntityStream().getEntities(EntityType.PLAYER).get(0);
            if (collision(player)){
                setDead();
                if (Engine.getEngine().getEntityStream().getEntities(item.getType()).contains(item)){
                    item.setStacks(item.getStacks()+1);
                }
                else {
                    Engine.getEngine().getEntityStream().addEntity(item.getType(), item);
                }
                Engine.getEngine().getEntityStream().releaseLock(item.getType());
            }
            Engine.getEngine().getEntityStream().releaseLock(EntityType.PLAYER);
        }
        else {
            setDead();
        }
    }

    public void render (int xPos, int yPos, RenderBuffer render){
        item.getSprite().render((int) (x + xPos), (int) (y+ yPos), 1, render);
    }
}
