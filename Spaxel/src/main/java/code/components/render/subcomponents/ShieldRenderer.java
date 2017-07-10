package code.components.render.subcomponents;

import code.components.ComponentType;
import code.components.item.ShieldItemComponent;
import code.components.link.LinkComponent;
import code.engine.NEntity;
import code.graphics.RenderData;

/**
 * Created by theod on 10-7-2017.
 */
public class ShieldRenderer extends Renderer{
    public void apply(RenderData data, NEntity entity){
        NEntity link = ((LinkComponent)entity.getComponent(ComponentType.LINK)).getLink();
        ShieldItemComponent shc = (ShieldItemComponent) link.getComponent(ComponentType.ITEM);
        float[] sinCos = data.getSinCos();
        sinCos[2] = ((float)shc.getCapacity()/shc.getMaxCapacity())/2 + (float)0.15;
        data.setSinCos(sinCos);
    }
}
