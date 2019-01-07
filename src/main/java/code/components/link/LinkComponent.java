package code.components.link;

import code.components.Component;
import code.components.ComponentType;
import code.entity.Entity;

/**
 * Created by theo on 16/06/17.
 */
public class LinkComponent extends Component{
    private Entity link;

    public LinkComponent(Entity link) {
        super(ComponentType.LINK);
        this.link = link;
    }

    public Entity getLink() {
        return link;
    }

    public void setLink(Entity link) {
        this.link = link;
    }

    public Component copy(){
        return new LinkComponent(link.copy());
    }
}
