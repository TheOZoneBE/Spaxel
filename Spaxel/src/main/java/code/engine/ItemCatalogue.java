package code.engine;

import code.engine.Engine;
import code.engine.EntityType;
import code.engine.NEntity;

import javax.swing.text.html.ListView;
import java.util.*;

/**
 * Created by theod on 12-7-2017.
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

    public NEntity produceRandom(List<ItemProperties> options){
        ItemProperties chosen = options.get(random.nextInt(options.size()));
        return Engine.getEngine().getIndustryMap().get(chosen.getIndustry()).produce();
    }

    public NEntity produceRandom(ItemFilter... filters){
        List<ItemProperties> chosen = new ArrayList<>();
        for (ItemProperties prop : items.values()){
            boolean passed = true;
            for (ItemFilter filter: filters){
                if(!filter.pass(prop)){
                    passed = false;
                    break;
                }
            }
            if (passed){
                chosen.add(prop);
            }
        }
        return produceRandom(chosen);
    }

    public ItemProperties getItemProp(String name){
        return items.get(name);
    }

    public interface ItemFilter {
        boolean pass(ItemProperties prop);
    }
}
