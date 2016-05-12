package code.inventory;

import code.engine.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by theo on 12-5-2016.
 */
public class ItemCatalogue {
    List<Item> items;
    Random random;

    public ItemCatalogue(){
        items = new ArrayList<>();
        random = new Random();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public Item getRandomItem(){
        return items.get(random.nextInt(items.size()));
    }
}
