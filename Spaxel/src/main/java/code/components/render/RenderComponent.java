package code.components.render;

import code.components.Component;
import code.components.ComponentType;
import code.components.render.subcomponents.Renderer;
import code.engine.NEntity;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;

import java.util.List;

/**
 * Created by theo on 4/06/17.
 */
public class RenderComponent extends Component {
    private List<Renderer> renderers;

    public RenderComponent(List<Renderer> renderers) {
        super(ComponentType.RENDER);
        this.renderers = renderers;
    }

    public void render(NEntity entity, MasterBuffer buffer){
        RenderData renderData = new RenderData();
        for (Renderer r: renderers){
            r.apply(renderData, entity);
        }
        buffer.addNewSprite(renderData);
    }

    public List<Renderer> getRenderers() {
        return renderers;
    }

    public void setRenderers(List<Renderer> renderers) {
        this.renderers = renderers;
    }
}
