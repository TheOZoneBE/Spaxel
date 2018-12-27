package code.components.render;

import code.components.Component;
import code.components.ComponentType;
import code.components.render.subcomponents.Renderer;
import code.engine.NEntity;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by theo on 4/06/17.
 */
public class RenderComponent extends Component {
    private List<Renderer> renderers;
    private boolean visible;
    private RenderLayer layer;

    public RenderComponent(List<Renderer> renderers, boolean visible, RenderLayer layer) {
        super(ComponentType.RENDER);
        this.renderers = renderers;
        this.visible = visible;
        this.layer = layer;
    }

    public void render(NEntity entity, MasterBuffer buffer){
        if(visible){
            RenderData renderData = new RenderData();
            for (Renderer r: renderers){
                r.apply(renderData, entity);
            }
            buffer.addNewSprite(layer, renderData);
        }
    }

    public List<Renderer> getRenderers() {
        return renderers;
    }

    public void setRenderers(List<Renderer> renderers) {
        this.renderers = renderers;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Component copy(){
        List<Renderer> copied = new ArrayList<>();
        for (Renderer render: renderers){
            copied.add(render);
        }
        return new RenderComponent(copied, visible, layer);
    }
}
