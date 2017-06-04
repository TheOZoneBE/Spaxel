package code.components;

import code.engine.NEntity;
import code.graphics.MasterBuffer;
import code.math.VectorF;

/**
 * Created by theo on 4/06/17.
 */
public class RendererComponent extends Component{
    public RendererComponent() {
        super(ComponentType.RENDERER);
    }

    public void render(VectorF offset, NEntity entity, MasterBuffer buffer){

    }
}
