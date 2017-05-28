package code.entity;

import code.engine.EntityType;
import code.graphics.SpriteData;
import code.inventory.Item;
import code.inventory.StatusEffect;

import java.util.*;

/**
 * Created by theo on 27-5-2016.
 */
public class Actor extends Entity {
    protected int health;
    protected int maxHealth;
    protected SpriteData sprite;
    protected float maxspeed;
    protected float turnrate;
    protected float acc;
    protected float xdir;
    protected float ydir;
    protected boolean canshoot;
    protected List<StatusEffect> effects;
    protected Map<EntityType, List<Item>> itemMap;

    public Actor(float x, float y, float rot, int health, SpriteData sprite, float maxspeed, float turnrate, float acc){
        super(x, y, rot);
        this.health = health;
        this.sprite = sprite;
        this.maxspeed = maxspeed;
        this.turnrate = turnrate;
        this.acc = acc;
        maxHealth = health;
        effects = new ArrayList<>();
        itemMap = new HashMap<>();
        itemMap.put(EntityType.MOUSE1ITEM, new ArrayList<>());
        itemMap.put(EntityType.MOUSE3ITEM, new ArrayList<>());
        itemMap.put(EntityType.SHIPITEM, new ArrayList<>());

        xdir =0;
        ydir = 0;
        canshoot = true;
        this.life = -1;
    }

    public void update(){
        super.update();
    }

    public boolean controlSpeed(float dx, float dy) {
        float speed = (float)Math.sqrt(dx * dx + dy * dy);
        return speed < maxspeed;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }

    public void addStatusEffect(StatusEffect effect){
        synchronized (effects){
            effect.affect(this);
            effects.add(effect);
        }
    }



    public SpriteData getSprite(){
        return sprite;
    }

    public float getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(float maxspeed) {
        this.maxspeed = maxspeed;
    }

    public float getAcc() {
        return acc;
    }

    public void setAcc(float acc) {
        this.acc = acc;
    }

    public boolean getCanShoot() {
        return canshoot;
    }

    public void setCanshoot(boolean canshoot) {
        this.canshoot = canshoot;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public List<Item> getItems(EntityType type){
        return itemMap.get(type);
    }

    public Iterator<Item> getItemIterator(EntityType type){
        return new Iterator<Item>() {
            List<Item> iterating = itemMap.get(type);
            int i = -1;
            @Override
            public boolean hasNext() {
                return i < iterating.size() - 1;
            }

            @Override
            public Item next() {
                i++;
                return iterating.get(i);
            }
        };
    }

    public void addItem(EntityType type, Item i){
        itemMap.get(type).add(i);
    }
}
