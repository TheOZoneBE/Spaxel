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
            if (player.collision(this)){
                setDead();
                Engine.getEngine().getEntityStream().addEntity(item.getType(), item);
            }
        }
        else {
            setDead();
        }
    }

    public void render (int xPos, int yPos, RenderBuffer render){
        updHitShape.render(xPos, yPos, render);
        item.getSprite().render((int) (x + xPos), (int) (y+ yPos), 1, render);
    }
}
