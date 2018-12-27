package code.engine;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by theod on 12-7-2017.
 */
public class ItemCatalogue {
    Map<String, ItemProperties> items;
    List<String> industryList;
    Random random;

    public ItemCatalogue(List<ItemProperties> itemProps) {
        items = new HashMap<>();
        for (ItemProperties ip : itemProps) {
            items.put(ip.getName(), ip);
        }
        random = new Random();
        initialize();
    }

    private void initialize() {
        this.industryList = new ArrayList<>();
        for (ItemProperties prop : items.values()) {
            for (int i = 0; i < prop.getSpawnRate(); i++) {
                industryList.add(prop.getIndustry());
            }
        }
    }

    public void addItemProp(ItemProperties ip) {
        items.put(ip.getName(), ip);
        initialize();
    }

    public NEntity produceRandom(List<ItemProperties> options) {
        ItemProperties chosen = options.get(random.nextInt(options.size()));
        return Engine.getEngine().getIndustryMap().get(chosen.getIndustry()).produce();
    }

    public NEntity produceRandom(ItemFilter... filters) {
        List<ItemProperties> chosen = new ArrayList<>();
        for (ItemProperties prop : items.values()) {
            boolean passed = true;
            for (ItemFilter filter : filters) {
                if (!filter.pass(prop)) {
                    passed = false;
                    break;
                }
            }
            if (passed) {
                chosen.add(prop);
            }
        }
        return produceRandom(chosen);
    }

    public ItemProperties getItemProp(String name) {
        return items.get(name);
    }

    public interface ItemFilter {
        boolean pass(ItemProperties prop);
    }
}
