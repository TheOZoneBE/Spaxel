package code.factories.components;


import code.components.Component;
import code.components.render.RenderComponent;
import code.components.render.subcomponents.Renderer;

import java.util.List;

/**
 * Created by theo on 5/06/17.
 */
public class RenderComponentFactory extends ComponentFactory {
    private List<Renderer> renderers;
    private boolean visible;

    public Component make(){
        return new RenderComponent(renderers, visible);
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
}
