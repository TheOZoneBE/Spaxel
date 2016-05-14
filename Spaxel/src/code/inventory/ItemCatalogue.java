package code.inventory;

import code.engine.EntityType;

import java.util.*;

/**
 * Created by theo on 12-5-2016.
 */
public class ItemCatalogue {
    Map<String, Item> items;
    Random random;

    public ItemCatalogue(){
        items = new HashMap<>();
        random = new Random();
    }

    public void addItem(String name, Item item){
        items.put(name, item);
    }

    public Item getRandomItem(){
        List<Item> temp = new ArrayList<>(items.values());
        return temp.get(random.nextInt(items.size()));
    }

    public Item getItem(String name){
        return items.get(name);
    }
}
