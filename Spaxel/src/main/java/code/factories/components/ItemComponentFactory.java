package code.factories.components;

import code.components.Component;
import code.components.item.ItemType;
import code.components.item.primary.BasicLaserItemComponent;

/**
 * Created by theo on 19/06/17.
 */
public class ItemComponentFactory extends ComponentFactory {
    private String name;

    public Component make(){
        switch(name){
            case "basic_laser":
                return new BasicLaserItemComponent();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
