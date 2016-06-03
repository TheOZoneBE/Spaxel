package code.resource;

import code.engine.EntityType;
import code.factories.ProjectileFactory;
import code.graphics.Sprite;
import code.inventory.Item;
import code.inventory.ItemCatalogue;
import code.inventory.ProjectileItem;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.lang.reflect.Constructor;
import java.util.Map;

public class ItemLoader extends EntityLoader {

    public ItemCatalogue loadItems(String path, Map<String, Sprite> spriteAtlas){
        super.loadFile(path);
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
                Item item = new ProjectileItem(EntityType.MOUSE1ITEM, spriteAtlas.get(sprite), spriteAtlas.get("cooldown_bar"),cooldown, projFac);
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
                Item item = new ProjectileItem(EntityType.MOUSE3ITEM, spriteAtlas.get(sprite),spriteAtlas.get("cooldown_bar"), cooldown,  projFac);
                items.addItem(name, item);
            }
            catch (Exception e){
                System.out.println("error");
            }
        }
        return items;
    }

}
