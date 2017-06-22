package code.components.primary;

import code.components.Component;
import code.components.ComponentType;
import code.components.link.LinkComponent;
import code.engine.Engine;
import code.engine.NEntity;

import java.util.List;

/**
 * Created by theo on 19/06/17.
 */
public class PrimaryComponent extends Component {
    private List<NEntity> items;

    public PrimaryComponent(List<NEntity> items) {
        super(ComponentType.PRIMARY);
        this.items = items;
    }

    public List<NEntity> getItems() {
        return items;
    }

    public void setItems(List<NEntity> items) {
        this.items = items;
    }

    public void addCascade(NEntity entity){
        for (NEntity e: items){
            e.addComponent(new LinkComponent(entity));
            Engine.getEngine().getNEntityStream().addEntity(e);
        }
    }

    public void removeCascade(){
        for (NEntity e: items){
            Engine.getEngine().getNEntityStream().removeEntity(e);
        }
    }
}
