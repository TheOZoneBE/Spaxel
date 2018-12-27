package code.components.link;

import code.components.Component;
import code.components.ComponentType;
import code.engine.NEntity;

/**
 * Created by theo on 16/06/17.
 */
public class LinkComponent extends Component{
    private NEntity link;

    public LinkComponent(NEntity link) {
        super(ComponentType.LINK);
        this.link = link;
    }

    public NEntity getLink() {
        return link;
    }

    public void setLink(NEntity link) {
        this.link = link;
    }

    public Component copy(){
        return new LinkComponent(link.copy());
    }
}
