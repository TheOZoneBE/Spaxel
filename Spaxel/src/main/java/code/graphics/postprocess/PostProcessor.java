package code.graphics.postprocess;

import code.graphics.FBO;
import code.graphics.Quad;
import code.graphics.shaders.ShaderProgram;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

/**
 * Created by theod on 25-7-2017.
 */
public class PostProcessor {
    protected ShaderProgram program;
    protected Quad fboQuad;

    public PostProcessor(ShaderProgram program){
        this.program = program;
        this.fboQuad = new Quad();
    }

    public void postProcess(FBO in, FBO out){

    }
}
