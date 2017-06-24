package code.inventory;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;

import java.util.*;

/**
 * Created by theo on 12-5-2016.
 */
public class ItemCatalogue {
    Map<String, ItemProperties> items;
    List<String> industryList;
    Random random;

    public ItemCatalogue(List<ItemProperties> itemProps){
        items = new HashMap<>();
        for (ItemProperties ip: itemProps){
            items.put(ip.getName(), ip);
        }
        random = new Random();
        initialize();
    }

    public void initialize(){
        this.industryList = new ArrayList<>();
        for (String name: items.keySet()){
            for (int i = 0; i< items.get(name).getSpawnRate(); i++){
                industryList.add(items.get(name).getIndustry());
            }
        }
    }

    public void addItemProp(ItemProperties ip){
        items.put(ip.getName(), ip);
        initialize();
    }

    public NEntity produceRandom(){
        List<ItemProperties> temp = new ArrayList<>(items.values());
        ItemProperties chosen = temp.get(random.nextInt(items.size()));
        return Engine.getEngine().getIndustryMap().get(chosen.getIndustry()).produce();
    }

    public ItemProperties getItemProp(String name){
        return items.get(name);
    }
}
