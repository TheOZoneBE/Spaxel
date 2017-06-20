package code.components.ship;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

import java.util.List;

/**
 * Created by theo on 19/06/17.
 */
public class ShipComponent extends Component {
    private List<NEntity> items;

    public ShipComponent(List<NEntity> items) {
        super(ComponentType.SHIP);
        this.items = items;
    }

    public List<NEntity> getItems() {
        return items;
    }

    public void setItems(List<NEntity> items) {
        this.items = items;
    }
}
