package code.components.behaviour.render;

import code.components.Component;
import code.components.ComponentType;
import code.components.behaviour.render.renderers.Renderer;
import code.components.storage.render.RenderStorage;
import code.entity.Entity;
import code.graphics.buffer.MasterBuffer;
import code.graphics.buffer.RenderJob;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by theo on 4/06/17.
 */
public class RenderBehaviour extends Component {
    private List<Renderer> renderers;


    public RenderBehaviour() {
        super(ComponentType.RENDER);
    }

    public RenderBehaviour(List<Renderer> renderers) {
        super(ComponentType.RENDER);
        this.renderers = renderers;
    }

    public void render(Entity entity, MasterBuffer buffer) {
        RenderStorage rndrStore = (RenderStorage) entity.getComponent(ComponentType.RENDER_STORE);

        if (rndrStore.isVisible()) {
            RenderJob renderJob = new RenderJob();
            for (Renderer r : renderers) {
                r.apply(renderJob, entity);
            }
            buffer.addNewRenderJob(rndrStore.getLayer(), renderJob);
        }
    }

    public List<Renderer> getRenderers() {
        return renderers;
    }

    public void setRenderers(List<Renderer> renderers) {
        this.renderers = renderers;
    }

    public Component copy() {
        return new RenderBehaviour(new ArrayList<>(renderers));
    }
}
