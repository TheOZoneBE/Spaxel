package code.graphics.postprocess;

import code.graphics.FBO;
import code.graphics.Quad;
import code.graphics.shaders.ShaderProgram;

/**
 * Created by theod on 25-7-2017.
 */
public abstract class PostProcessor {
    protected ShaderProgram program;
    protected Quad fboQuad;

    public PostProcessor(ShaderProgram program) {
        this.program = program;
        this.fboQuad = new Quad();
    }

    public abstract void postProcess(FBO in, FBO out);
}
