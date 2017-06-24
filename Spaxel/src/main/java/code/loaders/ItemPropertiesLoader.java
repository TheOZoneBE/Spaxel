package code.loaders;

import code.engine.EntityType;
import code.factories.ProjectileFactory;
import code.graphics.SpriteData;
import code.inventory.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

public class ItemPropertiesLoader extends Loader {

    public ItemCatalogue loadItems(String path){
        try {
            super.loadFile(path);
            ObjectMapper mapper = new ObjectMapper();
            List<ItemProperties> itemProperties = mapper.readValue(file, new TypeReference<List<ItemProperties>>(){});
            return new ItemCatalogue(itemProperties);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;

        //TODO cleanup
        /*
        ItemCatalogue items = new ItemCatalogue();
        NodeList nodelist = doc.getElementsByTagName("primaryWeapon");
        Element mouse1 = (Element)nodelist.item(0);
        nodelist = mouse1.getElementsByTagName("projectileItem");
        for(int i = 0; i < nodelist.getLength(); i++){
            Element nextChild = (Element)nodelist.item(i);
            String name = nextChild.getElementsByTagName("name").item(0).getTextContent();
            String sprite = nextChild.getElementsByTagName("sprite").item(0).getTextContent();
            int cooldown = Integer.parseInt((nextChild.getElementsByTagName("cooldown").item(0).getTextContent()));
            String factory = nextChild.getElementsByTagName("factory").item(0).getTextContent();
            String projectile = nextChild.getElementsByTagName("projectile").item(0).getTextContent();
            String trail = nextChild.getElementsByTagName("trail").item(0).getTextContent();
            int damage = Integer.parseInt((nextChild.getElementsByTagName("damage").item(0).getTextContent()));
            int life = Integer.parseInt((nextChild.getElementsByTagName("life").item(0).getTextContent()));
            int speed =  Integer.parseInt((nextChild.getElementsByTagName("speed").item(0).getTextContent()));
            try {
                Constructor constructor = Class.forName(factory).getConstructors()[0];
                ProjectileFactory projFac = (ProjectileFactory)constructor.newInstance(spriteAtlas.get(projectile), spriteAtlas.get(trail),damage, life, speed);
                Item item = new ProjectileItem(EntityType.MOUSE1ITEM, name, spriteAtlas.get(sprite), spriteAtlas.get("cooldown_bar"),cooldown, projFac);
                items.addItem(name, item);
            }
            catch (Exception e){
                System.out.println("error");
            }
        }
        nodelist = doc.getElementsByTagName("secondaryWeapon");
        Element mouse3 = (Element)nodelist.item(0);
        nodelist = mouse3.getElementsByTagName("projectileItem");
        for(int i = 0; i < nodelist.getLength(); i++){
            Element nextChild = (Element)nodelist.item(i);
            String name = nextChild.getElementsByTagName("name").item(0).getTextContent();
            String sprite = nextChild.getElementsByTagName("sprite").item(0).getTextContent();
            int cooldown = Integer.parseInt((nextChild.getElementsByTagName("cooldown").item(0).getTextContent()));
            String factory = nextChild.getElementsByTagName("factory").item(0).getTextContent();
            String projectile = nextChild.getElementsByTagName("projectile").item(0).getTextContent();
            String trail = nextChild.getElementsByTagName("trail").item(0).getTextContent();
            int damage = Integer.parseInt((nextChild.getElementsByTagName("damage").item(0).getTextContent()));
            int life = Integer.parseInt((nextChild.getElementsByTagName("life").item(0).getTextContent()));
            int speed =  Integer.parseInt((nextChild.getElementsByTagName("speed").item(0).getTextContent()));
            try {
                Constructor constructor = Class.forName(factory).getConstructors()[0];
                ProjectileFactory projFac = (ProjectileFactory)constructor.newInstance(spriteAtlas.get(projectile),spriteAtlas.get(trail), damage, life, speed);
                Item item = new ProjectileItem(EntityType.MOUSE3ITEM, name, spriteAtlas.get(sprite),spriteAtlas.get("cooldown_bar"), cooldown,  projFac);
                items.addItem(name, item);
            }
            catch (Exception e){
                System.out.println("error");
            }
        }
        loadOthers(items, spriteAtlas);
        return items;
        */
    }

    /*
    public void loadOthers(ItemCatalogue items, Map<String, SpriteData> spriteAtlas){
        Item i = new ActiveShield(EntityType.SHIPITEM,"active_shield", spriteAtlas.get("active_shield_item"), spriteAtlas.get("cooldown_bar"),250, spriteAtlas.get("active_shield_effect"),50);
        items.addItem("active_shield", i);
        i = new ForceShield(EntityType.SHIPITEM,"force_shield", spriteAtlas.get("force_shield_item"), spriteAtlas.get("cooldown_bar"),250, spriteAtlas.get("force_shield_effect"),50);
        items.addItem("force_shield", i);
        i = new AntiShield(EntityType.SHIPITEM, "anti_shield", spriteAtlas.get("anti_shield_item"), spriteAtlas.get("cooldown_bar"),250, spriteAtlas.get("anti_shield_effect"),50);
        items.addItem("anti_shield", i);
        i = new BasicShield(EntityType.SHIPITEM, "basic_shield", spriteAtlas.get("basic_shield_item"), spriteAtlas.get("cooldown_bar"),250, spriteAtlas.get("basic_shield_effect"),50);
        items.addItem("basic_shield", i);
    }*/

}
